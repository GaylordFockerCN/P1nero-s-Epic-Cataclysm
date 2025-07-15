package com.p1nero.p1nero_ec.network;

import java.util.function.Supplier;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.network.NetworkEvent;

public interface BasePacket {
    void encode(FriendlyByteBuf var1);

    default boolean handle(Supplier<NetworkEvent.Context> context) {
        ((NetworkEvent.Context)context.get()).enqueueWork(() -> this.execute(((NetworkEvent.Context)context.get()).getSender()));
        return true;
    }

    void execute(Player var1);
}
