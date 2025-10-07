package com.p1nero.p1nero_ec.network.packet.clientbound;

import com.merlin204.avalon.particle.AvalonParticles;
import com.p1nero.p1nero_ec.network.BasePacket;
import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;

/**
 * 手动加残影，server level的sendParticle发到客户端会暗改参数
 */
public record AddAvlEntityAfterImageParticle(int id) implements BasePacket {

    public static AddAvlEntityAfterImageParticle decode(FriendlyByteBuf buf) {
        return new AddAvlEntityAfterImageParticle(buf.readInt());
    }

    @Override
    public void encode(FriendlyByteBuf buf) {
        buf.writeInt(id);
    }

    @Override
    public void execute(Player player) {
        if (Minecraft.getInstance().player != null && Minecraft.getInstance().level != null) {
            Entity entity = Minecraft.getInstance().level.getEntity(id);
            if (entity != null) {
                Minecraft.getInstance().level.addParticle(AvalonParticles.AVALON_ENTITY_AFTER_IMAGE.get(), entity.getX(), entity.getY(), entity.getZ(), Double.longBitsToDouble(entity.getId()), 0.0, 0.0);
            }
        }
    }
}
