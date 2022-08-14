package me.andrew.gravitychanger;

import me.andrew.gravitychanger.config.GravityChangerConfig;
import me.shedaniel.autoconfig.AutoConfig;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;

import java.util.function.BiFunction;

public class ModMenuIntegration {
    public static BiFunction<MinecraftClient, Screen,Screen> getModConfigScreenFactory() {
        return (minecraftClient, parent) -> AutoConfig.getConfigScreen(GravityChangerConfig.class, parent).get();
    }
}
