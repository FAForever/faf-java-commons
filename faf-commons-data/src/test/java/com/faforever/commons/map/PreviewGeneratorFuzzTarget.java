package com.faforever.commons.map;

import com.code_intelligence.jazzer.api.FuzzedDataProvider;
import org.luaj.vm2.LuaError;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.EOFException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

/**
 * Jazzer Fuzzing target
 * Run with
 * alexander@alex-desktop:faf-java-commons/faf-commons-data/build/libs$ docker run -v $(pwd):/fuzzing cifuzz/jazzer --cp=fuzz-target-jar-1.0-SNAPSHOT.jar --target_class=com.faforever.commons.map.PreviewGeneratorFuzzTarget
 * @author Alexander von Trostorff
 */
public class PreviewGeneratorFuzzTarget {
  private static final String saveLuaTemplate= "Scenario = {\n" +
    "  next_area_id = '4',\n" +
    "  Props = {},\n" +
    "  Areas = {},\n" +
    "  MasterChain = {\n" +
    "    ['_MASTERCHAIN_'] = {\n" +
    "      Markers = {\n" +
    "        ['ARMY_1'] = {\n" +
    "          ['type'] = STRING( 'Blank Marker' ),\n" +
    "          ['position'] = VECTOR3( 87.50, 25.00, 26.50 ),\n" +
    "          ['orientation'] = VECTOR3( 0.00, 0.00, 0.00 ),\n" +
    "          ['color'] = STRING( 'ff800080' ),\n" +
    "          ['prop'] = STRING( '/env/common/props/markers/M_Blank_prop.bp' ),\n" +
    "        },\n" +
    "        ['ARMY_2'] = {\n" +
    "          ['type'] = STRING( 'Blank Marker' ),\n" +
    "          ['position'] = VECTOR3( 172.50, 25.00, 27.50 ),\n" +
    "          ['orientation'] = VECTOR3( 0.00, 0.00, 0.00 ),\n" +
    "          ['color'] = STRING( 'ff800080' ),\n" +
    "          ['prop'] = STRING( '/env/common/props/markers/M_Blank_prop.bp' ),\n" +
    "        },\n" +
    "        ['Mass 01'] = {\n" +
    "          ['type'] = STRING( 'Mass' ),\n" +
    "          ['position'] = VECTOR3( %.1f, 25.00, 147.50 ),\n" +
    "          ['orientation'] = VECTOR3( 0.00, 0.00, 0.00 ),\n" +
    "          ['size'] = FLOAT( %.1f ),\n" +
    "          ['resource'] = BOOLEAN( true ),\n" +
    "          ['amount'] = FLOAT( %.2f),\n" +
    "          ['color'] = STRING( 'ff808080' ),\n" +
    "          ['editorIcon'] = STRING( '/textures/editor/marker_mass.bmp' ),\n" +
    "          ['prop'] = STRING( '/env/common/props/markers/M_Mass_prop.bp' ),\n" +
    "        },\n" +
    "        ['Mass 02'] = {\n" +
    "          ['type'] = STRING( 'Mass' ),\n" +
    "          ['position'] = VECTOR3( 87.50, 25.00, 22.50 ),\n" +
    "          ['orientation'] = VECTOR3( 0.00, 0.00, 0.00 ),\n" +
    "          ['size'] = FLOAT( 1.00 ),\n" +
    "          ['resource'] = BOOLEAN( true ),\n" +
    "          ['amount'] = FLOAT( 100.00 ),\n" +
    "          ['color'] = STRING( 'ff808080' ),\n" +
    "          ['editorIcon'] = STRING( '/textures/editor/marker_mass.bmp' ),\n" +
    "          ['prop'] = STRING( '/env/common/props/markers/M_Mass_prop.bp' ),\n" +
    "        },\n" +
    "      },\n" +
    "    },\n" +
    "  },\n" +
    "  Chains = {},\n" +
    "  next_queue_id = '1',\n" +
    "  Orders = {},\n" +
    "  next_platoon_id = '1',\n" +
    "  Platoons = {},\n" +
    "  next_army_id = '10',\n" +
    "  next_group_id = '1',\n" +
    "  next_unit_id = '1',\n" +
    "  Armies = {\n" +
    "    ['ARMY_1'] = {\n" +
    "      personality = '',\n" +
    "      plans = '',\n" +
    "      color = 0,\n" +
    "      faction = 0,\n" +
    "      Economy = {mass = 0, energy = 0},\n" +
    "      Alliances = {},\n" +
    "      ['Units'] = GROUP {\n" +
    "        orders = '',\n" +
    "        platoon = '',\n" +
    "        Units = {\n" +
    "          ['INITIAL'] = GROUP {\n" +
    "            orders = '',\n" +
    "            platoon = '',\n" +
    "            Units = {},\n" +
    "          },\n" +
    "        },\n" +
    "      },\n" +
    "      PlatoonBuilders = {\n" +
    "        next_platoon_builder_id = '0',\n" +
    "        Builders = {},\n" +
    "      },\n" +
    "    },\n" +
    "    ['ARMY_2'] = {\n" +
    "      personality = '',\n" +
    "      plans = '',\n" +
    "      color = 0,\n" +
    "      faction = 0,\n" +
    "      Economy = {mass = 0, energy = 0},\n" +
    "      Alliances = {},\n" +
    "      ['Units'] = GROUP {\n" +
    "        orders = '',\n" +
    "        platoon = '',\n" +
    "        Units = {\n" +
    "          ['INITIAL'] = GROUP {\n" +
    "            orders = '',\n" +
    "            platoon = '',\n" +
    "            Units = {},\n" +
    "          },\n" +
    "        },\n" +
    "      },\n" +
    "      PlatoonBuilders = {\n" +
    "        next_platoon_builder_id = '0',\n" +
    "        Builders = {},\n" +
    "      },\n" +
    "    },\n" +
    "    ['ARMY_3'] = {\n" +
    "      personality = '',\n" +
    "      plans = '',\n" +
    "      color = 0,\n" +
    "      faction = 0,\n" +
    "      Economy = {mass = 0, energy = 0},\n" +
    "      Alliances = {},\n" +
    "      ['Units'] = GROUP {\n" +
    "        orders = '',\n" +
    "        platoon = '',\n" +
    "        Units = {\n" +
    "          ['INITIAL'] = GROUP {\n" +
    "            orders = '',\n" +
    "            platoon = '',\n" +
    "            Units = {},\n" +
    "          },\n" +
    "        },\n" +
    "      },\n" +
    "      PlatoonBuilders = {\n" +
    "        next_platoon_builder_id = '0',\n" +
    "        Builders = {},\n" +
    "      },\n" +
    "    },\n" +
    "    ['ARMY_4'] = {\n" +
    "      personality = '',\n" +
    "      plans = '',\n" +
    "      color = 0,\n" +
    "      faction = 0,\n" +
    "      Economy = {mass = 0, energy = 0},\n" +
    "      Alliances = {},\n" +
    "      ['Units'] = GROUP {\n" +
    "        orders = '',\n" +
    "        platoon = '',\n" +
    "        Units = {\n" +
    "          ['INITIAL'] = GROUP {\n" +
    "            orders = '',\n" +
    "            platoon = '',\n" +
    "            Units = {},\n" +
    "          },\n" +
    "        },\n" +
    "      },\n" +
    "      PlatoonBuilders = {\n" +
    "        next_platoon_builder_id = '0',\n" +
    "        Builders = {},\n" +
    "      },\n" +
    "    },\n" +
    "    ['ARMY_5'] = {\n" +
    "      personality = '',\n" +
    "      plans = '',\n" +
    "      color = 0,\n" +
    "      faction = 0,\n" +
    "      Economy = {mass = 0, energy = 0},\n" +
    "      Alliances = {},\n" +
    "      ['Units'] = GROUP {\n" +
    "        orders = '',\n" +
    "        platoon = '',\n" +
    "        Units = {\n" +
    "          ['INITIAL'] = GROUP {\n" +
    "            orders = '',\n" +
    "            platoon = '',\n" +
    "            Units = {},\n" +
    "          },\n" +
    "        },\n" +
    "      },\n" +
    "      PlatoonBuilders = {\n" +
    "        next_platoon_builder_id = '0',\n" +
    "        Builders = {},\n" +
    "      },\n" +
    "    },\n" +
    "    ['ARMY_6'] = {\n" +
    "      personality = '',\n" +
    "      plans = '',\n" +
    "      color = 0,\n" +
    "      faction = 0,\n" +
    "      Economy = {mass = 0, energy = 0},\n" +
    "      Alliances = {},\n" +
    "      ['Units'] = GROUP {\n" +
    "        orders = '',\n" +
    "        platoon = '',\n" +
    "        Units = {\n" +
    "          ['INITIAL'] = GROUP {\n" +
    "            orders = '',\n" +
    "            platoon = '',\n" +
    "            Units = {},\n" +
    "          },\n" +
    "        },\n" +
    "      },\n" +
    "      PlatoonBuilders = {\n" +
    "        next_platoon_builder_id = '0',\n" +
    "        Builders = {},\n" +
    "      },\n" +
    "    },\n" +
    "    ['ARMY_7'] = {\n" +
    "      personality = '',\n" +
    "      plans = '',\n" +
    "      color = 0,\n" +
    "      faction = 0,\n" +
    "      Economy = {mass = 0, energy = 0},\n" +
    "      Alliances = {},\n" +
    "      ['Units'] = GROUP {\n" +
    "        orders = '',\n" +
    "        platoon = '',\n" +
    "        Units = {\n" +
    "          ['INITIAL'] = GROUP {\n" +
    "            orders = '',\n" +
    "            platoon = '',\n" +
    "            Units = {},\n" +
    "          },\n" +
    "        },\n" +
    "      },\n" +
    "      PlatoonBuilders = {\n" +
    "        next_platoon_builder_id = '0',\n" +
    "        Builders = {},\n" +
    "      },\n" +
    "    },\n" +
    "    ['ARMY_8'] = {\n" +
    "      personality = '',\n" +
    "      plans = '',\n" +
    "      color = 0,\n" +
    "      faction = 0,\n" +
    "      Economy = {mass = 0, energy = 0},\n" +
    "      Alliances = {},\n" +
    "      ['Units'] = GROUP {\n" +
    "        orders = '',\n" +
    "        platoon = '',\n" +
    "        Units = {\n" +
    "          ['INITIAL'] = GROUP {\n" +
    "            orders = '',\n" +
    "            platoon = '',\n" +
    "            Units = {},\n" +
    "          },\n" +
    "        },\n" +
    "      },\n" +
    "      PlatoonBuilders = {\n" +
    "        next_platoon_builder_id = '0',\n" +
    "        Builders = {},\n" +
    "      },\n" +
    "    },\n" +
    "    ['ARMY_9'] = {\n" +
    "      personality = '',\n" +
    "      plans = '',\n" +
    "      color = 0,\n" +
    "      faction = 0,\n" +
    "      Economy = {mass = 0, energy = 0},\n" +
    "      Alliances = {},\n" +
    "      ['Units'] = GROUP {\n" +
    "        orders = '',\n" +
    "        platoon = '',\n" +
    "        Units = {\n" +
    "          ['INITIAL'] = GROUP {\n" +
    "            orders = '',\n" +
    "            platoon = '',\n" +
    "            Units = {},\n" +
    "          },\n" +
    "        },\n" +
    "      },\n" +
    "      PlatoonBuilders = {\n" +
    "        next_platoon_builder_id = '0',\n" +
    "        Builders = {},\n" +
    "      },\n" +
    "    },\n" +
    "    ['NEUTRAL_CIVILIAN'] = {\n" +
    "      personality = '',\n" +
    "      plans = '',\n" +
    "      color = 0,\n" +
    "      faction = 0,\n" +
    "      Economy = {mass = 0, energy = 0},\n" +
    "      Alliances = {},\n" +
    "      ['Units'] = GROUP {\n" +
    "        orders = '',\n" +
    "        platoon = '',\n" +
    "        Units = {\n" +
    "          ['INITIAL'] = GROUP {\n" +
    "            orders = '',\n" +
    "            platoon = '',\n" +
    "            Units = {},\n" +
    "          },\n" +
    "        },\n" +
    "      },\n" +
    "      PlatoonBuilders = {\n" +
    "        next_platoon_builder_id = '0',\n" +
    "        Builders = {},\n" +
    "      },\n" +
    "    },\n" +
    "  },\n" +
    "}\n";

    public static void fuzzerTestOneInput(FuzzedDataProvider data) throws IOException {
      Path currentDir = Path.of(".");
      Path saveLua = currentDir.resolve("example_save.lua");
      Path scmap = currentDir.resolve("example.scmap");
      float randomFloat = data.consumeFloat();
      if(randomFloat == Float.NEGATIVE_INFINITY || randomFloat == Float.POSITIVE_INFINITY || Float.isNaN(randomFloat)){
        return;
      }
      Files.writeString(saveLua, String.format(saveLuaTemplate, randomFloat, randomFloat, randomFloat), StandardOpenOption.CREATE,
        StandardOpenOption.TRUNCATE_EXISTING);
      Files.write(scmap, data.consumeRemainingAsBytes(), StandardOpenOption.CREATE,
        StandardOpenOption.TRUNCATE_EXISTING);

      try {
        PreviewGenerator.generatePreview(
          Path.of("."), 256, 256
        );
        /*try{
          ImageIO.write(bufferedImage, "png", Path.of("./"+Math.random()+".png").toFile());
        }catch (Exception e){
          throw new Error(e);
        }*/
      } catch (PreviewGenerator.IllegalInput ignore) {

      }
    }
}
