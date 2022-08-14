package me.andrew.gravitychanger.network;

import me.andrew.gravitychanger.ClientInit;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Direction;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;

import java.util.function.Supplier;

import static me.andrew.gravitychanger.GravityChangerMod.MOD_ID;

public class Networking {
    private static final String PROTOCOL_VERSION = "1";
    public static final SimpleChannel INSTANCE = NetworkRegistry.newSimpleChannel(
            new Identifier(MOD_ID, "gravity"),
            () -> PROTOCOL_VERSION,
            PROTOCOL_VERSION::equals,
            PROTOCOL_VERSION::equals
    );

    public static void register() {
        INSTANCE.registerMessage(0, GravityUpdate.class, GravityUpdate::encode, GravityUpdate::decode, GravityUpdate::handle);
    }

    public static record GravityUpdate(Direction gravityDirection, boolean initialGravity) {
        public static GravityUpdate decode(PacketByteBuf buf) {
            return new GravityUpdate(buf.readEnumConstant(Direction.class), buf.readBoolean());
        }
        public void encode(PacketByteBuf buf) {
            buf.writeEnumConstant(gravityDirection).writeBoolean(initialGravity);
        }

        public static void handle(GravityUpdate msg, Supplier<NetworkEvent.Context> contextSupplier) {
            contextSupplier.get().enqueueWork(() -> {
                DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> ClientInit.handleUpdatePacket(msg));
            });

            contextSupplier.get().setPacketHandled(true);
        }
    }

}
