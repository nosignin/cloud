package com.mysql.mybatis.plus.model;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;
import java.io.Serializable;

/**
 * <p>
 * 配件基础信息表
 * </p>
 *
 * @author 石佳
 * @since 2020-07-02
 */
@TableName("mk_parts")
public class Parts implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    /**
     * 联盟ID
     */
    private Long appId;

    /**
     * 门店ID(总部端为空)
     */
    private Long storeId;

    /**
     * 配件编码
     */
    private String partsNo;

    /**
     * 配件名称
     */
    private String partsName;

    /**
     * 配件类型
     */
    private Long partsCategoryId;

    /**
     * OE码
     */
    private String oeNo;

    /**
     * 出厂编码
     */
    private String factoryNo;

    /**
     * 条形码
     */
    private String barCode;

    /**
     * 通用图号
     */
    private String commonCode;

    /**
     * 单位
     */
    private String unit;

    /**
     * 规格
     */
    private String spec;

    /**
     * 型号
     */
    private String partModel;

    /**
     * 品牌
     */
    private String brand;

    /**
     * 产地
     */
    private String birthplace;

    private String applyCarModel;

    /**
     * 预计采购价
     */
    private BigDecimal costPrice;

    /**
     * VIP会员价
     */
    private BigDecimal vipPrice;

    /**
     * 批发价
     */
    private BigDecimal listPrice;

    /**
     * 最低售价
     */
    private BigDecimal lowPrice;

    /**
     * 参考售价
     */
    private BigDecimal price;

    /**
     * 4S参考价
     */
    private BigDecimal price4s;

    /**
     * 删除标识，0正常；1已删除
     */
    private Boolean deleteFlag;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAppId() {
        return appId;
    }

    public void setAppId(Long appId) {
        this.appId = appId;
    }

    public Long getStoreId() {
        return storeId;
    }

    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }

    public String getPartsNo() {
        return partsNo;
    }

    public void setPartsNo(String partsNo) {
        this.partsNo = partsNo;
    }

    public String getPartsName() {
        return partsName;
    }

    public void setPartsName(String partsName) {
        this.partsName = partsName;
    }

    public Long getPartsCategoryId() {
        return partsCategoryId;
    }

    public void setPartsCategoryId(Long partsCategoryId) {
        this.partsCategoryId = partsCategoryId;
    }

    public String getOeNo() {
        return oeNo;
    }

    public void setOeNo(String oeNo) {
        this.oeNo = oeNo;
    }

    public String getFactoryNo() {
        return factoryNo;
    }

    public void setFactoryNo(String factoryNo) {
        this.factoryNo = factoryNo;
    }

    public String getBarCode() {
        return barCode;
    }

    public void setBarCode(String barCode) {
        this.barCode = barCode;
    }

    public String getCommonCode() {
        return commonCode;
    }

    public void setCommonCode(String commonCode) {
        this.commonCode = commonCode;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getSpec() {
        return spec;
    }

    public void setSpec(String spec) {
        this.spec = spec;
    }

    public String getPartModel() {
        return partModel;
    }

    public void setPartModel(String partModel) {
        this.partModel = partModel;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getBirthplace() {
        return birthplace;
    }

    public void setBirthplace(String birthplace) {
        this.birthplace = birthplace;
    }

    public String getApplyCarModel() {
        return applyCarModel;
    }

    public void setApplyCarModel(String applyCarModel) {
        this.applyCarModel = applyCarModel;
    }

    public BigDecimal getCostPrice() {
        return costPrice;
    }

    public void setCostPrice(BigDecimal costPrice) {
        this.costPrice = costPrice;
    }

    public BigDecimal getVipPrice() {
        return vipPrice;
    }

    public void setVipPrice(BigDecimal vipPrice) {
        this.vipPrice = vipPrice;
    }

    public BigDecimal getListPrice() {
        return listPrice;
    }

    public void setListPrice(BigDecimal listPrice) {
        this.listPrice = listPrice;
    }

    public BigDecimal getLowPrice() {
        return lowPrice;
    }

    public void setLowPrice(BigDecimal lowPrice) {
        this.lowPrice = lowPrice;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getPrice4s() {
        return price4s;
    }

    public void setPrice4s(BigDecimal price4s) {
        this.price4s = price4s;
    }

    public Boolean getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(Boolean deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return "Parts{" +
        "id=" + id +
        ", appId=" + appId +
        ", storeId=" + storeId +
        ", partsNo=" + partsNo +
        ", partsName=" + partsName +
        ", partsCategoryId=" + partsCategoryId +
        ", oeNo=" + oeNo +
        ", factoryNo=" + factoryNo +
        ", barCode=" + barCode +
        ", commonCode=" + commonCode +
        ", unit=" + unit +
        ", spec=" + spec +
        ", partModel=" + partModel +
        ", brand=" + brand +
        ", birthplace=" + birthplace +
        ", applyCarModel=" + applyCarModel +
        ", costPrice=" + costPrice +
        ", vipPrice=" + vipPrice +
        ", listPrice=" + listPrice +
        ", lowPrice=" + lowPrice +
        ", price=" + price +
        ", price4s=" + price4s +
        ", deleteFlag=" + deleteFlag +
        ", createdAt=" + createdAt +
        ", updatedAt=" + updatedAt +
        "}";
    }
}
