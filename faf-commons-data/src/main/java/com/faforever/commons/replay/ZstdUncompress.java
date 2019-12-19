package com.faforever.commons.replay;

import org.apache.commons.compress.compressors.zstandard.ZstdCompressorInputStream;
import org.apache.commons.compress.utils.IOUtils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * Facade for zstd decompression.
 */
public final class ZstdUncompress {

  private ZstdUncompress() { throw new AssertionError("Not instantiatable"); }

  public static byte[] zstdUncompress(byte[] bytes, int start, int end) throws IOException {
    ZstdCompressorInputStream uncompress = new ZstdCompressorInputStream(
      new ByteArrayInputStream(bytes, start, end - start));
    ByteArrayOutputStream out = new ByteArrayOutputStream();
    IOUtils.copy(uncompress, out);
    return out.toByteArray();
  }
}
