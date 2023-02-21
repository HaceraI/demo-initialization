package com.haceral.com.demoinitialization;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EventDispatcherFactory {
    // @Autowired
    private DeviceFaultHandler deviceFaultHandler;

    public final EventDispatcher EVENT_DISPATCHER = EventDispatcher.newBuilder()
            .onDeviceFault(new EventHandlerService.DeviceFaultHandler() {
                @Override
                public void handle(String event) throws Exception {
                    System.out.println(event);
                }
            }).onDeviceOffline(new EventHandlerService.DeviceOfflineHandler() {
                @Override
                public void handle(String event) throws Exception {
                    System.out.println(event);
                    String decryptEventStr = EVENT_DISPATCHER.decryptEvent(event);
                    System.out.println("dec: " + decryptEventStr);

                }
            })
            .build();

    @Bean
    public EventDispatcher builderEventDispatcher() {
        final EventDispatcher eventDispatcher = EventDispatcher.newBuilder()
                // circular reference.
                // .onDeviceFault(deviceFaultHandler)
                .onDeviceFault(new EventHandlerService.DeviceFaultHandler() {
                    @Override
                    public void handle(String event) throws Exception {
                        System.out.println(event);
                    }
                })
                .onDeviceOffline(new EventHandlerService.DeviceOfflineHandler() {
                    @Override
                    public void handle(String event) throws Exception {
                        System.out.println(event);

                        // Error
                         String decryptEventStr = eventDispatcher.decryptEvent(event);
                    }
                })
                .build();
        return eventDispatcher;
    }
}
