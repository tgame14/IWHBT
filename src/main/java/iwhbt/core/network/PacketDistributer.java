package iwhbt.core.network;

import iwhbt.core.ModInfo;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import cpw.mods.fml.common.network.PacketDispatcher;

public class PacketDistributer {

	public static void sendInfoToClients(String username, String damage,
			float amount) {
		ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
		DataOutputStream dataStream = new DataOutputStream(byteStream);

		try {
			dataStream.writeByte((byte) 0);
			
			dataStream.writeUTF(username);
			dataStream.writeUTF(damage);
			dataStream.writeFloat(amount);

			PacketDispatcher.sendPacketToAllPlayers(PacketDispatcher.getPacket(
					ModInfo.CHANNEL, byteStream.toByteArray()));

		} catch (IOException ex) {
			System.err.append("Failed To send info to clients");
			ex.printStackTrace();

		}
	}

}
