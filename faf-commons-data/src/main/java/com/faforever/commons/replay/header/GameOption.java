package com.faforever.commons.replay.header;

/**
 * Populated by the game options field of the table that is passed to `CLobby:LaunchGame`
 * @param autoTeams
 * @param teamLock
 * @param teamSpawn
 * @param allowObservers
 * @param cheatsEnabled
 * @param prebuiltUnits
 * @param revealedCivilians
 * @param scoreEnabled
 * @param unitCap
 * @param unRated
 * @param victory
 */
public record GameOption(AutoTeams autoTeams, TeamLock teamLock, TeamSpawn teamSpawn, boolean allowObservers,
                         boolean cheatsEnabled, boolean prebuiltUnits, boolean revealedCivilians, boolean scoreEnabled,
                         int unitCap, boolean unRated, Victory victory) {

  public enum AutoTeams {
    none("None"),
    manual("Manual"),
    tvsb("Top versus bottom"),
    lvsr("Left versus right"),
    pvsi("Even versus uneven");

    private final String string;

    AutoTeams(String string) {
      this.string = string;
    }
  }

  public enum TeamLock {
    locked("Locked"), unlocked("Unlocked");

    private final String string;

    TeamLock(String string) {
      this.string = string;
    }
  }

  public enum TeamSpawn {
    fixed("Fixed"),
    random("Random"),
    balanced("Balanced"),
    balanced_flex("Flexible balanced"),
    random_reveal("Random and revealed"),
    balanced_reveal("Balanced and revealed"),
    balanced_reveal_mirrored("Mirror balanced and revealed"),
    balanced_flex_reveal("Flexible balanced and revealed");

    private final String string;

    TeamSpawn(String string) {
      this.string = string;
    }
  }

  public enum Victory {
    demoralization("Assasination"),
    domination("Supremacy"),
    eradication("Annihilation"),
    sandbox("Sandbox");

    private final String string;

    Victory(String string) {
      this.string = string;
    }
  }

}
