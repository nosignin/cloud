package com.example.redis.enums;

public enum ExcelEnum {
    DEFAULT("title","sheet","filename"),
    ;
    public String title;
    public String sheetName;
    public String fileName;
    ExcelEnum(String title, String sheetName, String fileName) {
        this.title = title;
        this.sheetName = sheetName;
        this.fileName = fileName;
    }
}
