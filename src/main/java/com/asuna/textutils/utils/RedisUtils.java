package com.asuna.textutils.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.validation.constraints.NotNull;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Component
public class RedisUtils {

    @Autowired
    private RedisTemplate<String, Object> template;

    public RedisUtils(RedisTemplate<String, Object> template){
        this.template = template;
    }

    public boolean expire(String key, long time){
        try {
            if (time > 0){
                template.expire(key, time, TimeUnit.SECONDS);
                return true;
            }else {
                return false;
            }
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public long getExpire(String key){
        if (key == null){
            return Long.MIN_VALUE;
        }
        return template.getExpire(key, TimeUnit.SECONDS);
    }

    public boolean hasKey(@NotNull String key){
        if (key == null){
            return false;
        }
        return template.hasKey(key);
    }

    public Object get(String key){
        try {
            if (key == null){
                return null;
            }
            return template.opsForValue().get(key);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public boolean set(String key, Object value){
        try {
            template.opsForValue().set(key, value);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public boolean set(String key, Object value, long time){
        try {
            if (time > 0){
                template.opsForValue().set(key, value, time, TimeUnit.SECONDS);
            }else {
                set(key, value);
            }
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public void delete(String...keys){
        if (keys == null || keys.length == 0){
            return;
        }
        if (keys.length == 1){
            template.delete(keys[0]);
        }else {
            template.delete(CollectionUtils.arrayToList(keys));
        }
    }

    public long increase(String key, long delta){
        if (delta < 0){
            throw new RuntimeException("增长因子不能小于0");
        }
        return template.opsForValue().increment(key, delta);
    }

    public long decrease(String key, long delta){
        if (delta < 0){
            throw new RuntimeException("增长因子不能小于0");
        }
        return template.opsForValue().decrement(key, delta);
    }

    public Map<Object, Object> hashMapGet(String key){
        if (key == null){
            return null;
        }
        return template.opsForHash().entries(key);
    }

    public Object hashGet(String key, String value){
        if (key == null){
            return null;
        }
        return template.opsForHash().get(key, value);
    }

    public boolean hashMapSet(String key, Map<String, Object> map){
        try {
            template.opsForHash().putAll(key, map);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public boolean hashSet(String key, String item, Object value){
        try {
            template.opsForHash().put(key, item, value);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public boolean hashSet(String key, String item, Object value, long time){
        try {
            template.opsForHash().put(key, item, value);
            if (time > 0){
                expire(key, time);
            }
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public void deleteHash(String key, String item){
        template.opsForHash().delete(key, item);
    }

    public boolean hasHashKeyPair(String key, String item){
        return template.opsForHash().hasKey(key, item);
    }


}
