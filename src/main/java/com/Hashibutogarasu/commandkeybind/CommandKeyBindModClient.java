package com.Hashibutogarasu.commandkeybind;

import com.Hashibutogarasu.commandkeybind.Configs.CommandKeyBindsConfigScreenFactory;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.text.TranslatableText;
import org.lwjgl.glfw.GLFW;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;

@Environment(EnvType.CLIENT)
public class CommandKeyBindModClient implements ClientModInitializer {
    public static final Logger LOGGER = LoggerFactory.getLogger("commandkeybind");
    public static String MOD_ID = "commandkeybind";
    @Override
    public void onInitializeClient() {

        KeyBinding executecommandkeybind = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.commandkeybind.executecommand", // The translation key of the keybinding's name
                InputUtil.Type.KEYSYM, // The type of the keybinding, KEYSYM for keyboard, MOUSE for mouse.
                GLFW.GLFW_KEY_BACKSLASH, // The keycode of the key
                "category.commandkeybind.keybinds" // The translation key of the keybinding's category.
        ));


        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (executecommandkeybind.wasPressed()) {
                String executecommand = CommandKeyBindsConfigScreenFactory.commandconfig.configcommandvalue;
                try {
                    boolean showchat = CommandKeyBindsConfigScreenFactory.commandconfig.showtochat;

                    if (!(executecommand.isEmpty() || executecommand.isBlank() || executecommand.equals("/"))) {
                        assert client.player != null;
                        client.player.sendChatMessage(executecommand);
                        if (showchat) {
                            if (executecommand.length() >= 10) {
                                client.player.sendSystemMessage(new TranslatableText("commandkeybind.systemmessage.success", executecommand.substring(0, 10) + "..."), UUID.randomUUID());
                            } else {
                                client.player.sendSystemMessage(new TranslatableText("commandkeybind.systemmessage.success", executecommand), UUID.randomUUID());
                            }
                        }
                    } else {
                        assert client.player != null;
                        if (showchat) {
                            if (executecommand.length() >= 10) {
                                client.player.sendSystemMessage(new TranslatableText("commandkeybind.systemmessage.issueerror", executecommand.substring(0, 10) + "..."), UUID.randomUUID());
                            } else {
                                client.player.sendSystemMessage(new TranslatableText("commandkeybind.systemmessage.issueerror", executecommand), UUID.randomUUID());
                            }
                        }
                    }
                }
                catch (Exception ignored){

                }
            }
        });
    }
}