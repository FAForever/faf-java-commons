package com.faforever.commons.replay;

import ch.qos.logback.classic.encoder.JsonEncoder;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.ImmutableMap;
import com.google.common.io.LittleEndianDataInputStream;
import org.apache.commons.compress.compressors.CompressorException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class LoadReplayTest {

  @TempDir
  public Path temporaryFolder;

  private final ObjectMapper objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

  @Test
  public void parseBinary01() throws CompressorException, IOException {
    assertDoesNotThrow(
      () -> {
        Path fafReplayFile = temporaryFolder.resolve("TestCommands01.fafreplay");
        Files.copy(Objects.requireNonNull(getClass().getResourceAsStream("/replay/TestCommands01.fafreplay")), fafReplayFile);
        ReplayContainer fafReplayContainer = LoadReplay.loadFAFReplayFromDisk(fafReplayFile);
      }
    );
  }

  @Test
  public void parseBinary02() throws CompressorException, IOException {
    assertDoesNotThrow(
      () -> {
        Path fafReplayFile = temporaryFolder.resolve("TestModeratorEvents.fafreplay");
        Files.copy(Objects.requireNonNull(getClass().getResourceAsStream("/replay/TestModeratorEvents.fafreplay")), fafReplayFile);
        ReplayContainer fafReplayContainer = LoadReplay.loadFAFReplayFromDisk(fafReplayFile);
      }
    );
  }

  @Test
  public void parseBinary03() throws CompressorException, IOException {
    assertDoesNotThrow(
      () -> {
        Path fafReplayFile = temporaryFolder.resolve("zstd_reference.fafreplay");
        Files.copy(Objects.requireNonNull(getClass().getResourceAsStream("/replay/zstd_reference.fafreplay")), fafReplayFile);
        ReplayContainer fafReplayContainer = LoadReplay.loadFAFReplayFromDisk(fafReplayFile);
      }
    );
  }

  @Test
  public void parseBinary04() throws CompressorException, IOException {
    assertDoesNotThrow(
      () -> {
        Path fafReplayFile = temporaryFolder.resolve("test.fafreplay");
        Files.copy(Objects.requireNonNull(getClass().getResourceAsStream("/replay/test.fafreplay")), fafReplayFile);
        ReplayContainer fafReplayContainer = LoadReplay.loadFAFReplayFromDisk(fafReplayFile);
      }
    );
  }

  @Test
  public void compareBinary01() throws CompressorException, IOException {
    Path fafReplayFile = temporaryFolder.resolve("22338092.fafreplay");
    Files.copy(Objects.requireNonNull(getClass().getResourceAsStream("/replay/load/22338092.fafreplay")), fafReplayFile);

    Path scfaReplayFile = temporaryFolder.resolve("22338092.scfareplay");
    Files.copy(Objects.requireNonNull(getClass().getResourceAsStream("/replay/load/22338092.scfareplay")), scfaReplayFile);

    ReplayContainer fafReplayContainer = LoadReplay.loadFAFReplayFromDisk(fafReplayFile);
    ReplayContainer scfaReplayContainer = LoadReplay.loadSCFAReplayFromDisk(scfaReplayFile);

    assertEquals(scfaReplayContainer.replay().bytes().length, fafReplayContainer.replay().bytes().length);
    assertEquals(Arrays.hashCode(scfaReplayContainer.replay().bytes()), Arrays.hashCode(fafReplayContainer.replay().bytes()));
    assertArrayEquals( scfaReplayContainer.replay().bytes(), fafReplayContainer.replay().bytes());
  }

  @Test
  public void compareBinary02() throws CompressorException, IOException {
    Path fafReplayFile = temporaryFolder.resolve("22373098.fafreplay");
    Files.copy(Objects.requireNonNull(getClass().getResourceAsStream("/replay/load/22373098.fafreplay")), fafReplayFile);

    Path scfaReplayFile = temporaryFolder.resolve("22373098.scfareplay");
    Files.copy(Objects.requireNonNull(getClass().getResourceAsStream("/replay/load/22373098.scfareplay")), scfaReplayFile);

    ReplayContainer fafReplayContainer = LoadReplay.loadFAFReplayFromDisk(fafReplayFile);
    ReplayContainer scfaReplayContainer = LoadReplay.loadSCFAReplayFromDisk(scfaReplayFile);

    assertEquals(scfaReplayContainer.replay().bytes().length, fafReplayContainer.replay().bytes().length);
    assertEquals(Arrays.hashCode(scfaReplayContainer.replay().bytes()), Arrays.hashCode(fafReplayContainer.replay().bytes()));
    assertArrayEquals( scfaReplayContainer.replay().bytes(), fafReplayContainer.replay().bytes());

  }

  @Test
  public void compareBinary03() throws CompressorException, IOException {
    Path fafReplayFile = temporaryFolder.resolve("22425616.fafreplay");
    Files.copy(Objects.requireNonNull(getClass().getResourceAsStream("/replay/load/22425616.fafreplay")), fafReplayFile);

    Path scfaReplayFile = temporaryFolder.resolve("22425616.scfareplay");
    Files.copy(Objects.requireNonNull(getClass().getResourceAsStream("/replay/load/22425616.scfareplay")), scfaReplayFile);

    ReplayContainer fafReplayContainer = LoadReplay.loadFAFReplayFromDisk(fafReplayFile);
    ReplayContainer scfaReplayContainer = LoadReplay.loadSCFAReplayFromDisk(scfaReplayFile);

    assertEquals(scfaReplayContainer.replay().bytes().length, fafReplayContainer.replay().bytes().length);
    assertEquals(Arrays.hashCode(scfaReplayContainer.replay().bytes()), Arrays.hashCode(fafReplayContainer.replay().bytes()));
    assertArrayEquals( scfaReplayContainer.replay().bytes(), fafReplayContainer.replay().bytes());

  }
}
