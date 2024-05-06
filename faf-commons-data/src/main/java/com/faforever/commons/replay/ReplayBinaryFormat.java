package com.faforever.commons.replay;

public sealed interface ReplayBinaryFormat {

  record WithContext (byte[] bytes) implements ReplayBinaryFormat {}

  record CompressedSCFA (byte[] bytes) implements ReplayBinaryFormat {}

  record BinarySCFA(byte[] bytes) implements ReplayBinaryFormat {}

}
