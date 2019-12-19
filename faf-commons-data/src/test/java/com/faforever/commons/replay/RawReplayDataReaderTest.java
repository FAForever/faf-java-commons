package com.faforever.commons.replay;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

import static org.hamcrest.MatcherAssert.assertThat;

public class RawReplayDataReaderTest {

  @TempDir
  public Path temporaryFolder;

  private void testBadReplay(String name) throws Exception {
    Path replayFile = temporaryFolder.resolve("tmp.fafreplay");
    Files.copy(getClass().getResourceAsStream(String.format("/replay/%s.fafreplay", name)), replayFile);
    Assertions.assertThrows(InvalidReplayException.class, () -> new RawReplayDataReader().read(replayFile));
  }

  @Test
  public void testNoNewlineHeader() throws Exception {
    testBadReplay("bad_nonewline");
  }

  @Test
  public void testBadJsonHeader() throws Exception {
    testBadReplay("bad_json");
  }

  @Test
  public void testBadCompressionType() throws Exception {
    testBadReplay("bad_compression_type");
  }

  @Test
  public void testUnknownCompression() throws Exception {
    testBadReplay("bad_unknown_compression");
  }

  @Test
  public void testBadBase64() throws Exception {
    testBadReplay("bad_base64");
  }

  @Test
  public void testReferenceZstd() throws Exception {
    Path replayFile = temporaryFolder.resolve("tmp.fafreplay");
    Path referenceFile = temporaryFolder.resolve("reference.fafreplay");
    Files.copy(getClass().getResourceAsStream("/replay/zstd_reference.fafreplay"), replayFile);
    Files.copy(getClass().getResourceAsStream("/replay/zstd_reference.raw"), referenceFile);

    byte data[] = new RawReplayDataReader().read(replayFile);
    byte reference[] = Files.readAllBytes(referenceFile);
    assertThat("Zstd compressed replay matches reference", Arrays.equals(data, reference));
  }
}
