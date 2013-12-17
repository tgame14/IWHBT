package iwhbt.core.network;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.util.ChatMessageComponent;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteStreams;

import cpw.mods.fml.common.network.IPacketHandler;
import cpw.mods.fml.common.network.Player;

public class PacketHandler implements IPacketHandler {

	@Override
	public void onPacketData(INetworkManager manager,
			Packet250CustomPayload packet, Player player) {
		ByteArrayDataInput reader = ByteStreams.newDataInput(packet.data);

		EntityPlayer entityplayer = (EntityPlayer) player;

		byte packetId = reader.readByte();

		switch (packetId) {

		case 0:

			String username = reader.readUTF();
			String source = reader.readUTF();
			float amount = reader.readFloat();

			entityplayer.sendChatToPlayer(ChatMessageComponent.createFromText(username
					+ " took " + amount / 2 + " hearts from " + source));

		}

	}
}
