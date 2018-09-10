package com.jiangjj.organizationservice.configs;

import com.alibaba.druid.pool.DruidDataSource;
import io.shardingsphere.core.api.config.MasterSlaveRuleConfiguration;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@ConfigurationProperties(prefix = "sharding")
@Data
public class ShardingJdbcConfiguration {
    private Map<String, DruidDataSource> dataSources = new HashMap<>();
    private MasterSlaveRuleConfiguration masterSlaveRule;
}
