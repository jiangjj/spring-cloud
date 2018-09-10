package com.jiangjj.licensingservice.utils;

import com.google.protobuf.InvalidProtocolBufferException;
import com.jiangjj.organizationservice.models.OrganizationProto;
import org.springframework.data.redis.serializer.*;

import java.nio.ByteBuffer;

public class ProtobufRedisSerializer implements RedisSerializationContext.SerializationPair<OrganizationProto.Organization> {

    @Override
    public RedisElementReader<OrganizationProto.Organization> getReader() {
        return new RedisElementReader<OrganizationProto.Organization>() {
            @Override
            public OrganizationProto.Organization read(ByteBuffer byteBuffer) {
                try {
                    return OrganizationProto.Organization.parseFrom(byteBuffer);
                } catch (InvalidProtocolBufferException e) {
                    e.printStackTrace();
                }
                return OrganizationProto.Organization.getDefaultInstance();
            }
        };
    }

    @Override
    public RedisElementWriter<OrganizationProto.Organization> getWriter() {
        return new RedisElementWriter<OrganizationProto.Organization>() {
            @Override
            public ByteBuffer write(OrganizationProto.Organization organization) {
                return ByteBuffer.wrap(organization.toByteArray());
            }
        };
    }
}
