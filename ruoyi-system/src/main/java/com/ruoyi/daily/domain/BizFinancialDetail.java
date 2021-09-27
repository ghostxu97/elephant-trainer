package com.ruoyi.daily.domain;

import java.math.BigDecimal;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 收支明细对象 biz_financial_detail
 *
 * @author GhostXu
 * @date 2021-09-27
 */
public class BizFinancialDetail extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 收支明细表自增主键
     */
    private Long financialDetailId;

    /**
     * 收支类型
     */
    @Excel(name = "收支类型")
    private Integer financialType;

    /**
     * 收支信息表ID
     */
    @Excel(name = "收支信息表ID")
    private Long financialId;

    /**
     * 类目名
     */
    @Excel(name = "类目名")
    private String categoryName;

    /**
     * 金额
     */
    @Excel(name = "金额")
    private BigDecimal money;

    public void setFinancialDetailId(Long financialDetailId) {
        this.financialDetailId = financialDetailId;
    }

    public Long getFinancialDetailId() {
        return financialDetailId;
    }

    public void setFinancialType(Integer financialType) {
        this.financialType = financialType;
    }

    public Integer getFinancialType() {
        return financialType;
    }

    public void setFinancialId(Long financialId) {
        this.financialId = financialId;
    }

    public Long getFinancialId() {
        return financialId;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public BigDecimal getMoney() {
        return money;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("financialDetailId", getFinancialDetailId())
                .append("financialType", getFinancialType())
                .append("financialId", getFinancialId())
                .append("categoryName", getCategoryName())
                .append("money", getMoney())
                .append("remark", getRemark())
                .append("createTime", getCreateTime())
                .append("createBy", getCreateBy())
                .append("updateTime", getUpdateTime())
                .append("updateBy", getUpdateBy())
                .toString();
    }
}
