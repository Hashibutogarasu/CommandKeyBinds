package com.Hashibutogarasu.commandkeybind.Configs;

import com.Hashibutogarasu.commandkeybind.CommandKeyBindModClient;
import com.moandjiezana.toml.Toml;
import com.moandjiezana.toml.TomlWriter;
import net.fabricmc.loader.api.FabricLoader;

import java.io.File;
import java.io.IOException;

public final class CommandkeybindConfig {

    private transient File file;
    public String configcommandvalue = "";
    public boolean showtochat = true;

    private CommandkeybindConfig() { }

    public static CommandkeybindConfig load() {
        File file = new File(
                FabricLoader.getInstance().getConfigDir().toString(),
                CommandKeyBindModClient.MOD_ID + ".toml"
        );

        CommandkeybindConfig config;
        if (file.exists()) {
            Toml configTOML = new Toml().read(file);
            config = configTOML.to(CommandkeybindConfig.class);
            config.file = file;
        } else {
            config = new CommandkeybindConfig();
            config.file = file;
            config.save();
        }
        return config;
    }

    public void save() {
        TomlWriter writer = new TomlWriter();
        try {
            writer.write(this, file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
