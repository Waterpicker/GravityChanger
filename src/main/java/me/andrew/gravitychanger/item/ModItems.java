package me.andrew.gravitychanger.item;

import me.andrew.gravitychanger.GravityChangerMod;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Direction;
import net.minecraft.util.registry.Registry;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public abstract class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(Registry.ITEM_KEY, "gravitychanger");

    public static final RegistryObject<GravityChangerItem> GRAVITY_CHANGER_DOWN = ITEMS.register("gravity_changer_down", () -> new GravityChangerItem(new Item.Settings().group(ItemGroup.TOOLS).maxCount(1), Direction.DOWN));
    public static final RegistryObject<Item> GRAVITY_CHANGER_UP = ITEMS.register("gravity_changer_up", () -> new GravityChangerItem(new Item.Settings().group(ItemGroup.TOOLS).maxCount(1), Direction.UP));
    public static final RegistryObject<Item> GRAVITY_CHANGER_NORTH = ITEMS.register("gravity_changer_north", () -> new GravityChangerItem(new Item.Settings().group(ItemGroup.TOOLS).maxCount(1), Direction.NORTH));
    public static final RegistryObject<Item> GRAVITY_CHANGER_SOUTH = ITEMS.register("gravity_changer_south", () -> new GravityChangerItem(new Item.Settings().group(ItemGroup.TOOLS).maxCount(1), Direction.SOUTH));
    public static final RegistryObject<Item> GRAVITY_CHANGER_WEST = ITEMS.register("gravity_changer_west", () -> new GravityChangerItem(new Item.Settings().group(ItemGroup.TOOLS).maxCount(1), Direction.WEST));
    public static final RegistryObject<Item> GRAVITY_CHANGER_EAST = ITEMS.register("gravity_changer_east", () -> new GravityChangerItem(new Item.Settings().group(ItemGroup.TOOLS).maxCount(1), Direction.EAST));

    public static void init(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
