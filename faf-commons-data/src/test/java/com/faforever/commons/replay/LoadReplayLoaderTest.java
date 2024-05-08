package com.faforever.commons.replay;

import com.faforever.commons.replay.body.ReplayBodyEvent;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.compress.compressors.CompressorException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Objects;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class LoadReplayLoaderTest {

  @TempDir
  public Path temporaryFolder;

  private final ObjectMapper objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

  @Test
  public void parseBinary01() throws CompressorException, IOException {
    assertDoesNotThrow(
      () -> {
        Path fafReplayFile = temporaryFolder.resolve("TestCommands01.fafreplay");
        Files.copy(Objects.requireNonNull(getClass().getResourceAsStream("/replay/TestCommands01.fafreplay")), fafReplayFile);
        ReplayContainer fafReplayContainer = ReplayLoader.loadFAFReplayFromDisk(fafReplayFile);
      }
    );
  }

  @Test
  public void parseBinary02() throws CompressorException, IOException {
    assertDoesNotThrow(
      () -> {
        Path fafReplayFile = temporaryFolder.resolve("TestModeratorEvents.fafreplay");
        Files.copy(Objects.requireNonNull(getClass().getResourceAsStream("/replay/TestModeratorEvents.fafreplay")), fafReplayFile);
        ReplayContainer fafReplayContainer = ReplayLoader.loadFAFReplayFromDisk(fafReplayFile);
      }
    );
  }

  @Test
  public void parseBinary03() throws CompressorException, IOException {
    assertDoesNotThrow(
      () -> {
        Path fafReplayFile = temporaryFolder.resolve("zstd_reference.fafreplay");
        Files.copy(Objects.requireNonNull(getClass().getResourceAsStream("/replay/zstd_reference.fafreplay")), fafReplayFile);
        ReplayContainer fafReplayContainer = ReplayLoader.loadFAFReplayFromDisk(fafReplayFile);
      }
    );
  }

  @Test
  public void parseBinary04() throws CompressorException, IOException {
    assertDoesNotThrow(
      () -> {
        Path fafReplayFile = temporaryFolder.resolve("test.fafreplay");
        Files.copy(Objects.requireNonNull(getClass().getResourceAsStream("/replay/test.fafreplay")), fafReplayFile);
        ReplayContainer fafReplayContainer = ReplayLoader.loadFAFReplayFromDisk(fafReplayFile);
      }
    );
  }

  @Test
  public void parseBinary05() throws CompressorException, IOException {
    assertDoesNotThrow(
      () -> {
        Path fafReplayFile = temporaryFolder.resolve("22451957.fafreplay");
        Files.copy(Objects.requireNonNull(getClass().getResourceAsStream("/replay/load/22451957.fafreplay")), fafReplayFile);
        ReplayContainer fafReplayContainer = ReplayLoader.loadFAFReplayFromDisk(fafReplayFile);
      }
    );
  }

  @Test
  public void parseBinary06() throws CompressorException, IOException {
    assertDoesNotThrow(
      () -> {
        Path fafReplayFile = temporaryFolder.resolve("22453414.fafreplay");
        Files.copy(Objects.requireNonNull(getClass().getResourceAsStream("/replay/load/22453414.fafreplay")), fafReplayFile);
        ReplayContainer fafReplayContainer = ReplayLoader.loadFAFReplayFromDisk(fafReplayFile);
      }
    );
  }

  @Test
  public void parseBinary07() throws CompressorException, IOException {
    assertDoesNotThrow(
      () -> {
        Path fafReplayFile = temporaryFolder.resolve("22453511.fafreplay");
        Files.copy(Objects.requireNonNull(getClass().getResourceAsStream("/replay/load/22453511.fafreplay")), fafReplayFile);
        ReplayContainer fafReplayContainer = ReplayLoader.loadFAFReplayFromDisk(fafReplayFile);
      }
    );
  }

  @Test
  public void compareBinary01() throws CompressorException, IOException {
    Path fafReplayFile = temporaryFolder.resolve("22338092.fafreplay");
    Files.copy(Objects.requireNonNull(getClass().getResourceAsStream("/replay/load/22338092.fafreplay")), fafReplayFile);

    Path scfaReplayFile = temporaryFolder.resolve("22338092.scfareplay");
    Files.copy(Objects.requireNonNull(getClass().getResourceAsStream("/replay/load/22338092.scfareplay")), scfaReplayFile);

    ReplayContainer fafReplayContainer = ReplayLoader.loadFAFReplayFromDisk(fafReplayFile);
    assertThat("No unprocessed tokens", fafReplayContainer.body().events().stream().filter(e -> e instanceof ReplayBodyEvent.Unprocessed).findAny().isEmpty());
    assertThat("No error tokens", fafReplayContainer.body().events().stream().filter(e -> e instanceof ReplayBodyEvent.ProcessingError).findAny().isEmpty());

    ReplayContainer scfaReplayContainer = ReplayLoader.loadSCFAReplayFromDisk(scfaReplayFile);
    assertThat("No unprocessed tokens", scfaReplayContainer.body().events().stream().filter(e -> e instanceof ReplayBodyEvent.Unprocessed).findAny().isEmpty());
    assertThat("No error tokens", scfaReplayContainer.body().events().stream().filter(e -> e instanceof ReplayBodyEvent.ProcessingError).findAny().isEmpty());

    assertEquals(scfaReplayContainer.body().events().size(), fafReplayContainer.body().events().size());
    assertArrayEquals( scfaReplayContainer.body().events().toArray(), fafReplayContainer.body().events().toArray());
  }

  @Test
  public void compareBinary02() throws CompressorException, IOException {
    Path fafReplayFile = temporaryFolder.resolve("22373098.fafreplay");
    Files.copy(Objects.requireNonNull(getClass().getResourceAsStream("/replay/load/22373098.fafreplay")), fafReplayFile);

    Path scfaReplayFile = temporaryFolder.resolve("22373098.scfareplay");
    Files.copy(Objects.requireNonNull(getClass().getResourceAsStream("/replay/load/22373098.scfareplay")), scfaReplayFile);

    ReplayContainer fafReplayContainer = ReplayLoader.loadFAFReplayFromDisk(fafReplayFile);
    assertThat("No unprocessed tokens", fafReplayContainer.body().events().stream().filter(e -> e instanceof ReplayBodyEvent.Unprocessed).findAny().isEmpty());
    assertThat("No error tokens", fafReplayContainer.body().events().stream().filter(e -> e instanceof ReplayBodyEvent.ProcessingError).findAny().isEmpty());

    ReplayContainer scfaReplayContainer = ReplayLoader.loadSCFAReplayFromDisk(scfaReplayFile);
    assertThat("No unprocessed tokens", scfaReplayContainer.body().events().stream().filter(e -> e instanceof ReplayBodyEvent.Unprocessed).findAny().isEmpty());
    assertThat("No error tokens", scfaReplayContainer.body().events().stream().filter(e -> e instanceof ReplayBodyEvent.ProcessingError).findAny().isEmpty());

    assertEquals(scfaReplayContainer.body().events().size(), fafReplayContainer.body().events().size());
    assertArrayEquals( scfaReplayContainer.body().events().toArray(), fafReplayContainer.body().events().toArray());

  }

  @Test
  public void compareBinary03() throws CompressorException, IOException {
    Path fafReplayFile = temporaryFolder.resolve("22425616.fafreplay");
    Files.copy(Objects.requireNonNull(getClass().getResourceAsStream("/replay/load/22425616.fafreplay")), fafReplayFile);

    Path scfaReplayFile = temporaryFolder.resolve("22425616.scfareplay");
    Files.copy(Objects.requireNonNull(getClass().getResourceAsStream("/replay/load/22425616.scfareplay")), scfaReplayFile);

    ReplayContainer fafReplayContainer = ReplayLoader.loadFAFReplayFromDisk(fafReplayFile);
    assertThat("No unprocessed tokens", fafReplayContainer.body().events().stream().filter(e -> e instanceof ReplayBodyEvent.Unprocessed).findAny().isEmpty());
    assertThat("No error tokens", fafReplayContainer.body().events().stream().filter(e -> e instanceof ReplayBodyEvent.ProcessingError).findAny().isEmpty());

    ReplayContainer scfaReplayContainer = ReplayLoader.loadSCFAReplayFromDisk(scfaReplayFile);
    assertThat("No unprocessed tokens", scfaReplayContainer.body().events().stream().filter(e -> e instanceof ReplayBodyEvent.Unprocessed).findAny().isEmpty());
    assertThat("No error tokens", scfaReplayContainer.body().events().stream().filter(e -> e instanceof ReplayBodyEvent.ProcessingError).findAny().isEmpty());

    assertEquals(scfaReplayContainer.body().events().size(), fafReplayContainer.body().events().size());
    assertArrayEquals( scfaReplayContainer.body().events().toArray(), fafReplayContainer.body().events().toArray());
  }
}
