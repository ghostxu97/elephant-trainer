package com.ruoyi.web.controller.daily;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.daily.domain.BizFinancialDetail;
import com.ruoyi.daily.service.IBizFinancialDetailService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 收支明细Controller
 *
 * @author GhostXu
 * @date 2021-09-27
 */
@RestController
@RequestMapping("daily/financial/detail")
public class BizFinancialDetailController extends BaseController {
    @Autowired
    private IBizFinancialDetailService bizFinancialDetailService;

    /**
     * 查询收支明细列表
     */
    @PreAuthorize("@ss.hasPermi('daily:financial:detail:list')")
    @GetMapping("/list")
    public TableDataInfo list(BizFinancialDetail bizFinancialDetail) {
        startPage();
        List<BizFinancialDetail> list = bizFinancialDetailService.selectBizFinancialDetailList(bizFinancialDetail);
        return getDataTable(list);
    }

    /**
     * 导出收支明细列表
     */
    @PreAuthorize("@ss.hasPermi('daily:financial:detail:export')")
    @Log(title = "收支明细", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(BizFinancialDetail bizFinancialDetail) {
        List<BizFinancialDetail> list = bizFinancialDetailService.selectBizFinancialDetailList(bizFinancialDetail);
        ExcelUtil<BizFinancialDetail> util = new ExcelUtil<BizFinancialDetail>(BizFinancialDetail.class);
        return util.exportExcel(list, "detail");
    }

    /**
     * 获取收支明细详细信息
     */
    @PreAuthorize("@ss.hasPermi('daily:financial:detail:query')")
    @GetMapping(value = "/{financialDetailId}")
    public AjaxResult getInfo(@PathVariable("financialDetailId") Long financialDetailId) {
        return AjaxResult.success(bizFinancialDetailService.selectBizFinancialDetailById(financialDetailId));
    }

    /**
     * 新增收支明细
     */
    @PreAuthorize("@ss.hasPermi('daily:financial:detail:add')")
    @Log(title = "收支明细", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BizFinancialDetail bizFinancialDetail) {
        return toAjax(bizFinancialDetailService.insertBizFinancialDetail(bizFinancialDetail));
    }

    /**
     * 修改收支明细
     */
    @PreAuthorize("@ss.hasPermi('daily:financial:detail:edit')")
    @Log(title = "收支明细", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BizFinancialDetail bizFinancialDetail) {
        return toAjax(bizFinancialDetailService.updateBizFinancialDetail(bizFinancialDetail));
    }

    /**
     * 删除收支明细
     */
    @PreAuthorize("@ss.hasPermi('daily:financial:detail:remove')")
    @Log(title = "收支明细", businessType = BusinessType.DELETE)
    @DeleteMapping("/{financialDetailIds}")
    public AjaxResult remove(@PathVariable Long[] financialDetailIds) {
        return toAjax(bizFinancialDetailService.deleteBizFinancialDetailByIds(financialDetailIds));
    }
}
