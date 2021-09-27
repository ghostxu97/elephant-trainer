package com.ruoyi.daily.domain;

import java.math.BigDecimal;
import java.util.List;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 收支管理对象 biz_financial_info
 *
 * @author GhostXu
 * @date 2021-09-27
 */
public class BizFinancialInfo extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 自增主键
     */
    private Long financialId;

    /**
     * 登记日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "登记日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date recordDate;

    /**
     * 收入金额
     */
    @Excel(name = "收入金额")
    private BigDecimal revenueMoney;

    /**
     * 支出金额
     */
    @Excel(name = "支出金额")
    private BigDecimal expenditureMoney;

    /**
     * 收支明细信息
     */
    private List<BizFinancialDetail> bizFinancialDetailList;

    public void setFinancialId(Long financialId) {
        this.financialId = financialId;
    }

    public Long getFinancialId() {
        return financialId;
    }

    public void setRecordDate(Date recordDate) {
        this.recordDate = recordDate;
    }

    public Date getRecordDate() {
        return recordDate;
    }

    public void setRevenueMoney(BigDecimal revenueMoney) {
        this.revenueMoney = revenueMoney;
    }

    public BigDecimal getRevenueMoney() {
        return revenueMoney;
    }

    public void setExpenditureMoney(BigDecimal expenditureMoney) {
        this.expenditureMoney = expenditureMoney;
    }

    public BigDecimal getExpenditureMoney() {
        return expenditureMoney;
    }

    public List<BizFinancialDetail> getBizFinancialDetailList() {
        return bizFinancialDetailList;
    }

    public void setBizFinancialDetailList(List<BizFinancialDetail> bizFinancialDetailList) {
        this.bizFinancialDetailList = bizFinancialDetailList;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("financialId", getFinancialId())
                .append("recordDate", getRecordDate())
                .append("revenueMoney", getRevenueMoney())
                .append("expenditureMoney", getExpenditureMoney())
                .append("bizFinancialDetailList", getBizFinancialDetailList())
                .toString();
    }
}
