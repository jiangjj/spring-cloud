package com.jiangjj.licensingservice.events.handlers;

import com.jiangjj.licensingservice.events.CustomerChannels;
import com.jiangjj.licensingservice.events.models.OrganizationChangeModel;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;

@EnableBinding(CustomerChannels.class)
@AllArgsConstructor
public class OrganizationChangeHandler {

    private static final Logger logger = LoggerFactory.getLogger(OrganizationChangeHandler.class);

    @StreamListener("inboundOrgChanges")
    @CacheEvict(value = "organizations", key = "#orgChange.organizationId", condition = "#orgChange.action == 'UPDATE' or #orgChange.action == 'DELETE'")
    public void loggerSink(OrganizationChangeModel orgChange) {
        logger.debug("Received a message of type " + orgChange.getType());
        switch(orgChange.getAction()){
            case "GET":
                logger.debug("Received a GET event from the organization service for organization id {}", orgChange.getOrganizationId());
                break;
            case "SAVE":
                logger.debug("Received a SAVE event from the organization service for organization id {}", orgChange.getOrganizationId());
                break;
            case "UPDATE":
                logger.debug("Received a UPDATE event from the organization service for organization id {}", orgChange.getOrganizationId());
                break;
            case "DELETE":
                logger.debug("Received a DELETE event from the organization service for organization id {}", orgChange.getOrganizationId());
                break;
            default:
                logger.error("Received an UNKNOWN event from the organization service of type {}", orgChange.getType());
                break;

        }
    }
}
