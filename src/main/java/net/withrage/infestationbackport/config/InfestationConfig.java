package net.withrage.infestationbackport.config;

import net.fabricmc.loader.api.FabricLoader;
import org.tomlj.Toml;
import org.tomlj.TomlParseResult;

import java.nio.file.Files;
import java.nio.file.Path;

public class InfestationConfig {
    private static final Path PATH = FabricLoader.getInstance().getConfigDir().resolve("infestationbackport.toml");

    public static int spawnChance = 15;
    public static int minSpawn = 1;
    public static int maxSpawn = 3;

    public static void load() {
        try {
            if (!Files.exists(PATH)) {
                writeDefaultFile();
                return;
            }

            TomlParseResult toml = Toml.parse(Files.readString(PATH));

            if (toml.hasErrors()) {
                writeDefaultFile();
                return;
            }
            spawnChance = getInt(toml, "general.spawnChance", spawnChance);
            minSpawn= getInt(toml, "general.minSpawn", minSpawn);
            maxSpawn = getInt(toml, "general.maxSpawn", maxSpawn);

        } catch (Exception e) {
            try { writeDefaultFile(); } catch (Exception ignored) {}
        }
    }

    private static int getInt(TomlParseResult toml, String key, int def) {
        Long v = toml.getLong(key);
        if (v == null) return def;
        if (v > Integer.MAX_VALUE) return Integer.MAX_VALUE;
        if (v < Integer.MIN_VALUE) return Integer.MIN_VALUE;
        return v.intValue();
    }

    private static void writeDefaultFile() throws Exception {
        Files.createDirectories(PATH.getParent());

        String content = ""
                + "# ================================\n"
                + "# Potion of Infestation Backport Configuration\n"
                + "# ================================\n\n"
                + "[general]\n"
                + "# Chance (0 - 100) that silverfish spawn when an infested entity is damaged.\n"
                + "spawnChance = " + spawnChance + "\n"
                + "# Minimum number of silverfish that can spawn.\n"
                + "minSpawn = " + minSpawn + "\n"
                + "# Maximum number of silverfish that can spawn.\n"
                + "maxSpawn = " + maxSpawn + "\n";

        Files.writeString(PATH, content);
    }
}
