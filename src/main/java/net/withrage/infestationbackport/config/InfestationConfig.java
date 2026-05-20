package net.withrage.infestationbackport.config;

import net.minecraftforge.fml.loading.FMLPaths;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public class InfestationConfig {
    private static final Path PATH = FMLPaths.CONFIGDIR.get().resolve("infestationbackport.toml");

    public static int spawnChance = 15;
    public static int minSpawn = 1;
    public static int maxSpawn = 3;

    public static void load() {
        try {
            if (!Files.exists(PATH)) {
                writeDefaultFile();
                return;
            }

            Map<String, String> values = readSimpleToml(PATH);

            spawnChance = getInt(values, "general.spawnChance", spawnChance);
            minSpawn= getInt(values, "general.minSpawn", minSpawn);
            maxSpawn = getInt(values, "general.maxSpawn", maxSpawn);

        } catch (Exception e) {
            e.printStackTrace();

            try {
                writeDefaultFile();
            } catch (Exception ignored) {
            }
        }
    }

    private static Map<String, String> readSimpleToml(Path path) throws Exception {
        Map<String, String> values = new HashMap<>();
        String section = "";

        for (String rawLine : Files.readAllLines(path)) {
            String line = rawLine.trim();

            if (line.isEmpty() || line.startsWith("#")) continue;

            int commentIndex = line.indexOf("#");
            if (commentIndex >= 0) {
                line = line.substring(0, commentIndex).trim();
            }

            if (line.startsWith("[") && line.endsWith("]")) {
                section = line.substring(1, line.length() - 1).trim();
                continue;
            }

            int equalsIndex = line.indexOf("=");
            if (equalsIndex < 0) continue;

            String key = line.substring(0, equalsIndex).trim();
            String value = line.substring(equalsIndex + 1).trim();

            values.put(section + "." + key, value);
        }

        return values;
    }

    private static int getInt(Map<String, String> values,
                              String key,
                              int def) {

        String value = values.get(key);
        if (value == null) return def;

        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            return def;
        }
    }

    private static void writeDefaultFile() throws Exception {
        Files.createDirectories(PATH.getParent());

        String content = """
                # Potion of Infestation Backport Configuration
                
                [general]
                
                # Chance (0 - 100) that silverfish spawn when an infested entity is damaged.
                spawnChance = 15
                
                # Minimum number of silverfish that can spawn.
                minSpawn = 1
                
                # Maximum number of silverfish that can spawn.
                maxSpawn = 3
                """;

        Files.createDirectories(PATH.getParent());
        Files.writeString(PATH, content);
    }
}
