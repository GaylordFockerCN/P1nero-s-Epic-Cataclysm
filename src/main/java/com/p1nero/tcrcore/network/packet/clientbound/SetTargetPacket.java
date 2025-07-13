package com.p1nero.tcrcore.network.packet.clientbound;

import com.p1nero.dialog_lib.network.packet.BasePacket;
import com.p1nero.tcrcore.mixin.LocalPlayerPatchAccessor;
import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import yesman.epicfight.client.ClientEngine;

public record SetTargetPacket(int id) implements BasePacket {

    @Override
    public void encode(FriendlyByteBuf buf) {
        buf.writeInt(id);
    }

    public static SetTargetPacket decode(FriendlyByteBuf buf){
        return new SetTargetPacket(buf.readInt());
    }

    @Override
    public void execute(Player player) {
        if(Minecraft.getInstance().player != null && Minecraft.getInstance().level != null){
           Entity entity = Minecraft.getInstance().level.getEntity(id);
           if(entity instanceof LivingEntity living){
               ((LocalPlayerPatchAccessor)ClientEngine.getInstance().getPlayerPatch()).setRayTarget(living);
           }
        }
    }
}
