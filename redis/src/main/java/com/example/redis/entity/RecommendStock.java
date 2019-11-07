package com.example.redis.entity;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class RecommendStock {
//    荐股id
    String id;
//    荐股的用户id
    String userId;
//    查看荐股需要支付的费用
    Integer pay;
//    买入价
    BigDecimal buyPrice;
//    卖出价
    BigDecimal salePrice;
}
