package com.Hashibutogarasu.commandkeybind;

import com.Hashibutogarasu.commandkeybind.Configs.CommandKeyBindsConfigScreenFactory;
import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;

public class ModmenuIntegration implements ModMenuApi {
    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return CommandKeyBindsConfigScreenFactory::genConfig;
    }
}
