package com.faforever.commons.replay;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.google.common.io.BaseEncoding;
import org.apache.commons.compress.compressors.CompressorException;
import org.apache.commons.compress.compressors.CompressorInputStream;
import org.apache.commons.compress.compressors.CompressorStreamFactory;
import org.apache.commons.compress.utils.IOUtils;
import org.jetbrains.annotations.NotNull;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Objects;

public class Replay {

  private static ReplayContainer loadSCFAReplayFromMemory(ReplayMetadata metadata, ReplayBinaryFormat.BinarySCFA bytes) {
    return new ReplayContainer(metadata, bytes);
  }

  public static ReplayContainer loadSCFAReplayFromDisk(Path scfaReplayFile)  throws IOException, IllegalArgumentException  {
    if (!scfaReplayFile.toString().toLowerCase().endsWith("scfareplay")) {
      throw new IllegalArgumentException ("Unknown file format: " + scfaReplayFile.getFileName());
    }

    byte[] bytes = Files.readAllBytes(scfaReplayFile);
    return loadSCFAReplayFromMemory(null, new ReplayBinaryFormat.BinarySCFA(bytes));
  }

  private static ReplayContainer loadFAFReplayFromMemory(ReplayBinaryFormat.WithContext fafReplayBytes)  throws IOException, CompressorException {
    int separator = findSeparatorIndex(fafReplayBytes.bytes());
    byte[] metadataBytes = Arrays.copyOfRange(fafReplayBytes.bytes(), 0, separator);
    String metadataString = new String(metadataBytes, StandardCharsets.UTF_8);

    ObjectMapper parsedMetadata = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    ReplayMetadata replayMetadata = parsedMetadata.readValue(metadataString, ReplayMetadata.class);

    byte[] compressedReplayBytes = Arrays.copyOfRange(fafReplayBytes.bytes(), separator + 1, fafReplayBytes.bytes().length);
    byte[] replayBytes = decompress(compressedReplayBytes, replayMetadata);

    return loadSCFAReplayFromMemory(replayMetadata, new ReplayBinaryFormat.BinarySCFA(replayBytes));
  }

  public static ReplayContainer loadFAFReplayFromDisk(Path fafReplayFile) throws IOException, CompressorException, IllegalArgumentException  {
    if (!fafReplayFile.toString().toLowerCase().endsWith("fafreplay")) {
      throw new IllegalArgumentException ("Unknown file format: " + fafReplayFile.getFileName());
    }

    byte[] replayBytes = Files.readAllBytes(fafReplayFile);
    return loadFAFReplayFromMemory(new ReplayBinaryFormat.WithContext(replayBytes));
  }

  private static int findSeparatorIndex(byte[] replayData) {
    int headerEnd;
    for (headerEnd = 0; headerEnd < replayData.length; headerEnd++) {
      if (replayData[headerEnd] == '\n') {
        return headerEnd;
      }
    }
    throw new IllegalArgumentException("Missing separator between replay header and body");
  }

  private static byte[] decompress(byte[] data, @NotNull ReplayMetadata metadata) throws IOException, CompressorException {
    CompressionType compressionType = Objects.requireNonNullElse(metadata.getCompression(), CompressionType.QTCOMPRESS);

    switch (compressionType) {
      case QTCOMPRESS: {
        return QtCompress.qUncompress(BaseEncoding.base64().decode(new String(data)));
      }
      case ZSTD: {
        ByteArrayInputStream arrayInputStream = new ByteArrayInputStream(data);
        CompressorInputStream compressorInputStream = new CompressorStreamFactory()
          .createCompressorInputStream(arrayInputStream);

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        IOUtils.copy(compressorInputStream, out);
        return out.toByteArray();
      }
      case UNKNOWN:
      default:
        throw new IOException("Unknown replay format in replay file");
    }
  }
}
