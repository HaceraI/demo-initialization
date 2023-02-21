package com.haceral.com.demoinitialization;

public class EventHandlerService {

    public abstract static class AppTicket implements IEventHandler<String> {
        @Override
        public String getEvent() {
            return "AppTicket";
        }
    }

    public abstract static class DeviceFaultHandler implements IEventHandler<String> {
        @Override
        public String getEvent() {
            return "DeviceFaultHandler";
        }
    }

    public abstract static class DeviceOfflineHandler implements IEventHandler<String> {
        @Override
        public String getEvent() {
            return "DeviceOfflineHandler";
        }
    }
}
