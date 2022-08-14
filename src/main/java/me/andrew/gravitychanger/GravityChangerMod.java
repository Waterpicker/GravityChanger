package me.andrew.gravitychanger;

import me.andrew.gravitychanger.command.GravityCommand;
import me.andrew.gravitychanger.config.GravityChangerConfig;
import me.andrew.gravitychanger.item.ModItems;
import me.andrew.gravitychanger.network.Networking;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.GsonConfigSerializer;
import net.minecraft.util.Identifier;
import net.minecraftforge.client.ConfigGuiHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import static me.andrew.gravitychanger.GravityChangerMod.MOD_ID;

@Mod(MOD_ID)
public class GravityChangerMod {
    public static final String MOD_ID = "gravitychanger";
    public static final Identifier CHANNEL_GRAVITY = new Identifier(MOD_ID, "gravity");

    public static GravityChangerConfig config;

    public GravityChangerMod() {
        IEventBus MOD_BUS = FMLJavaModLoadingContext.get().getModEventBus();

        MOD_BUS.addListener(this::commonSetup);
        MinecraftForge.EVENT_BUS.addListener(this::commandRegister);

        ModItems.init(MOD_BUS);
    }

    public void commonSetup(FMLCommonSetupEvent event) {
        AutoConfig.register(GravityChangerConfig.class, GsonConfigSerializer::new);
        config = AutoConfig.getConfigHolder(GravityChangerConfig.class).getConfig();
        Networking.register();
        ModLoadingContext.get().registerExtensionPoint(ConfigGuiHandler.ConfigGuiFactory.class, () -> new ConfigGuiHandler.ConfigGuiFactory(ModMenuIntegration.getModConfigScreenFactory()));
    }

    public void commandRegister(RegisterCommandsEvent event) {
        GravityCommand.register(event.getDispatcher());
    }
}
