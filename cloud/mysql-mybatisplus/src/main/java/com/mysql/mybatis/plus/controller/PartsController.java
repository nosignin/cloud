package com.mysql.mybatis.plus.controller;


import com.mysql.mybatis.plus.model.Parts;
import com.mysql.mybatis.plus.service.IPartsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 配件基础信息表 前端控制器
 * </p>
 *
 * @author 石佳
 * @since 2020-07-02
 */
@RestController
@RequestMapping("/parts")
public class PartsController {
    @Autowired
    private IPartsService iPartsService;

    @GetMapping("/list")
    public List<Parts> list(){
        return iPartsService.list();
    }
}

