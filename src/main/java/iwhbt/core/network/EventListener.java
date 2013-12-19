package iwhbt.core.network;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EntityDamageSource;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import cpw.mods.fml.common.FMLCommonHandler;

public class EventListener {

	@ForgeSubscribe
	public void PlayerAttacked(LivingHurtEvent event) {
		if (FMLCommonHandler.instance().getEffectiveSide().isServer()) {

			if (event.entityLiving instanceof EntityPlayer) {
				EntityPlayer player = (EntityPlayer) event.entityLiving;

				if (event.source.getDamageType().equalsIgnoreCase("player")) {
					EntityDamageSource playerHit = (EntityDamageSource) event.source;
					EntityPlayer entity = (EntityPlayer) playerHit.getEntity();

					PacketDistributer.sendInfoToClients(player.username,
							entity.username, event.ammount);
				}

				else if (event.source.getDamageType().equalsIgnoreCase("mob")) {
					EntityDamageSource mobHit = (EntityDamageSource) event.source;
					PacketDistributer.sendInfoToClients(player.username, mobHit
							.getEntity().getTranslatedEntityName(),
							event.ammount);
				}

				else {
					PacketDistributer.sendInfoToClients(player.username,
							event.source.getDamageType(), event.ammount);
				}

			}

		}
	}

}
