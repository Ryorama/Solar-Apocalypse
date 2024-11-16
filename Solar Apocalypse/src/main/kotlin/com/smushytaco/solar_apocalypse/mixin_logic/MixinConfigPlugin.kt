package com.smushytaco.solar_apocalypse.mixin_logic;

import net.fabricmc.loader.api.FabricLoader
import org.objectweb.asm.tree.ClassNode
import org.spongepowered.asm.mixin.extensibility.IMixinConfigPlugin
import org.spongepowered.asm.mixin.extensibility.IMixinInfo
class MixinConfigPlugin: IMixinConfigPlugin {
    override fun onLoad(mixinPackage: String) {}
    override fun getRefMapperConfig() = ""
    override fun shouldApplyMixin(targetClassName: String, mixinClassName: String): Boolean {
        return if (targetClassName == "me.jellysquid.mods.sodium.client.render.immediate.CloudRenderer" && FabricLoader.getInstance().isModLoaded("sodium")) {
            true
        } else if (mixinClassName == "com.smushytaco.solar_apocalypse.mixins.client.ApocalypseClouds" && FabricLoader.getInstance().isModLoaded("sodium")) {
            false
        } else {
            true
        }
    }

    override fun acceptTargets(myTargets: Set<String>, otherTargets: Set<String>) {}
    override fun getMixins() = listOf<String>()
    override fun preApply(targetClassName: String, targetClass: ClassNode, mixinClassName: String, mixinInfo: IMixinInfo) {}
    override fun postApply(targetClassName: String, targetClass: ClassNode, mixinClassName: String, mixinInfo: IMixinInfo) {}
}