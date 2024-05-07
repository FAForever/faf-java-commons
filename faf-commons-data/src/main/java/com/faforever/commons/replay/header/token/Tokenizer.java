package com.faforever.commons.replay.header.token;

import com.faforever.commons.replay.header.Source;
import com.faforever.commons.replay.shared.Utils;
import com.google.common.io.LittleEndianDataInputStream;
import org.jetbrains.annotations.Contract;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Tokenizer {

  @Contract(pure = true)
  public static Token tokenize(LittleEndianDataInputStream dataStream) throws IOException {

    String gameVersion = Utils.parseString(dataStream);
    String arg1 = Utils.parseString(dataStream); // Always \r\n

    String[] replayAndScenario = Utils.parseString(dataStream).split("\\r\\n");
    String replayVersion = replayAndScenario[0];
    String pathToScenario = replayAndScenario[1];
    String arg2 = Utils.parseString(dataStream); // always \r\n and some unknown character

    int sizeModsInBytes = dataStream.readInt();
    byte[] mods = dataStream.readNBytes(sizeModsInBytes);

    int sizeGameOptionsInBytes = dataStream.readInt();
    byte[] gameOptions = dataStream.readNBytes(sizeGameOptionsInBytes);

    int numberOfClients = dataStream.readUnsignedByte();
    List<Source> clients = new ArrayList<>(numberOfClients);
    for (int i = 0; i < numberOfClients; i++) {
      String playerName = Utils.parseString(dataStream);
      int playerId = dataStream.readInt();
      Source source = new Source(i, playerId, playerName);
      clients.add(source);
    }

    boolean cheatsEnabled = dataStream.readUnsignedByte() > 0;

    int numberOfArmies = dataStream.readUnsignedByte();
    List<byte[]> playerOptions = new ArrayList<>(numberOfClients);
    for (int i = 0; i < numberOfArmies; i++) {
      int sizePlayerOptionsInBytes = dataStream.readInt();
      byte[] options = dataStream.readNBytes(sizePlayerOptionsInBytes );
      int playerSource = dataStream.readUnsignedByte();
      playerOptions.add(options);

      if (playerSource != 255) {
        byte[] arg3 = dataStream.readNBytes(1);
      }
    }

    int seed = dataStream.readInt();

    return new Token(gameVersion, replayVersion, pathToScenario, cheatsEnabled, seed, clients, mods, gameOptions, playerOptions);
  }
}
