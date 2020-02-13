package tk.deltawolf.sea.container;

import com.google.common.collect.Sets;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.*;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IWorldPosCallable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;
import net.minecraftforge.items.wrapper.InvWrapper;
import tk.deltawolf.sea.items.HookItem;
import tk.deltawolf.sea.items.ReelItem;
import tk.deltawolf.sea.items.RodItem;
import tk.deltawolf.sea.lists.BlockList;
import tk.deltawolf.sea.lists.ItemList;

import java.util.Set;

import static tk.deltawolf.sea.lists.ContainerList.TACKLEBENCH_CONTAINER;

public class TackleBenchContainer extends Container {
	private Slot slotExtra;
	private Slot slotRod3;
	private Slot slotRod2;
	private Slot slotRod1;
	private Slot slotReel;
	private Slot slotHook;
	private Slot slotCombined;
	//private final IInventory field_217040_j;
	//private final IInventory field_217041_k;
	private TileEntity tileEntity;
	private PlayerEntity player;
	private IItemHandler playerInventory;

	private static final Set<Item> AcceptedRod = Sets.newHashSet(Items.STICK, Items.BAMBOO, Items.IRON_INGOT);
	private static final Set<Item> AcceptedHook = Sets.newHashSet(ItemList.hook, ItemList.gold_hook);
	private static final Set<Item> AcceptedReel = Sets.newHashSet(ItemList.reel);//in the future check tags in data
	private static final Set<Item> AcceptedExtra = Sets.newHashSet(ItemList.pebble);//item.isIn(AquacultureAPI.Tags.TACKLE_BOX)
	private static final Set<Item> AcceptedCombined = Sets.newHashSet(ItemList.fishing_rod);//should only be mod fishing rod

	public TackleBenchContainer(int windowId, World world, BlockPos pos, PlayerInventory inventory, PlayerEntity player) {
		super(TACKLEBENCH_CONTAINER, windowId);
		tileEntity = world.getTileEntity(pos);
		this.player = player;
		this.playerInventory = new InvWrapper(inventory);

		tileEntity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).ifPresent(h -> {
			slotExtra = addSlot(new SlotItemHandler(h, 0 , 26, 9) {
				public boolean isItemValid(ItemStack stack) {
					for (Item item:AcceptedExtra) {
						if (stack.getItem() == item) {
							return true;
						}
					}
					return false;
				}
			});
			slotRod3 = addSlot(new SlotItemHandler(h, 1 , 70, 9) {
				public boolean isItemValid(ItemStack stack) {
					for (Item item:AcceptedRod) {
						if (stack.getItem() == item) {
							return true;
						}
					}
					return false;
				}
			});
			slotRod2 = addSlot(new SlotItemHandler(h, 2 , 48, 31) {
				public boolean isItemValid(ItemStack stack) {
					for (Item item:AcceptedRod) {
						if (stack.getItem() == item) {
							return true;
						}
					}
					return false;
				}
			});
			slotRod1 = addSlot(new SlotItemHandler(h, 3 , 26, 53) {
				public boolean isItemValid(ItemStack stack) {
					for (Item item:AcceptedRod) {
						if (stack.getItem() == item) {
							return true;
						}
					}
					return false;
				}
			});
			slotReel = addSlot(new SlotItemHandler(h, 4 , 48, 53) {
				public boolean isItemValid(ItemStack stack) {
					for (Item item:AcceptedReel) {
						if (stack.getItem() == item) {
							return true;
						}
					}
					return false;
				}
			});
			slotHook = addSlot(new SlotItemHandler(h, 5 , 70, 53) {
				public boolean isItemValid(ItemStack stack) {
					for (Item item:AcceptedHook) {
						if (stack.getItem() == item) {
							return true;
						}
					}
					return false;
				}
			});
			slotCombined = addSlot(new SlotItemHandler(h, 6 , 134, 31) {
				public boolean isItemValid(ItemStack stack) {
					for (Item item:AcceptedCombined) {
						if (stack.getItem() == item) {
							return true;
						}
					}
					return false;
				}
			});
			//addSlot(new SlotItemHandler(h, 0 , 26, 9)); //extra
			//addSlot(new SlotItemHandler(h, 1, 70, 9)); //rod 3
			//addSlot(new SlotItemHandler(h, 2, 48, 31)); //rod 2
			//addSlot(new SlotItemHandler(h, 3, 26, 53)); //rod 1
			//addSlot(new SlotItemHandler(h, 4, 48, 53)); //reel
			//addSlot(new SlotItemHandler(h, 5, 70, 53)); //hook
			//addSlot(new SlotItemHandler(h, 6, 134, 31)); //combined
		});
		layoutPlayerInventory(8, 84); //10,70.. 7,83
	}

	@Override
	public boolean canInteractWith(PlayerEntity playerIn) {
		return isWithinUsableDistance(IWorldPosCallable.of(tileEntity.getWorld(), tileEntity.getPos()), player, BlockList.tackle_bench);
	}

	@Override
	public ItemStack transferStackInSlot(PlayerEntity player, int index) {
		//TODO crashes when shift clicking out of container
		//TODO check items by tag as well as instanceof
		Slot slot = this.inventorySlots.get(index);
		ItemStack itemStack = ItemStack.EMPTY;
		//return slot != null ? slot.getStack() : ItemStack.EMPTY;

		if (slot != null && slot.getHasStack()) {
			ItemStack stack = slot.getStack();
			itemStack = stack.copy();
			if (index == slotCombined.slotNumber) {
				if (!this.mergeItemStack(stack, 7, 43, true)) {
					return ItemStack.EMPTY;
				}
				slot.onSlotChange(stack, itemStack);
			} else if (index != slotHook.slotNumber && index != slotReel.slotNumber && index != slotRod1.slotNumber && index != slotRod2.slotNumber && index != slotRod3.slotNumber && index != slotExtra.slotNumber) {
				if (stack.getItem() instanceof HookItem) {
					if (!this.mergeItemStack(stack, this.slotHook.slotNumber, this.slotHook.slotNumber + 1, false)) {
						return ItemStack.EMPTY;
					}
				} else if (stack.getItem() instanceof ReelItem) {
					if (!this.mergeItemStack(stack, this.slotReel.slotNumber, this.slotReel.slotNumber + 1, false)) {
						return ItemStack.EMPTY;
					}
				} else if (stack.getItem() instanceof RodItem) {
					if (!this.mergeItemStack(stack, this.slotRod1.slotNumber, this.slotRod1.slotNumber + 1, false)) {
						return ItemStack.EMPTY;
					} else if (!this.mergeItemStack(stack, this.slotRod2.slotNumber, this.slotRod2.slotNumber + 1, false)) {
						return ItemStack.EMPTY;
					} else if (!this.mergeItemStack(stack, this.slotRod3.slotNumber, this.slotRod3.slotNumber + 1, false)) {
						return ItemStack.EMPTY;
					}
				}  else if (!this.mergeItemStack(stack, 7, 43, false)) {
					return ItemStack.EMPTY;
				}
				if (stack.isEmpty()) {
					slot.putStack(ItemStack.EMPTY);
				} else {
					slot.onSlotChanged();
				}

				if (stack.getCount() == itemStack.getCount()) {
					return ItemStack.EMPTY;
				}

				slot.onTake(player, stack);
			}
		}
		return itemStack;
//		} else {
//			return ItemStack.EMPTY;
//		}
	}

	private int addSlotRange(IItemHandler handler, int index, int x, int y, int amount, int dx) {
		for (int i = 0; i < amount; i++) {
			addSlot(new SlotItemHandler(handler, index, x, y));
			x += dx;
			index++;
		}
		return index;
	}
	private int addSlotBox(IItemHandler handler, int index, int x, int y, int horAmount, int dx, int verAmount, int dy) {
		for (int j = 0; j < verAmount; j++) {
			index = addSlotRange(handler, index, x, y, horAmount, dx);
			y += dy;
		}
		return index;
	}

	private void layoutPlayerInventory(int leftCol, int topRow) {
		//Player Inventory
		addSlotBox(playerInventory, 9, leftCol, topRow, 9,18,3, 18);

		//Hotbar
		topRow +=58;
		addSlotRange(playerInventory, 0, leftCol, topRow, 9, 18);
	}
}
