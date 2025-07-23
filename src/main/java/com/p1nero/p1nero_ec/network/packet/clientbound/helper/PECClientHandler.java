package com.p1nero.p1nero_ec.network.packet.clientbound.helper;

import com.p1nero.p1nero_ec.capability.DataManager;
import com.p1nero.p1nero_ec.capability.PECCapabilityProvider;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class PECClientHandler {

    public static void syncBoolData(String key, boolean isLocked, boolean value) {
        if(Minecraft.getInstance().player != null && Minecraft.getInstance().level != null) {
            LocalPlayer player = Minecraft.getInstance().player;
            if (isLocked) {
                DataManager.putData(player, key + "isLocked", true);
                return;
            }
            DataManager.putData(player, key, value);
            DataManager.putData(player, key + "isLocked", false);
        }
    }

    public static void syncDoubleData(String key, boolean isLocked, double value) {
        if (Minecraft.getInstance().player != null && Minecraft.getInstance().level != null) {
            LocalPlayer player = Minecraft.getInstance().player;
            if (isLocked) {
                DataManager.putData(player, key + "isLocked", true);
                return;
            }
            DataManager.putData(player, key, value);
            DataManager.putData(player, key + "isLocked", false);
        }
    }

    public static void syncStringData(String key, boolean isLocked, String value){
        if (Minecraft.getInstance().player != null && Minecraft.getInstance().level != null) {
            LocalPlayer player = Minecraft.getInstance().player;
            if (isLocked) {
                DataManager.putData(player, key + "isLocked", true);
                return;
            }
            DataManager.putData(player, key, value);
            DataManager.putData(player, key + "isLocked", false);
        }
    }

    public static void syncTCRPlayer(CompoundTag compoundTag) {
        if(Minecraft.getInstance().player != null && Minecraft.getInstance().level != null) {
            PECCapabilityProvider.getTCRPlayer(Minecraft.getInstance().player).loadNBTData(compoundTag);
        }
    }

}
