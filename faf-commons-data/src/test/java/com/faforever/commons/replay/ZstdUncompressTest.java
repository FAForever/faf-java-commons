package com.faforever.commons.replay;

import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;

import static com.faforever.commons.test.IsUtilityClassMatcher.isUtilityClass;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class ZstdUncompressTest {
  public static final byte[] UNCOMPRESSED_BYTES = "fafafafafafafaf".getBytes(StandardCharsets.US_ASCII);
  public static final byte[] COMPRESSED_BYTES = new byte[]{
    40, -75, 47, -3, 36, 15, 69, 0, 0, 16, 102, 97, 1, 0, 29, 14, 11, -66, 93, -9, 17
  };

  @Test
  void testIsUtilityClass() {
    assertThat(ZstdUncompress.class, isUtilityClass());
  }

  @Test
  void testZstdUncompress() throws Exception {
    byte[] uncompressedBytes = ZstdUncompress.zstdUncompress(COMPRESSED_BYTES, 0, COMPRESSED_BYTES.length);
    assertArrayEquals(UNCOMPRESSED_BYTES, uncompressedBytes);
  }

  @Test
  void testZstdUncompressRanges() throws Exception {
    byte[] input = new byte[COMPRESSED_BYTES.length + 10];
    System.arraycopy(COMPRESSED_BYTES, 0, input, 4, COMPRESSED_BYTES.length);
    byte[] uncompressedBytes = ZstdUncompress.zstdUncompress(input, 4, COMPRESSED_BYTES.length + 4);
    assertArrayEquals(UNCOMPRESSED_BYTES, uncompressedBytes);
  }
}
