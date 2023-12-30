package xaeroplus.util;

import org.waste.of.time.manager.CaptureManager;
import xaeroplus.XaeroPlus;

public class WorldToolsHelper {
    private static boolean isWorldToolsPresent = false;
    private static boolean checked = false;

    public static boolean isWorldToolsPresent() {
        // todo: check the HotCache method for accessing saved chunks we use instead of just class being present
        if (!checked) {
            try {
                Class.forName("org.waste.of.time.WorldTools");
                XaeroPlus.LOGGER.info("Found WorldTools. Enabling WorldTools support.");
                isWorldToolsPresent = true;
            } catch (final Throwable e) {
                XaeroPlus.LOGGER.info("WorldTools not found. Disabling WorldTools support.");
                isWorldToolsPresent = false;
            }
            checked = true;
        }
        return isWorldToolsPresent;
    }

    public static boolean isDownloading() {
        return CaptureManager.INSTANCE.getCapturing();
    }
}
