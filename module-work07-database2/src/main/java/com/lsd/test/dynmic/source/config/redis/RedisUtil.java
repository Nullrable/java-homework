package com.lsd.test.dynmic.source.config.redis;

import com.lsd.test.dynmic.source.config.AppContextHolder;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;

@Configuration
@Slf4j
public class RedisUtil {

    @Value("${redis.valid}")
    private static boolean redisValid = true;

    private static RedisSummoner redisSummoner;

    @Autowired
    public void setRedisSummoner(RedisSummoner redisSummoner) {
        RedisUtil.redisSummoner = redisSummoner;
    }

    public static void put(String key, Object value){
        if(redisValid){
            try {
                redisSummoner.getRedisTemplate(AppContextHolder.getSourceKey()).opsForValue().set(key, value);

            } catch (Exception e) {
                log.warn(e.getMessage(), e);

            }

        }
    }

    public static void put( String key, Object value, int expireTime){
        if(redisValid){
            try {

                redisSummoner.getRedisTemplate(AppContextHolder.getSourceKey()).opsForValue().set(key, value, expireTime, TimeUnit.SECONDS);
            } catch (Exception e) {
                log.warn(e.getMessage(), e);

            }

        }
    }

    public static void put(String key, Object value, Date expireTime){
        if(redisValid){
            try {

                RedisTemplate redisTemplate = redisSummoner.getRedisTemplate(AppContextHolder.getSourceKey());
                redisTemplate.opsForValue().set(key, value);
                redisTemplate.expireAt(key, expireTime);
            } catch (Exception e) {
                log.warn(e.getMessage(), e);

            }

        }
    }

    public static void setPut(String key, Object value){
        if(redisValid){
            try {
                RedisTemplate redisTemplate = redisSummoner.getRedisTemplate(AppContextHolder.getSourceKey());
                redisTemplate.opsForSet().add(key, value);

            } catch (Exception e) {
                log.warn(e.getMessage(), e);

            }

        }
    }

    public static void setPut(String key, Object value, int expireDays){
        if(redisValid){
            try {
                RedisTemplate redisTemplate = redisSummoner.getRedisTemplate(AppContextHolder.getSourceKey());
                redisTemplate.opsForSet().add(key, value);
                redisTemplate.expire(key, expireDays, TimeUnit.DAYS);
            } catch(Exception e) {
                log.warn(e.getMessage(), e);
            }
        }
    }

    public static void setPutList(String key, List values){
        if(values.isEmpty()){
            return;
        }

        try {
            RedisTemplate redisTemplate = redisSummoner.getRedisTemplate(AppContextHolder.getSourceKey());
            Object[] objects = values.toArray(new Object[values.size()]);
            redisTemplate.opsForSet().add(key, objects);
        } catch(Exception e) {
            log.warn(e.getMessage(), e);
        }

    }

    public static void setPutList(String key, List values, int seconds){
        if(values.size() == 0){
            return;
        }
        if(redisValid){
            try {
                RedisTemplate redisTemplate = redisSummoner.getRedisTemplate(AppContextHolder.getSourceKey());
                Object[] objects = values.toArray(new Object[values.size()]);
                redisTemplate.opsForSet().add(key, objects);
                redisTemplate.expire(key, seconds, TimeUnit.SECONDS);
            } catch(Exception e) {
                log.warn(e.getMessage(), e);
            }
        }
    }

    public static void setRemove(String key, Object value){
        if(redisValid){
            try {

                RedisTemplate redisTemplate = redisSummoner.getRedisTemplate(AppContextHolder.getSourceKey());
                redisTemplate.opsForSet().remove(key, value);

            } catch (Exception e) {
                log.warn(e.getMessage(), e);

            }

        }
    }

    public static Set setGet(String key){

        try {
            RedisTemplate redisTemplate = redisSummoner.getRedisTemplate(AppContextHolder.getSourceKey());
            return redisTemplate.opsForSet().members(key);
        } catch (Exception e) {
            log.warn(e.getMessage(), e);
            return null;
        }
    }

    public static boolean setExist(String key, Object value){

        try {
            RedisTemplate redisTemplate = redisSummoner.getRedisTemplate(AppContextHolder.getSourceKey());
            return redisTemplate.opsForSet().isMember(key, value);
        } catch (Exception e) {
            log.warn(e.getMessage(), e);
            return false;
        }
    }

    public static boolean setIfAbsent(String key, Object value, int expireTime){

        try {
            RedisTemplate redisTemplate = redisSummoner.getRedisTemplate(AppContextHolder.getSourceKey());
            boolean result = redisTemplate.opsForValue().setIfAbsent(key, value);
            if(result && expireTime != 0) {
                redisTemplate.expire(key, expireTime, TimeUnit.SECONDS);
            }
            return result;
        } catch (Exception e) {
            log.warn(e.getMessage(), e);
            return false;
        }
    }

    public static Object get(String key){
        if(!redisValid){
            return null;
        }

        try {
            RedisTemplate redisTemplate = redisSummoner.getRedisTemplate(AppContextHolder.getSourceKey());
            return redisTemplate.opsForValue().get(key);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return null;
        }
    }

    public static Long increment(String key, long value){
        if(!redisValid){
            return null;
        }
        try {
            RedisTemplate redisTemplate = redisSummoner.getRedisTemplate(AppContextHolder.getSourceKey());
            return redisTemplate.opsForValue().increment(key, value);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return null;
        }
    }

    public static void delete(String key){
        if(redisValid){
            try {

                RedisTemplate redisTemplate = redisSummoner.getRedisTemplate(AppContextHolder.getSourceKey());
                redisTemplate.delete(key);

            } catch (Exception e) {
                log.warn(e.getMessage(), e);
            }

        }
    }

    public static void hashPut(String key, Object hashKey, Object value){
        if(redisValid){
            try {

                RedisTemplate redisTemplate = redisSummoner.getRedisTemplate(AppContextHolder.getSourceKey());
                redisTemplate.opsForHash().put(key, hashKey, value);
            } catch (Exception e) {
                log.error(e.getMessage(), e);

            }

        }
    }

    public static Set findHashKeys(String key){
        if(redisValid){
            try {

                RedisTemplate redisTemplate = redisSummoner.getRedisTemplate(AppContextHolder.getSourceKey());
                return redisTemplate.opsForHash().keys(key);

            } catch (Exception e) {
                log.error(e.getMessage(), e);
                return null;

            }

        }
        return null;
    }

    public static Map hashGetMap(String key){
        if(redisValid){
            try {

                RedisTemplate redisTemplate = redisSummoner.getRedisTemplate(AppContextHolder.getSourceKey());
                return redisTemplate.opsForHash().entries(key);

            } catch (Exception e) {
                log.error(e.getMessage(), e);

            }

        }
        return null;
    }

    public static Object hashGet(String key, Object hashKey){
        if(redisValid){
            try {

                RedisTemplate redisTemplate = redisSummoner.getRedisTemplate(AppContextHolder.getSourceKey());
                return redisTemplate.opsForHash().get(key, hashKey);

            } catch (Exception e) {
                log.warn(e.getMessage(), e);

            }

        }
        return null;
    }

    public static void hashDelete(String key, Object hashKey){
        if(redisValid){
            try {

                RedisTemplate redisTemplate = redisSummoner.getRedisTemplate(AppContextHolder.getSourceKey());
                redisTemplate.opsForHash().delete(key, hashKey);

            } catch (Exception e) {
                log.warn(e.getMessage(), e);

            }

        }
    }

    public static boolean tryLock(String key) {
        return tryLock(key, 10);
    }

    public static boolean tryLock(String key, int second) {
        try {

            RedisTemplate redisTemplate = redisSummoner.getRedisTemplate(AppContextHolder.getSourceKey());

            second *= 20;
            int currentSecond = 0;
            while(currentSecond <= second) {
                Boolean gotten = redisTemplate.opsForValue().setIfAbsent(key, "locked");
                if(gotten != null && gotten) {
                    return true;
                }
                Thread.sleep(50);
                currentSecond++;
            }
            return false;
        } catch(Exception e) {
            return false;
        }
    }

    public static void releaseLock(String key) {

        RedisTemplate redisTemplate = redisSummoner.getRedisTemplate(AppContextHolder.getSourceKey());
        redisTemplate.delete(key);
    }

    public static Long pkIncrement(String key, long value){
        RedisTemplate redisTemplate = redisSummoner.getRedisTemplate(AppContextHolder.getSourceKey());
        return redisTemplate.opsForValue().increment(key, value);

    }

    public static Long pkHashIncrement(String key, String hashkey, long value){
        RedisTemplate redisTemplate = redisSummoner.getRedisTemplate(AppContextHolder.getSourceKey());
        return redisTemplate.opsForHash().increment(key, hashkey, value);

    }

    /**
     * 一定要用setIfAbsent
     * @param key
     * @param value
     * @return
     */
    public static boolean pkPut(String key, long value){
        RedisTemplate redisTemplate = redisSummoner.getRedisTemplate(AppContextHolder.getSourceKey());
        return redisTemplate.opsForValue().setIfAbsent(key, value);
    }

    public static void pkDelete(String key){

        RedisTemplate redisTemplate = redisSummoner.getRedisTemplate(AppContextHolder.getSourceKey());
        redisTemplate.delete(key);
    }

    /**
     * 一定要用setIfAbsent
     * @param key
     * @param value
     * @param expireTime 超时时间
     * @return
     */
    public static boolean pkPut(String key, long value, int expireTime){

        RedisTemplate redisTemplate = redisSummoner.getRedisTemplate(AppContextHolder.getSourceKey());

        boolean flag =  redisTemplate.opsForValue().setIfAbsent(key, value);
        if(flag){
            redisTemplate.expire(key, expireTime, TimeUnit.SECONDS);
        }
        return flag;
    }

    public static boolean pkHashPut(String key, String hashkey, long value){

        RedisTemplate redisTemplate = redisSummoner.getRedisTemplate(AppContextHolder.getSourceKey());
        redisTemplate.opsForHash().put(key, hashkey, value);
        return false;
    }

    public static Long pkGet(String key){

        RedisTemplate redisTemplate = redisSummoner.getRedisTemplate(AppContextHolder.getSourceKey());
        return (Long) redisTemplate.opsForValue().get(key);

    }

    public static boolean expire(String key, int time, TimeUnit timeUnit) {

        RedisTemplate redisTemplate = redisSummoner.getRedisTemplate(AppContextHolder.getSourceKey());
        return redisTemplate.expire(key, time, timeUnit);

    }

    public static Set<String> keys(String pattern){

        RedisTemplate redisTemplate = redisSummoner.getRedisTemplate(AppContextHolder.getSourceKey());

        return redisTemplate.keys(pattern);
    }


    public static boolean hasKey(String key) {

        RedisTemplate redisTemplate = redisSummoner.getRedisTemplate(AppContextHolder.getSourceKey());
        return redisTemplate.hasKey(key);

    }



}
