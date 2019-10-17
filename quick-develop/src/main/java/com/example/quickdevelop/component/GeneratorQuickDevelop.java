package com.example.quickdevelop.component;

import com.example.quickdevelop.component.enums.CodeTypeEnum;
import com.example.quickdevelop.component.util.GeneratorCodeUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * 输入表的信息,然后执行main方法即可生成通用业务代码
 */
@Slf4j
public class GeneratorQuickDevelop {

    public static void main(String[] args) throws Exception{
        String url = "jdbc:mysql://localhost:3306/bit_shield_currency?useUnicode=true&characterEncoding=utf8&serverTimezone=Asia/Shanghai";
        String username = "root";
        String password = "123456";
//        文件输出路径
        String outPutPath = "C:/Users/Administrator/Desktop/quickdevelop";
        GeneratorCodeUtil.init(url, outPutPath, username, password);

        //包名
        String packageName="com.example.quickdevelop";

        GeneratorCodeUtil.generatorCode("student","学生表",packageName, CodeTypeEnum.ALL);
        GeneratorCodeUtil.generatorCode("teacher","老师表",packageName, CodeTypeEnum.ALL);
        GeneratorCodeUtil.generatorCode("teacher_student","老师学生表",packageName, CodeTypeEnum.ALL);
        log.info(">>> 生成完毕 <<<");
    }

}
