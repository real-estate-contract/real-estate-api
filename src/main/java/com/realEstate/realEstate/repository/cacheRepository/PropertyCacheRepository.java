package com.realEstate.realEstate.repository.cacheRepository;


import com.realEstate.realEstate.model.entity.Property;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.time.Duration;
import java.util.Optional;

@Repository
@Slf4j
@RequiredArgsConstructor
public class PropertyCacheRepository {

    private final RedisTemplate<String, Property> propertyRedisTemplate;
    private final static Duration USER_CACHE_TTL = Duration.ofDays(30);

    public void setProperty(Property property) {
        String key = getKey(property.getPropertyId());
        log.info("Set property to Redis {}({})", key,property);
        propertyRedisTemplate.opsForValue().set(key, property, USER_CACHE_TTL);
    }

    public Optional<Property> getProperty(Long propertyId) {
        Property data = propertyRedisTemplate.opsForValue().get(getKey(propertyId));
        log.info("Get Property from redis {}", data);
        return Optional.ofNullable(data);
    }

    private String getKey(Long propertyId) {
        return "UID: " + propertyId;
    }
}
