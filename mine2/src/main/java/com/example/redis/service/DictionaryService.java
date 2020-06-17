package com.example.redis.service;

public interface DictionaryService {
    public <V> V getDictionaryValueByKeyWithDefaultValue(String key,V defaultValue);
    public String getDictionaryValueByKeyWithException(String key);
}
