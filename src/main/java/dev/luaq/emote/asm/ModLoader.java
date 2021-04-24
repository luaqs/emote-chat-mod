package dev.luaq.emote.asm;

import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin;

import java.util.Map;

public class ModLoader implements IFMLLoadingPlugin {
    @Override
    public String[] getASMTransformerClass() {
        // return array of class names
        return new String[] {

        };
    }

    @Override
    public String getModContainerClass() {
        return null;
    }

    @Override
    public String getSetupClass() {
        return null;
    }

    @Override
    public void injectData(Map<String, Object> data) { }

    @Override
    public String getAccessTransformerClass() {
        return null;
    }
}
