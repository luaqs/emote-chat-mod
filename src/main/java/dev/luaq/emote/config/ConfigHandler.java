package dev.luaq.emote.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;
import lombok.Getter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

public class ConfigHandler<T> {
    public static final Gson PRETTY_GSON = new GsonBuilder().setPrettyPrinting().create();

    @Getter private final File file;
    @Getter private T config;

    public ConfigHandler(File file, T defaultConfig) {
        this.file   = file;
        this.config = defaultConfig;

        // call read by default
        readConfig();
    }

    public void populate() {
        // create the parent folder
        File parentFolder = file.getParentFile();
        //noinspection ResultOfMethodCallIgnored
        parentFolder.mkdirs();

        try {
            String json = PRETTY_GSON.toJson(config);
            Files.write(file.toPath(), json.getBytes(StandardCharsets.UTF_8));
        } catch (JsonIOException | IOException e) {
            // TODO: 20/04/2021 report the failed
        }
    }

    public boolean readConfig() {
        if (!file.exists()) {
            populate();
            return false;
        }

        FileReader reader;
        try {
            reader = new FileReader(file);
        } catch (FileNotFoundException ignored) { // we definitely check
            return false;
        }

        //noinspection unchecked
        config = (T) PRETTY_GSON.fromJson(reader, config.getClass());

        return true;
    }
}
