package com.haceral.com.demoinitialization;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DeviceFaultHandler extends EventHandlerService.DeviceFaultHandler {
    private final EventDispatcher eventDispatcher;
    public DeviceFaultHandler(EventDispatcher eventDispatcher) {
        this.eventDispatcher = eventDispatcher;
    }

    @Override
    public void handle(String event) throws Exception {
        System.out.println(event);

        // circular reference.
        String s = eventDispatcher.decryptEvent(event);
        System.out.println(s);
    }
}
