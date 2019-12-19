package com.faforever.commons.replay;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.io.BaseEncoding;
import com.google.common.io.ByteArrayDataInput;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

/**
 * Utility class for unpacking replay data.
 */
public class RawReplayDataReader {
  private enum CompressionType {
    LEGACY_QTCOMPRESS,
    LEGACY_ZSTD,
    UNKNOWN,
  }

  @JsonIgnoreProperties(ignoreUnknown = true)
  private static class ReplayHeader {
    /* Just enough to learn the compression type. */
    public CompressionType compressionType;

    @JsonCreator
    public ReplayHeader(@JsonProperty("compression") String compression) {
      if (compression == null)
        compressionType = CompressionType.LEGACY_QTCOMPRESS;
      else if (compression.equals("zstd"))
        compressionType = CompressionType.LEGACY_ZSTD;
      else
        compressionType = CompressionType.UNKNOWN;
    }
  }

  private ObjectMapper jsonParser;

  public RawReplayDataReader() {
    jsonParser = new ObjectMapper();
  }

  private byte[] uncompress(byte[] data, int start, int end, CompressionType ctype) throws IOException, InvalidReplayException {
    ByteArrayDataInput i;
    switch (ctype) {
      case LEGACY_QTCOMPRESS:
        /* FIXME data copy */
        String b64encodedData = new String(data, start, end - start, StandardCharsets.UTF_8);
        byte decodedData[];
        try {
          decodedData = BaseEncoding.base64().decode(b64encodedData);
        } catch (IllegalArgumentException e) {
          throw new InvalidReplayException("Replay data is not a valid base64 string");
        }
        return QtCompress.qUncompress(decodedData);
      case LEGACY_ZSTD:
        return ZstdUncompress.zstdUncompress(data, start, end);
      case UNKNOWN:
      default:
        throw new InvalidReplayException("Unknown replay compression format");
    }
  }

  private CompressionType getCompressionType(byte[] data, int start, int end) throws InvalidReplayException, IOException {
    try {
      ReplayHeader header = jsonParser.readValue(
        new String(Arrays.copyOfRange(data, start, end), StandardCharsets.UTF_8),
        ReplayHeader.class);
      return header.compressionType;
    } catch (JsonProcessingException e) {
      throw new InvalidReplayException("Failed to parse replay header", e);
    }
  }

  public byte[] read(Path replayPath) throws IOException, InvalidReplayException {
    byte[] replayData = Files.readAllBytes(replayPath);

    int headerEnd;
    for (headerEnd = 0; headerEnd < replayData.length; headerEnd++) {
      if (replayData[headerEnd] == '\n')
        break;
    }
    if (headerEnd == replayData.length) {
      throw new InvalidReplayException("Missing separator between replay header and body");
    }
    int dataStart = headerEnd + 1;

    CompressionType ctype = getCompressionType(replayData, 0, headerEnd);
    return uncompress(replayData, dataStart, replayData.length, ctype);
  }
}
