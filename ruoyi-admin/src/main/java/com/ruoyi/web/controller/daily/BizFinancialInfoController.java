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
import com.ruoyi.daily.domain.BizFinancialInfo;
import com.ruoyi.daily.service.IBizFinancialInfoService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 收支管理Controller
 *
 * @author GhostXu
 * @date 2021-09-27
 */
@RestController
@RequestMapping("/daily/financial/info")
public class BizFinancialInfoController extends BaseController {
    @Autowired
    private IBizFinancialInfoService bizFinancialInfoService;

    /**
     * 查询收支管理列表
     */
    @PreAuthorize("@ss.hasPermi('daily:financial:info:list')")
    @GetMapping("/list")
    public TableDataInfo list(BizFinancialInfo bizFinancialInfo) {
        startPage();
        List<BizFinancialInfo> list = bizFinancialInfoService.selectBizFinancialInfoList(bizFinancialInfo);
        return getDataTable(list);
    }

    /**
     * 导出收支管理列表
     */
    @PreAuthorize("@ss.hasPermi('daily:financial:info:export')")
    @Log(title = "收支管理", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(BizFinancialInfo bizFinancialInfo) {
        List<BizFinancialInfo> list = bizFinancialInfoService.selectBizFinancialInfoList(bizFinancialInfo);
        ExcelUtil<BizFinancialInfo> util = new ExcelUtil<BizFinancialInfo>(BizFinancialInfo.class);
        return util.exportExcel(list, "financial");
    }

    /**
     * 获取收支管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('daily:financial:info:query')")
    @GetMapping(value = "/{financialId}")
    public AjaxResult getInfo(@PathVariable("financialId") Long financialId) {
        return AjaxResult.success(bizFinancialInfoService.selectBizFinancialInfoById(financialId));
    }

    /**
     * 新增收支管理
     */
    @PreAuthorize("@ss.hasPermi('daily:financial:info:add')")
    @Log(title = "收支管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BizFinancialInfo bizFinancialInfo) {
        return toAjax(bizFinancialInfoService.insertBizFinancialInfo(bizFinancialInfo));
    }

    /**
     * 修改收支管理
     */
    @PreAuthorize("@ss.hasPermi('daily:financial:info:edit')")
    @Log(title = "收支管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BizFinancialInfo bizFinancialInfo) {
        return toAjax(bizFinancialInfoService.updateBizFinancialInfo(bizFinancialInfo));
    }

    /**
     * 删除收支管理
     */
    @PreAuthorize("@ss.hasPermi('daily:financial:info:remove')")
    @Log(title = "收支管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{financialIds}")
    public AjaxResult remove(@PathVariable Long[] financialIds) {
        return toAjax(bizFinancialInfoService.deleteBizFinancialInfoByIds(financialIds));
    }
}
