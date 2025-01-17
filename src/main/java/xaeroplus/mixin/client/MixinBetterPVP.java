package xaeroplus.mixin.client;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import xaeroplus.XaeroPlus;

@Mixin(targets = {"xaero.pvp.BetterPVP"}, remap = false)
@Pseudo
public class MixinBetterPVP {

    @Inject(method = "loadCommon", at = @At("HEAD"))
    public void loadCommonInject(final CallbackInfo ci) {
        XaeroPlus.initialize();
    }
}
