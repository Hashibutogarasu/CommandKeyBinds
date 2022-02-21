package com.Hashibutogarasu.commandkeybind.Configs;

import com.Hashibutogarasu.commandkeybind.CommandKeyBindModClient;
import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.TranslatableText;

import java.util.concurrent.atomic.AtomicReference;

public class CommandKeyBindsConfigScreenFactory{

    public static CommandkeybindConfig commandconfig;
    public static AtomicReference<String> currentValue;
    public static AtomicReference<Boolean> ShowtoChat;

    public CommandKeyBindsConfigScreenFactory() { }

    public static Screen genConfig(Screen parent){

        try{
            commandconfig = CommandkeybindConfig.load();
            currentValue = new AtomicReference<>(commandconfig.configcommandvalue);
            ShowtoChat = new AtomicReference<>(commandconfig.showtochat);
        }
        catch (Exception ignored){

        }

        ConfigBuilder builder = ConfigBuilder.create()
                .setParentScreen(new KeyConfigScreen(new TranslatableText("gui.commandkeybind.title")))
                .setTitle(new TranslatableText("gui.commandkeybind.title"));

        builder.setSavingRunnable(() -> {
            try{
                commandconfig.configcommandvalue = currentValue.get();
                commandconfig.showtochat = ShowtoChat.get();
                commandconfig.save();
            }
            catch(Exception ignored){

            }
        });

        ConfigCategory general = builder.getOrCreateCategory(new TranslatableText("category.commandkeybind.general"));
        builder.setParentScreen(parent);
        ConfigEntryBuilder entryBuilder = builder.entryBuilder();

        general.addEntry(entryBuilder.startStrField(new TranslatableText("option.commandkeybind.command"), commandconfig.configcommandvalue)
                .setDefaultValue(commandconfig.configcommandvalue) // Recommended: Used when user click "Reset"
                .setSaveConsumer(newValue -> currentValue.set(newValue)) // Recommended: Called when user save the config
                .build()); // Builds the option entry for cloth config

        general.addEntry(entryBuilder.startBooleanToggle(new TranslatableText("option.commandkeybind.shotochat"), commandconfig.showtochat)
                .setDefaultValue(commandconfig.showtochat) // Recommended: Used when user click "Reset"
                .setSaveConsumer(newValue -> ShowtoChat.set(newValue)) // Recommended: Called when user save the config
                .build()); // Builds the option entry for cloth config

        return builder.build();
    }
}
