package com.ruoyi.daily.mapper;

import java.util.List;

import com.ruoyi.daily.domain.BizFinancialInfo;
import com.ruoyi.daily.domain.BizFinancialDetail;

/**
 * 收支管理Mapper接口
 *
 * @author GhostXu
 * @date 2021-09-27
 */
public interface BizFinancialInfoMapper {
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
     * 删除收支管理
     *
     * @param financialId 收支管理ID
     * @return 结果
     */
    public int deleteBizFinancialInfoById(Long financialId);

    /**
     * 批量删除收支管理
     *
     * @param financialIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteBizFinancialInfoByIds(Long[] financialIds);

    /**
     * 批量删除收支明细
     *
     * @param customerIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteBizFinancialDetailByFinancialIds(Long[] financialIds);

    /**
     * 批量新增收支明细
     *
     * @param bizFinancialDetailList 收支明细列表
     * @return 结果
     */
    public int batchBizFinancialDetail(List<BizFinancialDetail> bizFinancialDetailList);


    /**
     * 通过收支管理ID删除收支明细信息
     *
     * @param roleId 角色ID
     * @return 结果
     */
    public int deleteBizFinancialDetailByFinancialId(Long financialId);
}
