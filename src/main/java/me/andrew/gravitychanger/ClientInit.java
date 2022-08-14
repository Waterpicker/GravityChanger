package me.andrew.gravitychanger;

import me.andrew.gravitychanger.accessor.RotatableEntityAccessor;
import me.andrew.gravitychanger.network.Networking;
import net.minecraft.client.MinecraftClient;

public class ClientInit {
    public static void handleUpdatePacket(Networking.GravityUpdate msg) {
        MinecraftClient.getInstance().execute(() -> {
            if(MinecraftClient.getInstance().player == null) return;
            ((RotatableEntityAccessor) MinecraftClient.getInstance().player).gravitychanger$setGravityDirection(msg.gravityDirection(), msg.initialGravity());
        });
    }

//    @Override
//    public void onInitializeClient() {
//        ClientPlayNetworking.registerGlobalReceiver(GravityChangerMod.CHANNEL_GRAVITY, (client, handler, buf, responseSender) -> {
//            Direction gravityDirection = buf.readEnumConstant(Direction.class);
//            boolean initialGravity = buf.readBoolean();
//            client.execute(() -> {
//                if(client.player == null) return;
//
//                ((RotatableEntityAccessor) client.player).gravitychanger$setGravityDirection(gravityDirection, initialGravity);
//            });
//        });
//    }
}
