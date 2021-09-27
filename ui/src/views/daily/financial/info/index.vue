<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="登记日期">
        <el-date-picker
          v-model="daterangeRecordDate"
          size="small"
          style="width: 240px"
          value-format="yyyy-MM-dd"
          type="daterange"
          range-separator="-"
          start-placeholder="开始日期"
          end-placeholder="结束日期"
        ></el-date-picker>
      </el-form-item>
      <el-form-item label="收入金额" prop="revenueMoney">
        <el-input
          v-model="queryParams.revenueMoney"
          placeholder="请输入收入金额"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="支出金额" prop="expenditureMoney">
        <el-input
          v-model="queryParams.expenditureMoney"
          placeholder="请输入支出金额"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['daily:financial:info:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['daily:financial:info:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['daily:financial:info:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['daily:financial:info:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="infoList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="收支记录编号" align="center" prop="financialId" />
      <el-table-column label="登记日期" align="center" prop="recordDate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.recordDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="收入金额" align="center" prop="revenueMoney" />
      <el-table-column label="支出金额" align="center" prop="expenditureMoney" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['daily:financial:info:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['daily:financial:info:remove']"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    
    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改收支管理对话框 -->
    <el-dialog :title="title" :visible.sync="open"  width="70%" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
          <el-row>
              <el-col :span="8">
                <el-form-item label="登记日期" prop="recordDate">
                    <el-date-picker clearable size="small"
                        v-model="form.recordDate"
                        type="date"
                        value-format="yyyy-MM-dd"
                        placeholder="选择登记日期">
                    </el-date-picker>
                </el-form-item>
              </el-col>
              <el-col :span="5">
                <el-form-item label="收入金额" prop="revenueMoney">
                    <el-input v-model="form.revenueMoney" readonly />
                </el-form-item>
              </el-col>
              <el-col :offset="3" :span="5">
                <el-form-item label="支出金额" prop="expenditureMoney">
                    <el-input v-model="form.expenditureMoney" readonly />
                </el-form-item>
              </el-col>
          </el-row>
        <el-divider content-position="center">收支明细信息</el-divider>
        <el-row :gutter="10" class="mb8">
          <el-col :span="1.5">
            <el-button type="primary" icon="el-icon-plus" size="mini" @click="handleAddBizFinancialDetail('1')">添加收入记录</el-button>
          </el-col>
          <el-col :span="1.5">
            <el-button type="warning" icon="el-icon-plus" size="mini" @click="handleAddBizFinancialDetail('2')">添加支出记录</el-button>
          </el-col>
          <el-col :span="1.5">
            <el-button type="danger" icon="el-icon-delete" size="mini" @click="handleDeleteBizFinancialDetail">删除</el-button>
          </el-col>
        </el-row>
        <el-table :data="bizFinancialDetailList" :row-class-name="rowBizFinancialDetailIndex" @selection-change="handleBizFinancialDetailSelectionChange" ref="bizFinancialDetail">
          <el-table-column type="selection" width="50" align="center" />
          <el-table-column label="序号" align="center" prop="index" width="50"/>
          <el-table-column label="收支类型" prop="financialType">
            <template slot-scope="scope">
                <el-select v-model="scope.row.financialType"
                    filterable 
                    placeholder="请选择收支类型"
                    @change="((val)=>{handleAddBizFinancialDetail(scope.row)})"
                >
                    <el-option
                        v-for="dict in financialTypeOptions"
                        :key="dict.dictValue"
                        :label="dict.dictLabel"
                        :value="dict.dictValue"
                    >
                    </el-option>
                </el-select>
            </template>
          </el-table-column>
          <el-table-column label="类目名" prop="categoryName">
            <template slot-scope="scope">
              <el-input v-model="scope.row.categoryName" placeholder="请输入类目名" />
            </template>
          </el-table-column>
          <el-table-column label="金额" prop="money">
            <template slot-scope="scope">
              <el-input type="number" @change="completePrice(scope.row)" v-model="scope.row.money" placeholder="0" />
            </template>
          </el-table-column>
          <el-table-column label="备注" prop="remark">
            <template slot-scope="scope">
              <el-input v-model="scope.row.remark" placeholder="请输入备注" />
            </template>
          </el-table-column>
        </el-table>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>


<script>
import { listInfo, getInfo, delInfo, addInfo, updateInfo, exportInfo } from "@/api/daily/financial/info";

export default {
  name: "Info",
  components: {
  },
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 子表选中数据
      checkedBizFinancialDetail: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 收支管理表格数据
      infoList: [],
      // 收支明细表格数据
      bizFinancialDetailList: [],
      // 收支类型数据字典
      financialTypeOptions: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 登记日期时间范围
      daterangeRecordDate: [],
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        recordDate: null,
        revenueMoney: null,
        expenditureMoney: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        recordDate: [
          { required: true, message: "登记日期不能为空", trigger: "blur" }
        ],
        revenueMoney: [
          { required: true, message: "收入金额不能为空", trigger: "blur" }
        ],
        expenditureMoney: [
          { required: true, message: "支出金额不能为空", trigger: "blur" }
        ]
      }
    };
  },
  created() {
    this.getList();
    this.getDicts("biz_financial_type").then(response => {
      this.financialTypeOptions = response.data;
    });
  },
  methods: {
    /** 查询收支管理列表 */
    getList() {
      this.loading = true;
      this.queryParams.params = {};
      if (null != this.daterangeRecordDate && '' != this.daterangeRecordDate) {
        this.queryParams.params["beginRecordDate"] = this.daterangeRecordDate[0];
        this.queryParams.params["endRecordDate"] = this.daterangeRecordDate[1];
      }
      listInfo(this.queryParams).then(response => {
        this.infoList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 计算订单明细金额
    completePrice(row) {
      // console.log(this.bizOrderDetailList);
      if (isNaN(row.money)) {
        this.$alert("该行数据输入有误，请输入阿拉伯数字。", "错误提示", { confirmButtonText: "确定", });
      }
      let revenueAmount = 0;
      let expenditureAmount = 0;
      this.bizFinancialDetailList.forEach(detail => {
          if (detail.financialType === '1') {
              revenueAmount += detail.money - 0;// -0 是为了防止数字计算时被当成字符串拼接
          } else {
              expenditureAmount += detail.money - 0;// -0 是为了防止数字计算时被当成字符串拼接
          }
      });
      revenueAmount = this.multiply(revenueAmount, 1, 2);
      expenditureAmount = this.multiply(expenditureAmount, 1, 2);
      this.form.revenueMoney = revenueAmount;
      this.form.expenditureMoney = expenditureAmount;
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        financialId: null,
        recordDate: null,
        revenueMoney: null,
        expenditureMoney: null
      };
      this.bizFinancialDetailList = [];
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.daterangeRecordDate = [];
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.financialId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加收支管理";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const financialId = row.financialId || this.ids
      getInfo(financialId).then(response => {
        this.form = response.data;
        this.bizFinancialDetailList = response.data.bizFinancialDetailList;
        this.open = true;
        this.title = "修改收支管理";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          this.form.bizFinancialDetailList = this.bizFinancialDetailList;
          if (this.form.financialId != null) {
            updateInfo(this.form).then(response => {
              this.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addInfo(this.form).then(response => {
              this.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const financialIds = row.financialId || this.ids;
      this.$confirm('是否确认删除收支管理编号为"' + financialIds + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delInfo(financialIds);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        })
    },
	/** 收支明细序号 */
    rowBizFinancialDetailIndex({ row, rowIndex }) {
      row.index = rowIndex + 1;
    },
    /** 收支明细添加按钮操作 */
    handleAddBizFinancialDetail(type) {
      let obj = {};
      obj.financialType = type;
      obj.categoryName = "";
      obj.money = "";
      obj.remark = "";
      this.bizFinancialDetailList.push(obj);
    },
    /** 收支明细删除按钮操作 */
    handleDeleteBizFinancialDetail() {
      if (this.checkedBizFinancialDetail.length == 0) {
        this.$alert("请先选择要删除的收支明细数据", "提示", { confirmButtonText: "确定", });
      } else {
        this.bizFinancialDetailList.splice(this.checkedBizFinancialDetail[0].index - 1, 1);
      }
    },
    /** 单选框选中数据 */
    handleBizFinancialDetailSelectionChange(selection) {
      if (selection.length > 1) {
        this.$refs.bizFinancialDetail.clearSelection();
        this.$refs.bizFinancialDetail.toggleRowSelection(selection.pop());
      } else {
        this.checkedBizFinancialDetail = selection;
      }
    },
    /** 导出按钮操作 */
    handleExport() {
      const queryParams = this.queryParams;
      this.$confirm('是否确认导出所有收支管理数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return exportInfo(queryParams);
        }).then(response => {
          this.download(response.msg);
        })
    }
  }
};
</script>
