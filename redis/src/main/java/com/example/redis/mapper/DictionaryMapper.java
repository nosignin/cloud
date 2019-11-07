package com.example.redis.mapper;

import com.example.redis.entity.Dictionary;
import org.apache.ibatis.annotations.Param;

public interface DictionaryMapper {
    Dictionary getKey(@Param("key") String key);
}
