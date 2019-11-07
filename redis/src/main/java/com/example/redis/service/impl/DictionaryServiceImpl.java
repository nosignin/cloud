package com.example.redis.service.impl;

import com.example.redis.entity.Dictionary;
import com.example.redis.enums.ResultEnum;
import com.example.redis.exception.BaseException;
import com.example.redis.mapper.DictionaryMapper;
import com.example.redis.service.DictionaryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
@Slf4j
public class DictionaryServiceImpl implements DictionaryService {
    @Resource
    private DictionaryMapper dictionaryMapper;
    /**
     * 从字典中获取值
     * @param key
     * @return
     */
    @Override
    public String getDictionaryValueByKey(String key) {
        Dictionary dictionary = dictionaryMapper.getKey(key);
        if(dictionary!=null){
            Integer status = dictionary.getStatus();
            if(status==0){
                return dictionary.getValue();
            }
        }
        log.error(">>> {} : {} <<<",ResultEnum.DICTIONARY_NOT_EXIST_OR_NOT_ACTIVATED.message,key);
        throw new BaseException(ResultEnum.DICTIONARY_NOT_EXIST_OR_NOT_ACTIVATED.message);
    }
}
