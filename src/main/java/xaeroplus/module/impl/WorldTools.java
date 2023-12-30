package xaeroplus.module.impl;

import net.minecraft.registry.RegistryKey;
import net.minecraft.world.World;
import org.waste.of.time.storage.cache.HotCache;
import xaeroplus.Globals;
import xaeroplus.feature.render.ChunkHighlightProvider;
import xaeroplus.feature.render.ColorHelper;
import xaeroplus.module.Module;
import xaeroplus.settings.XaeroPlusSettingRegistry;
import xaeroplus.util.ChunkUtils;
import xaeroplus.util.WorldToolsHelper;

import static xaeroplus.feature.render.ColorHelper.getColor;

@Module.ModuleInfo()
public class WorldTools extends Module {

    private int worldToolsColor = getColor(0, 255, 0, 100);

    @Override
    public void onEnable() {
        if (!WorldToolsHelper.isWorldToolsPresent()) return;
        Globals.drawManager.registerChunkHighlightProvider(
            this.getClass(),
            new ChunkHighlightProvider(
                this::isChunkDownloaded,
                this::getWorldToolsColor
            )
        );
    }

    @Override
    public void onDisable() {
        Globals.drawManager.unregister(this.getClass());
    }

    public boolean isChunkDownloaded(final int x, final int z, final RegistryKey<World> dimension) {
        return WorldToolsHelper.isDownloading()
            && Globals.getCurrentDimensionId() == ChunkUtils.getActualDimension()
            && HotCache.INSTANCE.isChunkSaved(x, z);
    }

    public int getWorldToolsColor() {
        return worldToolsColor;
    }

    public void setRgbColor(final int color) {
        worldToolsColor = ColorHelper.getColorWithAlpha(color, (int) XaeroPlusSettingRegistry.worldToolsAlphaSetting.getValue());
    }

    public void setAlpha(final float alpha) {
        worldToolsColor = ColorHelper.getColorWithAlpha(worldToolsColor, (int) alpha);
    }
}
