package com.faforever.commons.replay;

import com.faforever.commons.replay.body.Body;
import com.faforever.commons.replay.header.Header;
import com.faforever.commons.replay.header.parse.Parser;
import com.faforever.commons.replay.header.token.Token;
import com.faforever.commons.replay.header.token.Tokenizer;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.google.common.io.BaseEncoding;
import com.google.common.io.LittleEndianDataInputStream;
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
import java.util.List;
import java.util.Objects;

public class Replay {

  private static Header loadSCFAReplayHeader(LittleEndianDataInputStream stream) throws IOException{
    Token headerToken = Tokenizer.tokenize(stream);
    return Parser.parseHeader(headerToken);
  }

  private static Body loadSCFAReplayBody(LittleEndianDataInputStream stream) throws IOException{
    List<com.faforever.commons.replay.body.token.Token> bodyTokens = com.faforever.commons.replay.body.token.Tokenizer.tokenize(stream);
    return new Body(com.faforever.commons.replay.body.parse.Parser.parseTokens(bodyTokens));
  }

  private static ReplayContainer loadSCFAReplayFromMemory(ReplayMetadata metadata, ReplayBinaryFormat.BinarySCFA bytes) throws IOException {
    try (LittleEndianDataInputStream stream = new LittleEndianDataInputStream((new ByteArrayInputStream(bytes.bytes())))) {
      Header replayHead = loadSCFAReplayHeader(stream);
      Body replayBody = loadSCFAReplayBody(stream);
      return new ReplayContainer(metadata, replayHead, replayBody, bytes.bytes());
    }
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
