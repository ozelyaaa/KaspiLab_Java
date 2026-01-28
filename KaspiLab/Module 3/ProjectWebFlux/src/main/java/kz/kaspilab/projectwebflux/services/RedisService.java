package kz.kaspilab.projectwebflux.services;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.ReactiveStringRedisTemplate;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.Duration;

@Service
@RequiredArgsConstructor
public class RedisService {

    private final ReactiveStringRedisTemplate redisTemplate;

    public Mono<Boolean> acquire(String key, Duration ttl) {
        return redisTemplate
                .opsForValue()
                .setIfAbsent(key, "1", ttl)
                .defaultIfEmpty(false);
    }
}
