package com.grim3212.mc.pack.industry.network;

import java.io.IOException;

import com.grim3212.mc.pack.core.network.AbstractMessage.AbstractServerMessage;
import com.grim3212.mc.pack.industry.inventory.ContainerLocksmithWorkbench;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.relauncher.Side;

public class MessageSetLock extends AbstractServerMessage<MessageSetLock> {

	private String lock;

	public MessageSetLock() {
	}

	public MessageSetLock(String lock) {
		this.lock = lock;
	}

	@Override
	protected void read(PacketBuffer buffer) throws IOException {
		this.lock = buffer.readString(11);
	}

	@Override
	protected void write(PacketBuffer buffer) throws IOException {
		buffer.writeString(this.lock);
	}

	@Override
	public void process(EntityPlayer player, Side side) {

		if (player.openContainer instanceof ContainerLocksmithWorkbench) {
			((ContainerLocksmithWorkbench) player.openContainer).updateLock(lock);
		}
	}

}