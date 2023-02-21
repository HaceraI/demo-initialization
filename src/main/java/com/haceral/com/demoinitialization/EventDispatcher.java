package com.haceral.com.demoinitialization;

import java.util.HashMap;
import java.util.Map;

public class EventDispatcher {
    private Map<String, IEventHandler> eventHandler = new HashMap<>();

    public EventDispatcher(Builder builder) {
        this.eventHandler = builder.eventHandler;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public Object handle(String reqType, String eventReq) throws Throwable {
        // dec
        String plainEventJsonStr = decryptEvent(eventReq);
        IEventHandler handler = eventHandler.get(reqType);
        handler.handle(plainEventJsonStr);
        return "SUCCESS";
    }

    public static class Builder {
        private Map<String, IEventHandler> eventHandler = new HashMap<>();

        public Builder() {
            this.eventHandler.put("app_ticket", new EventHandlerService.AppTicket() {
                @Override
                public void handle(String event) throws Exception {
                    System.out.println(event);
                }
            });
        }

        public EventDispatcher build() { return new EventDispatcher(this); }

        public Builder onDeviceFault(EventHandlerService.DeviceFaultHandler handler) {
            if (eventHandler.containsKey("device.fault")) {
                throw new RuntimeException("device.fault repeat");
            }
            eventHandler.put("device.fault", handler);
            return this;
        }

        public Builder onDeviceOffline(EventHandlerService.DeviceOfflineHandler handler) {
            if (eventHandler.containsKey("device.offline")) {
                throw new RuntimeException("device.offline repeat");
            }
            eventHandler.put("device.offline", handler);
            return this;
        }
    }

     public String decryptEvent(String cipherEventJsonStr) {
        // Decrypt request...
         return cipherEventJsonStr + " - Dec";
     }
}
