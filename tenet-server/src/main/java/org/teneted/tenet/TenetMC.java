package org.teneted.tenet;

import net.fabricmc.fabric.impl.event.interaction.InteractionEventsRouter;
import net.fabricmc.fabric.impl.tag.convention.v2.TranslationConventionLogWarnings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TenetMC {

    public static final Logger LOGGER =
            LoggerFactory.getLogger("TenetModLoader");

    public static void init() {
        LOGGER.info("TenetModLoader is loading...");
        InteractionEventsRouter.onInitialize();
        TranslationConventionLogWarnings.onInitialize();
    }
}
