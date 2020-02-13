package tk.deltawolf.sea.blocks.tiles;

import com.google.common.collect.Sets;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import tk.deltawolf.sea.container.TackleBenchContainer;
import tk.deltawolf.sea.lists.ItemList;
import tk.deltawolf.sea.lists.TileEntityList;
import tk.deltawolf.sea.util.Util;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Set;

public class TackleBenchTile extends TileEntity implements  ITickableTileEntity, INamedContainerProvider {
	//INamedContainerProvider,

	private LazyOptional<IItemHandler> handler = LazyOptional.of(this::createHandler);

	private static final Set<Item> AcceptedRod = Sets.newHashSet(Items.STICK, Items.BAMBOO, Items.IRON_INGOT);
	private static final Set<Item> AcceptedHook = Sets.newHashSet(ItemList.hook, ItemList.gold_hook);
	private static final Set<Item> AcceptedReel = Sets.newHashSet(ItemList.reel);
	private static final Set<Item> AcceptedExtra = Sets.newHashSet(ItemList.pebble);
	private static final Set<Item> AcceptedCombined = Sets.newHashSet(ItemList.fishing_rod);

	public TackleBenchTile(){
		super(TileEntityList.TACKLEBENCH_TILE);
		//inventoryTitle = "gui.toolforge.name";
	}
	@Override
	public void tick(){
//		if (world.isRemote) {
//			Util.Log().info("Tile Entity Tick"); //tick on client only
//		}
	}

	public <T>LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
		if (cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
			return LazyOptional.of(() -> (T)createHandler());
		}
		return super.getCapability(cap, side);
	}

	/*@Override
	public ITextComponent getDisplayName() {
		return new StringTextComponent(getType().getRegistryName().getPath());
	}*/

	@SuppressWarnings("unchecked")
	@Override
	public void read(CompoundNBT tag) {
		CompoundNBT invTag = tag.getCompound("inv");
		handler.ifPresent(h -> ((INBTSerializable<CompoundNBT>) h).deserializeNBT(invTag));
		super.read(tag);
	}

	@SuppressWarnings("unchecked")
	@Override
	public CompoundNBT write(CompoundNBT tag) {
		handler.ifPresent(h -> {
			CompoundNBT compound = ((INBTSerializable<CompoundNBT>) h).serializeNBT();
			tag.put("inv", compound);
		});
		return super.write(tag);
	}

	private IItemHandler createHandler() {//may need null check
		return new ItemStackHandler(7) {

			@Override
			protected void onContentsChanged(int slot) {
				markDirty();
			}

			@Override
			public boolean isItemValid(int slot, @Nonnull ItemStack stack) {
				if (slot == 0) {
					for (Item item:AcceptedExtra) {
						if (stack.getItem() == item) {
							return true;
						}
					}
					return false;
				} else if (slot >= 1 && slot <=3) {
					for (Item item:AcceptedRod) {
						if (stack.getItem() == item) {
							return true;
						}
					}
					return false;
				} else if (slot == 4) {
					for (Item item:AcceptedReel) {
						if (stack.getItem() == item) {
							return true;
						}
					}
					return false;
				} else if (slot == 5) {
					for (Item item:AcceptedHook) {
						if (stack.getItem() == item) {
							return true;
						}
					}
					return false;
				} else if (slot == 6) {
					for (Item item:AcceptedCombined) {
						if (stack.getItem() == item) {
							return true;
						}
					}
					return false;
				} else {
					Util.Log().warn("Invalid slot number" + slot);
					return false;
				}
			}

			@Nonnull
			@Override
			public ItemStack insertItem(int slot, @Nonnull ItemStack stack, boolean simulate) {
				if (stack.getItem() == Items.DIAMOND) {
					return stack;
				}
				return super.insertItem(slot, stack, simulate);
			}
		};
	}

	@Override
	public ITextComponent getDisplayName() {
		return new StringTextComponent(getType().getRegistryName().getPath());
	}

	@Nullable
	@Override
	public Container createMenu(int i, PlayerInventory playerInventory, PlayerEntity playerEntity) {
		return new TackleBenchContainer(i, world, pos, playerInventory, playerEntity);
	}
}
