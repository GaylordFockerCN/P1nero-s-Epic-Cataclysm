package com.p1nero.p1nero_ec.network.packet.clientbound;

import com.p1nero.p1nero_ec.network.BasePacket;
import com.p1nero.p1nero_ec.network.packet.clientbound.helper.DistHelper;
import com.p1nero.p1nero_ec.network.packet.clientbound.helper.PECClientHandler;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Player;

public record SyncPECPlayerPacket(CompoundTag data) implements BasePacket {
    public static SyncPECPlayerPacket decode(FriendlyByteBuf buf) {
        return new SyncPECPlayerPacket(buf.readNbt());
    }

    @Override
    public void encode(FriendlyByteBuf buf) {
        buf.writeNbt(data);
    }

    @Override
    public void execute(Player playerEntity) {
        DistHelper.runClient(() -> () -> {
            PECClientHandler.syncTCRPlayer(data);
        });
    }
}