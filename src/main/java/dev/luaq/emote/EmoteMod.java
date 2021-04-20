package dev.luaq.emote;

import dev.luaq.emote.config.ConfigHandler;
import dev.luaq.emote.config.EmoteConfig;
import lombok.Getter;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = "emote", version = "1.0")
public class EmoteMod {
    @Mod.Instance
    public static EmoteMod instance;

    @Getter private ConfigHandler<EmoteConfig> config;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        // create the config handler
        config = new ConfigHandler<>(event.getSuggestedConfigurationFile(), new EmoteConfig());

        // save it on shutdown
        Runtime.getRuntime().addShutdownHook(new Thread(() -> config.populate()));
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
        
    }
}
