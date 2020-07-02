package com.mysql.mybatis.plus.service.impl;

import com.mysql.mybatis.plus.model.Parts;
import com.mysql.mybatis.plus.mapper.PartsMapper;
import com.mysql.mybatis.plus.service.IPartsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 配件基础信息表 服务实现类
 * </p>
 *
 * @author 石佳
 * @since 2020-07-02
 */
@Service
public class PartsServiceImpl extends ServiceImpl<PartsMapper, Parts> implements IPartsService {

}
