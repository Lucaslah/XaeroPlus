package xaeroplus.mixin.client;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import xaero.map.MapProcessor;
import xaero.map.world.MapDimension;
import xaero.map.world.MapWorld;
import xaeroplus.DelegatingHashTable;

import java.util.Hashtable;

@Mixin(value = MapWorld.class, remap = false)
public class MixinMapWorld {

    @Shadow
    private Hashtable<Integer, MapDimension> dimensions;

    @Inject(method = "<init>", at = @At("RETURN"))
    public void init(String mainId, String oldUnfixedMainId, MapProcessor mapProcessor, final CallbackInfo ci) {
        // Hashtable is slow af as every operation is synchronized
        // replace it with our own implementation
        // will still be thread-safe
        // except with iterators - however, the base Xaero code does use synchronization blocks.
        this.dimensions = new DelegatingHashTable<>();
    }

    /**
     * @author rfresh2
     * @reason fast dimension map lookup without synchronization
     */
    @Overwrite
    public MapDimension getDimension(int dimId) {
        return this.dimensions.get(dimId);
    }
}
