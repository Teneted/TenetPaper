package org.teneted.tenet.event;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;

public class RegisterEvents {

    public static Event<RegisterItem> REGISTER_ITEM = EventFactory.createArrayBacked(RegisterEvents.RegisterItem.class, callbacks -> () -> {
        for (RegisterEvents.RegisterItem callback : callbacks) {
            callback.register();
        }
    });

    public static Event<RegisterBlock> REGISTER_BLOCK = EventFactory.createArrayBacked(RegisterEvents.RegisterBlock.class, callbacks -> () -> {
        for (RegisterEvents.RegisterBlock callback : callbacks) {
            callback.register();
        }
    });

    @FunctionalInterface
    public interface RegisterItem {
        void register();
    }

    @FunctionalInterface
    public interface RegisterBlock {
        void register();
    }
}
