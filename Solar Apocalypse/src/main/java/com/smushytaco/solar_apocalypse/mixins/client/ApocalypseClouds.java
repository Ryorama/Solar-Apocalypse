package com.smushytaco.solar_apocalypse.mixins.client;

import com.mojang.blaze3d.systems.RenderSystem;
import com.smushytaco.solar_apocalypse.SolarApocalypseClient;
import net.minecraft.client.render.WorldRenderer;
import net.minecraft.client.util.math.MatrixStack;
import org.joml.Matrix4f;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(WorldRenderer.class)
public abstract class ApocalypseClouds {
    @Inject(method = "renderClouds(Lnet/minecraft/client/util/math/MatrixStack;Lorg/joml/Matrix4f;FDDD)V", at = @At(value = "INVOKE", target = "Lcom/mojang/blaze3d/systems/RenderSystem;setShader(Ljava/util/function/Supplier;)V", shift = At.Shift.AFTER))
    private void hookRenderClouds(MatrixStack matrices, Matrix4f projectionMatrix, float tickDelta, double cameraX, double cameraY, double cameraZ, CallbackInfo ci) {
        float[] shaderColor = RenderSystem.getShaderColor();
        shaderColor[3] = 1;
        RenderSystem.setShaderColor(shaderColor[0], shaderColor[1], shaderColor[2], shaderColor[3] * (1.0F - SolarApocalypseClient.INSTANCE.getCloudFade()));
    }
}