package com.jiangjj.organizationservice.events.sources;

import com.jiangjj.organizationservice.events.models.OrganizationChangeModel;
import com.jiangjj.organizationservice.utils.UserContext;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class SimpleSourceBean {
    private Source source;

    private static final Logger logger = LoggerFactory.getLogger(SimpleSourceBean.class);

    public void publishOrgChange(String action,Long  orgId) {
        logger.debug("Sending RabbitMQ message {} for Organization Id: {}", action, orgId);
        OrganizationChangeModel changeModel = new OrganizationChangeModel(OrganizationChangeModel.class.getTypeName(),
                action, orgId, UserContext.getCorrelationId());
        source.output().send(MessageBuilder.withPayload(changeModel).build());
    }
}
