package com.example.quickdevelop.component.aspect;

import com.github.pagehelper.PageInfo;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Aspect
@Component
public class RedisAspect {

    @Resource
    private RedisTemplate<String,String> redisTemplate;

    @Around("within(@org.springframework.stereotype.Service *) && @annotation(cachePaging)")
    public Object around(ProceedingJoinPoint proceedingJoinPoint, CachePaging cachePaging) throws Throwable {
        System.out.println("===around===");
        String keyPrefix = cachePaging.keyPrefix();
        Object[] objects = proceedingJoinPoint.getArgs();
        Object param = objects[0];

//        Field field = param.getClass().getDeclaredField("page");
//        field.setAccessible(true);//暴力访问私有属性
//        Integer page = (Integer)field.get(param);

        String key = keyPrefix+":hashCode_"+JSONObject.fromObject(param).hashCode();
        System.err.println(key);

        Object redisObject = redisTemplate.opsForValue().get(key);
        if(redisObject==null){
            Object mysqlObject = proceedingJoinPoint.proceed();
            JSONObject mysqlJsonObject = JSONObject.fromObject(mysqlObject);
            Object listObj = mysqlJsonObject.get("list");
            JSONArray listJsonArray = JSONArray.fromObject(listObj);
            if(listJsonArray!=null&&listJsonArray.size()>0){
                redisTemplate.opsForValue().set(key,mysqlJsonObject.toString(),cachePaging.expire(), TimeUnit.MINUTES);
            }
            return mysqlObject;
        }else{
            JSONObject redisJsonObject = JSONObject.fromObject(redisObject);
            Object listObj = redisJsonObject.get("list");
            List list  = (List)JSONArray.toCollection(JSONArray.fromObject(listObj),cachePaging.clazz());
            Integer total = (Integer)redisJsonObject.get("total");
            Integer pageNum = (Integer)redisJsonObject.get("pageNum");
            Integer pageSize = (Integer)redisJsonObject.get("pageSize");
            Integer size = (Integer)redisJsonObject.get("size");
            Integer startRow = (Integer)redisJsonObject.get("startRow");
            Integer endRow = (Integer)redisJsonObject.get("endRow");
            Integer pages = (Integer)redisJsonObject.get("pages");
            Integer prePage = (Integer)redisJsonObject.get("prePage");
            Integer nextPage = (Integer)redisJsonObject.get("nextPage");
            Integer navigatePages = (Integer)redisJsonObject.get("navigatePages");
            Integer navigateFirstPage = (Integer)redisJsonObject.get("navigateFirstPage");
            Integer navigateLastPage = (Integer)redisJsonObject.get("navigateLastPage");

            boolean isFirstPage = (boolean)redisJsonObject.get("isFirstPage");
            boolean isLastPage = (boolean)redisJsonObject.get("isLastPage");
            boolean hasPreviousPage = (boolean)redisJsonObject.get("hasPreviousPage");
            boolean hasNextPage = (boolean)redisJsonObject.get("hasNextPage");

            Object navigatepageNumsObj = redisJsonObject.get("navigatepageNums");
            JSONArray navigatepageNumsJsonArray = JSONArray.fromObject(navigatepageNumsObj);
            int[] navigatepageNums = new int[navigatepageNumsJsonArray.size()];
            if(navigatepageNumsJsonArray!=null&&navigatepageNumsJsonArray.size()>0){
                for(int i=0;i<navigatepageNumsJsonArray.size();i++){
                    navigatepageNums[i] = (Integer) navigatepageNumsJsonArray.get(i);
                }
            }

            PageInfo redis = new PageInfo();
            redis.setList(list);
            redis.setTotal(total);
            redis.setPageNum(pageNum);
            redis.setPageSize(pageSize);
            redis.setSize(size);
            redis.setStartRow(startRow);
            redis.setEndRow(endRow);
            redis.setPages(pages);
            redis.setPrePage(prePage);
            redis.setNextPage(nextPage);
            redis.setNavigatePages(navigatePages);
            redis.setNavigateFirstPage(navigateFirstPage);
            redis.setNavigateLastPage(navigateLastPage);
            redis.setNavigatepageNums(navigatepageNums);
            redis.setIsFirstPage(isFirstPage);
            redis.setIsLastPage(isLastPage);
            redis.setHasPreviousPage(hasPreviousPage);
            redis.setHasNextPage(hasNextPage);
            return redis;
        }

    }

}
