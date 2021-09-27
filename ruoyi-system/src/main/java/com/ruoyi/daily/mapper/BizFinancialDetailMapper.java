package com.ruoyi.daily.mapper;

import java.util.List;

import com.ruoyi.daily.domain.BizFinancialDetail;

/**
 * 收支明细Mapper接口
 *
 * @author GhostXu
 * @date 2021-09-27
 */
public interface BizFinancialDetailMapper {
    /**
     * 查询收支明细
     *
     * @param financialDetailId 收支明细ID
     * @return 收支明细
     */
    public BizFinancialDetail selectBizFinancialDetailById(Long financialDetailId);

    /**
     * 查询收支明细列表
     *
     * @param bizFinancialDetail 收支明细
     * @return 收支明细集合
     */
    public List<BizFinancialDetail> selectBizFinancialDetailList(BizFinancialDetail bizFinancialDetail);

    /**
     * 新增收支明细
     *
     * @param bizFinancialDetail 收支明细
     * @return 结果
     */
    public int insertBizFinancialDetail(BizFinancialDetail bizFinancialDetail);

    /**
     * 修改收支明细
     *
     * @param bizFinancialDetail 收支明细
     * @return 结果
     */
    public int updateBizFinancialDetail(BizFinancialDetail bizFinancialDetail);

    /**
     * 删除收支明细
     *
     * @param financialDetailId 收支明细ID
     * @return 结果
     */
    public int deleteBizFinancialDetailById(Long financialDetailId);

    /**
     * 批量删除收支明细
     *
     * @param financialDetailIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteBizFinancialDetailByIds(Long[] financialDetailIds);
}
