package com.jiangjj.licensingservice.clients;

import com.jiangjj.licensingservice.configs.RedisConfig;
import com.jiangjj.licensingservice.models.Organization;
import com.jiangjj.licensingservice.utils.ProtostuffRedisSerializer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.redis.DataRedisTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.cache.Cache;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataRedisTest
public class OrganizationServiceClientTest {

    @Autowired
    private RedisConnectionFactory connectionFactory;

    private RedisCacheManager cm;

    @Before
    public void setUp() throws Exception {
        cm = RedisCacheManager.builder(connectionFactory)
                .cacheDefaults(RedisCacheConfiguration.defaultCacheConfig().serializeValuesWith(new ProtostuffRedisSerializer()))
                .build();
    }

    @Test
    public void getOrganizationProtobuf() {
        Cache cache = cm.getCache("protostuffCacheManager");
        Organization organization = new Organization();
        Long id = 1L;
        String name = "name";
        organization.setId(id);
        organization.setName(name);
        String key = "getOrganizationProtobuf#" + id;
        cache.put(key, organization);
        Organization result = cache.get(key, Organization.class);
        assertTrue(organization.equals(result));
    }

    @After
    public void tearDown() throws Exception {
    }

}