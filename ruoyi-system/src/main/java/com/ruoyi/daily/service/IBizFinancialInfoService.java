package com.ruoyi.daily.service;

import java.util.List;

import com.ruoyi.daily.domain.BizFinancialInfo;

/**
 * 收支管理Service接口
 *
 * @author GhostXu
 * @date 2021-09-27
 */
public interface IBizFinancialInfoService {
    /**
     * 查询收支管理
     *
     * @param financialId 收支管理ID
     * @return 收支管理
     */
    public BizFinancialInfo selectBizFinancialInfoById(Long financialId);

    /**
     * 查询收支管理列表
     *
     * @param bizFinancialInfo 收支管理
     * @return 收支管理集合
     */
    public List<BizFinancialInfo> selectBizFinancialInfoList(BizFinancialInfo bizFinancialInfo);

    /**
     * 新增收支管理
     *
     * @param bizFinancialInfo 收支管理
     * @return 结果
     */
    public int insertBizFinancialInfo(BizFinancialInfo bizFinancialInfo);

    /**
     * 修改收支管理
     *
     * @param bizFinancialInfo 收支管理
     * @return 结果
     */
    public int updateBizFinancialInfo(BizFinancialInfo bizFinancialInfo);

    /**
     * 批量删除收支管理
     *
     * @param financialIds 需要删除的收支管理ID
     * @return 结果
     */
    public int deleteBizFinancialInfoByIds(Long[] financialIds);

    /**
     * 删除收支管理信息
     *
     * @param financialId 收支管理ID
     * @return 结果
     */
    public int deleteBizFinancialInfoById(Long financialId);
}
