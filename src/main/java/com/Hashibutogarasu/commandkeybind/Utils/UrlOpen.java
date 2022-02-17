package com.Hashibutogarasu.commandkeybind;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.util.Util;

public class UrlOpen {
    public void OpenUrl(boolean openInBrowser,String uri){
        Screen beforescreen = MinecraftClient.getInstance().currentScreen;

        if (openInBrowser) {
            Util.getOperatingSystem().open(uri);
        }
        MinecraftClient.getInstance().setScreen(beforescreen);
    }
}
