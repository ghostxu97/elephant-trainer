package com.ruoyi.daily.service.impl;

import java.util.List;

import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.daily.mapper.BizFinancialDetailMapper;
import com.ruoyi.daily.domain.BizFinancialDetail;
import com.ruoyi.daily.service.IBizFinancialDetailService;

/**
 * 收支明细Service业务层处理
 *
 * @author GhostXu
 * @date 2021-09-27
 */
@Service
public class BizFinancialDetailServiceImpl implements IBizFinancialDetailService {
    @Autowired
    private BizFinancialDetailMapper bizFinancialDetailMapper;

    /**
     * 查询收支明细
     *
     * @param financialDetailId 收支明细ID
     * @return 收支明细
     */
    @Override
    public BizFinancialDetail selectBizFinancialDetailById(Long financialDetailId) {
        return bizFinancialDetailMapper.selectBizFinancialDetailById(financialDetailId);
    }

    /**
     * 查询收支明细列表
     *
     * @param bizFinancialDetail 收支明细
     * @return 收支明细
     */
    @Override
    public List<BizFinancialDetail> selectBizFinancialDetailList(BizFinancialDetail bizFinancialDetail) {
        return bizFinancialDetailMapper.selectBizFinancialDetailList(bizFinancialDetail);
    }

    /**
     * 新增收支明细
     *
     * @param bizFinancialDetail 收支明细
     * @return 结果
     */
    @Override
    public int insertBizFinancialDetail(BizFinancialDetail bizFinancialDetail) {
        bizFinancialDetail.setCreateTime(DateUtils.getNowDate());
        return bizFinancialDetailMapper.insertBizFinancialDetail(bizFinancialDetail);
    }

    /**
     * 修改收支明细
     *
     * @param bizFinancialDetail 收支明细
     * @return 结果
     */
    @Override
    public int updateBizFinancialDetail(BizFinancialDetail bizFinancialDetail) {
        bizFinancialDetail.setUpdateTime(DateUtils.getNowDate());
        return bizFinancialDetailMapper.updateBizFinancialDetail(bizFinancialDetail);
    }

    /**
     * 批量删除收支明细
     *
     * @param financialDetailIds 需要删除的收支明细ID
     * @return 结果
     */
    @Override
    public int deleteBizFinancialDetailByIds(Long[] financialDetailIds) {
        return bizFinancialDetailMapper.deleteBizFinancialDetailByIds(financialDetailIds);
    }

    /**
     * 删除收支明细信息
     *
     * @param financialDetailId 收支明细ID
     * @return 结果
     */
    @Override
    public int deleteBizFinancialDetailById(Long financialDetailId) {
        return bizFinancialDetailMapper.deleteBizFinancialDetailById(financialDetailId);
    }
}
