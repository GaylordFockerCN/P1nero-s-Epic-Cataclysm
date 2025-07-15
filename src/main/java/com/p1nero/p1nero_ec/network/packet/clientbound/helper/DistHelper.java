package com.p1nero.p1nero_ec.network.packet.clientbound.helper;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;

import java.util.function.Supplier;

public class DistHelper {
    public static void runClient(Supplier<Runnable> runnable) {
        DistExecutor.unsafeRunWhenOn(Dist.CLIENT, runnable);
    }

    public static void runServer(Supplier<Runnable> runnable) {
        DistExecutor.unsafeRunWhenOn(Dist.DEDICATED_SERVER, runnable);
    }
}