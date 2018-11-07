package com.jiangjj.licensingservice.utils;

import com.jiangjj.licensingservice.models.Organization;
import org.springframework.data.redis.serializer.RedisElementReader;
import org.springframework.data.redis.serializer.RedisElementWriter;
import org.springframework.data.redis.serializer.RedisSerializationContext;

import java.nio.ByteBuffer;

public class ProtostuffRedisSerializer implements RedisSerializationContext.SerializationPair<Organization> {
    @Override
    public RedisElementReader<Organization> getReader() {
        return byteBuffer -> ProtoStuffUtil.deserialize(byteBuffer.array(), Organization.class);
    }

    @Override
    public RedisElementWriter<Organization> getWriter() {
        return o -> ByteBuffer.wrap(ProtoStuffUtil.serialize(o));
    }
}
