package dev.seethroughleaves.client;

import net.fabricmc.api.ClientModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SeeThroughLeavesClient implements ClientModInitializer {
	public static final String MOD_ID = "seethroughleaves";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitializeClient() {
		LOGGER.info("Ignore the Greenz loaded: your crosshair now looks through decorative foliage.");
	}
}
