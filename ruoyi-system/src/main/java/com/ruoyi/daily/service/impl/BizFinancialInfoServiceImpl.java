package com.ruoyi.daily.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

import com.ruoyi.common.utils.StringUtils;
import org.springframework.transaction.annotation.Transactional;
import com.ruoyi.daily.domain.BizFinancialDetail;
import com.ruoyi.daily.mapper.BizFinancialInfoMapper;
import com.ruoyi.daily.domain.BizFinancialInfo;
import com.ruoyi.daily.service.IBizFinancialInfoService;

/**
 * 收支管理Service业务层处理
 *
 * @author GhostXu
 * @date 2021-09-27
 */
@Service
public class BizFinancialInfoServiceImpl implements IBizFinancialInfoService {
    @Autowired
    private BizFinancialInfoMapper bizFinancialInfoMapper;

    /**
     * 查询收支管理
     *
     * @param financialId 收支管理ID
     * @return 收支管理
     */
    @Override
    public BizFinancialInfo selectBizFinancialInfoById(Long financialId) {
        return bizFinancialInfoMapper.selectBizFinancialInfoById(financialId);
    }

    /**
     * 查询收支管理列表
     *
     * @param bizFinancialInfo 收支管理
     * @return 收支管理
     */
    @Override
    public List<BizFinancialInfo> selectBizFinancialInfoList(BizFinancialInfo bizFinancialInfo) {
        return bizFinancialInfoMapper.selectBizFinancialInfoList(bizFinancialInfo);
    }

    /**
     * 新增收支管理
     *
     * @param bizFinancialInfo 收支管理
     * @return 结果
     */
    @Transactional
    @Override
    public int insertBizFinancialInfo(BizFinancialInfo bizFinancialInfo) {
        int rows = bizFinancialInfoMapper.insertBizFinancialInfo(bizFinancialInfo);
        insertBizFinancialDetail(bizFinancialInfo);
        return rows;
    }

    /**
     * 修改收支管理
     *
     * @param bizFinancialInfo 收支管理
     * @return 结果
     */
    @Transactional
    @Override
    public int updateBizFinancialInfo(BizFinancialInfo bizFinancialInfo) {
        bizFinancialInfoMapper.deleteBizFinancialDetailByFinancialId(bizFinancialInfo.getFinancialId());
        insertBizFinancialDetail(bizFinancialInfo);
        return bizFinancialInfoMapper.updateBizFinancialInfo(bizFinancialInfo);
    }

    /**
     * 批量删除收支管理
     *
     * @param financialIds 需要删除的收支管理ID
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteBizFinancialInfoByIds(Long[] financialIds) {
        bizFinancialInfoMapper.deleteBizFinancialDetailByFinancialIds(financialIds);
        return bizFinancialInfoMapper.deleteBizFinancialInfoByIds(financialIds);
    }

    /**
     * 删除收支管理信息
     *
     * @param financialId 收支管理ID
     * @return 结果
     */
    @Override
    public int deleteBizFinancialInfoById(Long financialId) {
        bizFinancialInfoMapper.deleteBizFinancialDetailByFinancialId(financialId);
        return bizFinancialInfoMapper.deleteBizFinancialInfoById(financialId);
    }

    /**
     * 新增收支明细信息
     *
     * @param bizFinancialInfo 收支管理对象
     */
    public void insertBizFinancialDetail(BizFinancialInfo bizFinancialInfo) {
        List<BizFinancialDetail> bizFinancialDetailList = bizFinancialInfo.getBizFinancialDetailList();
        Long financialId = bizFinancialInfo.getFinancialId();
        if (StringUtils.isNotNull(bizFinancialDetailList)) {
            List<BizFinancialDetail> list = new ArrayList<BizFinancialDetail>();
            for (BizFinancialDetail bizFinancialDetail : bizFinancialDetailList) {
                bizFinancialDetail.setFinancialId(financialId);
                list.add(bizFinancialDetail);
            }
            if (list.size() > 0) {
                bizFinancialInfoMapper.batchBizFinancialDetail(list);
            }
        }
    }
}
