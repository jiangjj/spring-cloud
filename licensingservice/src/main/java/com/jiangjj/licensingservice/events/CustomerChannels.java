package com.jiangjj.licensingservice.events;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface CustomerChannels {
    @Input("inboundOrgChanges")
    SubscribableChannel orgs();
}
