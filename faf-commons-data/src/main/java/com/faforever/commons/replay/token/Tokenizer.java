package com.faforever.commons.replay.token;

import com.google.common.io.LittleEndianDataInputStream;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.*;

@Slf4j
public class Tokenizer {

  public static List<Token> tokenize(LittleEndianDataInputStream dataStream) throws IOException {
    ArrayList<Token> tokens = new ArrayList<>();
    while (dataStream.available() > 0) {
      int tokenId = dataStream.readUnsignedByte();
      int tokenLength = dataStream.readUnsignedShort();
      byte[] tokenContent = dataStream.readNBytes( tokenLength - 3);

      tokens.add(new Token(Token.TokenId.values()[tokenId], tokenLength, tokenContent));
    }

    return tokens;
  }
}
