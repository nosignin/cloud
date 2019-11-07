package com.example.redis.aop;

import com.example.redis.annotation.CheckRecommendStockParamAnnotation;
import com.example.redis.entity.CommHttpResult;
import com.example.redis.entity.Dictionary;
import com.example.redis.entity.RecommendStock;
import com.example.redis.enums.ResultEnum;
import com.example.redis.exception.BaseException;
import com.example.redis.mapper.DictionaryMapper;
import com.example.redis.service.DictionaryService;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.math.BigDecimal;

/**
 * 业务需求:需要写一个股票推荐,预言某个股票的涨幅,推荐需要付费查看,推荐有自己的id,以及推荐人的用户id,荐股在一定周期后就自动结束了
 * 现在需要校验的是:
 * =====================================================
 * 最小涨幅不能低于10%
 * 最大涨幅不能高于1000%
 * 最小付费价格不能低于1块钱
 * 最大付费价格不能高于100块钱
 * 最大进行中的荐股数量不能超过10
 * 荐股id是否存在
 * 荐股user_id是否存在
 * =====================================================
 */
@Aspect
@Component
@Slf4j
@Order(2)
public class CheckRecommendStockParamAspect {
    @Autowired
    private DictionaryService dictionaryService;

    @Pointcut("@annotation(com.example.redis.annotation.CheckRecommendStockParamAnnotation)")
    public void pointCut(){}

    /**
     * 访问:localhost:8080/test/launch/recommend/stock
     * 参数:{"id":"e123","userId":"a123","pay":10,"buyPrice":15.2,"salePrice":24.3}
     * 结果:{"status":"fail","data":"该字典不存在或者未激活"}
     * @param joinPoint
     * @return
     */
    @Around("pointCut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        CheckRecommendStockParamAnnotation annotation = signature.getMethod().getAnnotation(CheckRecommendStockParamAnnotation.class);
        Object[] args = joinPoint.getArgs();
        RecommendStock recommendStock = (RecommendStock) args[0];
        boolean checkMinRise = annotation.checkMinRise();
        checkMinRise(recommendStock,checkMinRise);
        boolean checkMaxRise = annotation.checkMaxRise();
        checkMaxRise(recommendStock,checkMaxRise);
        boolean checkMinPay = annotation.checkMinPay();
        checkMinPay(recommendStock,checkMinPay);
        boolean checkMaxPay = annotation.checkMaxPay();
        checkMaxPay(recommendStock,checkMaxPay);
        boolean checkOnGoingAmount = annotation.checkOnGoingAmount();
        checkOnGoingAmount(recommendStock,checkOnGoingAmount);
        boolean checkIdExist = annotation.checkIdExist();
        checkIdExist(recommendStock,checkIdExist);
        boolean checkUserIdExist = annotation.checkUserIdExist();
        checkUserIdExist(recommendStock,checkUserIdExist);
        Object resultData = joinPoint.proceed(args);
        return resultData;
    }

    public void checkMinRise(RecommendStock recommendStock, boolean checkMinRise){
        if(checkMinRise){
            BigDecimal rise = caculateRise(recommendStock);
            String recommendStockMinRiseStr = dictionaryService.getDictionaryValueByKey("RECOMMEND_STOCK_MIN_RISE");
            BigDecimal minRise = new BigDecimal(recommendStockMinRiseStr);
            if(rise.compareTo(minRise)==-1){
                log.error(">>> {} <<<",ResultEnum.RECOMMEND_STOCK_RISE_MUST_NOT_BELOW_MIN_RISE.message);
                throw new BaseException(ResultEnum.RECOMMEND_STOCK_RISE_MUST_NOT_BELOW_MIN_RISE.message);
            }
        }
    }

    private BigDecimal caculateRise(RecommendStock recommendStock) {
        BigDecimal buyPrice = recommendStock.getBuyPrice();
        BigDecimal salePrice = recommendStock.getSalePrice();
        BigDecimal rise = salePrice.subtract(buyPrice).multiply(new BigDecimal(100)).divide(buyPrice,8,BigDecimal.ROUND_HALF_UP);
        return rise;
    }


    public void checkMaxRise(RecommendStock recommendStock, boolean checkMaxRise){
        if(checkMaxRise){
            BigDecimal rise = caculateRise(recommendStock);
            String recommendStockMaxRiseStr = dictionaryService.getDictionaryValueByKey("RECOMMEND_STOCK_MAX_RISE");
            BigDecimal maxRise = new BigDecimal(recommendStockMaxRiseStr);
            if(rise.compareTo(maxRise)==-1){
                log.error(">>> {} <<<",ResultEnum.RECOMMEND_STOCK_RISE_MUST_NOT_UPPER_MAX_RISE.message);
                throw new BaseException(ResultEnum.RECOMMEND_STOCK_RISE_MUST_NOT_UPPER_MAX_RISE.message);
            }
        }
    }

    public void checkMinPay(RecommendStock recommendStock, boolean checkMinPay){
        if(checkMinPay){
            Integer pay = recommendStock.getPay();
            String recommendStockMinPay = dictionaryService.getDictionaryValueByKey("RECOMMEND_STOCK_MIN_PAY");
            Integer minPay = Integer.valueOf(recommendStockMinPay);
            if(pay<minPay){
                log.error(">>> {} <<<",ResultEnum.RECOMMEND_STOCK_PAY_MUST_NOT_BELOW_MIN_PAY.message);
                throw new BaseException(ResultEnum.RECOMMEND_STOCK_PAY_MUST_NOT_BELOW_MIN_PAY.message);
            }
        }
    }
    public void checkMaxPay(RecommendStock recommendStock, boolean checkMaxPay){
        if(checkMaxPay){
            Integer pay = recommendStock.getPay();
            String recommendStockMaxPay = dictionaryService.getDictionaryValueByKey("RECOMMEND_STOCK_MAX_PAY");
            Integer maxPay = Integer.valueOf(recommendStockMaxPay);
            if(pay>maxPay){
                log.error(">>> {} <<<",ResultEnum.RECOMMEND_STOCK_PAY_MUST_NOT_UPPER_MAX_PAY.message);
                throw new BaseException(ResultEnum.RECOMMEND_STOCK_PAY_MUST_NOT_UPPER_MAX_PAY.message);
            }
        }
    }
    public void checkOnGoingAmount(RecommendStock recommendStock, boolean checkOnGoingAmount){
//        需要修改表字段,加入周期,开始时间等等,业务略微复杂,不实现
    }
    public void checkIdExist(RecommendStock recommendStock, boolean checkIdExist){
//        自行查数据表
    }
    public void checkUserIdExist(RecommendStock recommendStock, boolean checkUserIdExist){
//        自行查数据表即可
    }
}
