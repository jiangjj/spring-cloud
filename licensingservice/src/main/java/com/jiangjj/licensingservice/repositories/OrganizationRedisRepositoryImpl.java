package com.jiangjj.licensingservice.repositories;

import com.jiangjj.licensingservice.models.Organization;
import lombok.AllArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
public class OrganizationRedisRepositoryImpl implements OrganizationRedisRepository{
    private static final String HASH_NAME = "organization";
    private RedisTemplate<Object, Object> redisTemplate;
    @Override
    public void saveOrganization(Organization org) {
        redisTemplate.opsForHash().put(HASH_NAME, org.getId(), org);
    }

    @Override
    public void updateOrganization(Organization org) {
        redisTemplate.opsForHash().put(HASH_NAME, org.getId(), org);
    }

    @Override
    public void deleteOrganization(String organizationId) {
        redisTemplate.opsForHash().delete(HASH_NAME, organizationId);
    }

    @Override
    public Organization findOrganization(String organizationId) {
        return (Organization) redisTemplate.opsForHash().get(HASH_NAME, organizationId);
    }
}
