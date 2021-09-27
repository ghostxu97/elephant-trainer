/**
 * 通用js方法封装处理
 * Copyright (c) 2019 ruoyi
 */

const baseURL = process.env.VUE_APP_BASE_API

// 日期格式化
export function parseTime(time, pattern) {
	if (arguments.length === 0 || !time) {
		return null
	}
	const format = pattern || '{y}-{m}-{d} {h}:{i}:{s}'
	let date
	if (typeof time === 'object') {
		date = time
	} else {
		if ((typeof time === 'string') && (/^[0-9]+$/.test(time))) {
			time = parseInt(time)
		} else if (typeof time === 'string') {
			time = time.replace(new RegExp(/-/gm), '/');
		}
		if ((typeof time === 'number') && (time.toString().length === 10)) {
			time = time * 1000
		}
		date = new Date(time)
	}
	const formatObj = {
		y: date.getFullYear(),
		m: date.getMonth() + 1,
		d: date.getDate(),
		h: date.getHours(),
		i: date.getMinutes(),
		s: date.getSeconds(),
		a: date.getDay()
	}
	const time_str = format.replace(/{(y|m|d|h|i|s|a)+}/g, (result, key) => {
		let value = formatObj[key]
		// Note: getDay() returns 0 on Sunday
		if (key === 'a') { return ['日', '一', '二', '三', '四', '五', '六'][value] }
		if (result.length > 0 && value < 10) {
			value = '0' + value
		}
		return value || 0
	})
	return time_str
}

// 表单重置
export function resetForm(refName) {
	if (this.$refs[refName]) {
		this.$refs[refName].resetFields();
	}
}

// 添加日期范围
export function addDateRange(params, dateRange, propName) {
	var search = params;
	search.params = {};
	if (null != dateRange && '' != dateRange) {
		if (typeof(propName) === "undefined") {
			search.params["beginTime"] = dateRange[0];
			search.params["endTime"] = dateRange[1];
		} else {
			search.params["begin" + propName] = dateRange[0];
			search.params["end" + propName] = dateRange[1];
		}
	}
	return search;
}

// 回显数据字典
export function selectDictLabel(datas, value) {
	var actions = [];
	Object.keys(datas).some((key) => {
		if (datas[key].dictValue == ('' + value)) {
			actions.push(datas[key].dictLabel);
			return true;
		}
	})
	return actions.join('');
}

// 回显数据字典（字符串数组）
export function selectDictLabels(datas, value, separator) {
	var actions = [];
	var currentSeparator = undefined === separator ? "," : separator;
	var temp = value.split(currentSeparator);
	Object.keys(value.split(currentSeparator)).some((val) => {
		Object.keys(datas).some((key) => {
			if (datas[key].dictValue == ('' + temp[val])) {
				actions.push(datas[key].dictLabel + currentSeparator);
			}
		})
	})
	return actions.join('').substring(0, actions.join('').length - 1);
}

// 通用下载方法
export function download(fileName) {
	window.location.href = baseURL + "/common/download?fileName=" + encodeURI(fileName) + "&delete=" + true;
}

// 字符串格式化(%s )
export function sprintf(str) {
	var args = arguments, flag = true, i = 1;
	str = str.replace(/%s/g, function () {
		var arg = args[i++];
		if (typeof arg === 'undefined') {
			flag = false;
			return '';
		}
		return arg;
	});
	return flag ? str : '';
}

// 转换字符串，undefined,null等转化为""
export function praseStrEmpty(str) {
	if (!str || str == "undefined" || str == "null") {
		return "";
	}
	return str;
}

/**
 * 构造树型结构数据
 * @param {*} data 数据源
 * @param {*} id id字段 默认 'id'
 * @param {*} parentId 父节点字段 默认 'parentId'
 * @param {*} children 孩子节点字段 默认 'children'
 * @param {*} rootId 根Id 默认 0
 */
export function handleTree(data, id, parentId, children, rootId) {
	id = id || 'id'
	parentId = parentId || 'parentId'
	children = children || 'children'
	rootId = rootId || Math.min.apply(Math, data.map(item => { return item[parentId] })) || 0
	//对源数据深度克隆
	const cloneData = JSON.parse(JSON.stringify(data))
	//循环所有项
	const treeData = cloneData.filter(father => {
		let branchArr = cloneData.filter(child => {
			//返回每一项的子级数组
			return father[id] === child[parentId]
		});
		branchArr.length > 0 ? father.children = branchArr : '';
		//返回第一层
		return father[parentId] === rootId;
	});
	return treeData != '' ? treeData : data;
}

/**
 * 乘法（值1，值2,精度值）
 */
 export function multiply(num1, num2, precisio) {
	let mulNum1;
	let len1;
	let mulNum2;
	let len2;
	precisio = precisio === undefined ? 2 : precisio;
	if (num1.toString().indexOf('.') > 0) {
		len1 = num1.toString().split('.')[1].length
      	mulNum1 = (num1 * Math.pow(10, len1));
	} else {
		mulNum1 = num1;
		len1 = 0;
	}
	if (num2.toString().indexOf('.') > 0) {
		len2 = num2.toString().split('.')[1].length
      	mulNum2 = (num2 * Math.pow(10, len2));
	} else {
		mulNum2 = num2;
		len2 = 0;
	}

	let val = (mulNum1 * mulNum2) / (Math.pow(10, len1) * Math.pow(10, len2));
	let value = Math.round(val * Math.pow(10, precisio)) / Math.pow(10, precisio)
	return value;
}

/** 将时间转为文字描述 */
export function formatTime2Disp(dateTimeStamp) {
		
	var minute = 1000 * 60;
	var hour = minute * 60;
	var day = hour * 24;
	var month = day * 30;

	let disp = "";
	if(dateTimeStamp==undefined){
			
		return false;
	}else{
		dateTimeStamp = dateTimeStamp.replace(/\-/g, "/");
		
		var sTime = new Date(dateTimeStamp).getTime();//把时间pretime的值转为时间戳
		
		var now = new Date().getTime();//获取当前时间的时间戳
		
		var diffValue = now - sTime;
		
		if(diffValue < 0){
			console.log("结束日期不能小于开始日期！");
		}
		
		var monthC = diffValue/month;
		var weekC = diffValue/(7*day);
		var dayC = diffValue/day;
		var hourC = diffValue/hour;
		var minC = diffValue/minute;
		
		if(monthC >= 1){
			disp = parseInt(monthC) + "个月前";
		}
		else if(weekC >= 1){
			disp = parseInt(weekC) + "周前";
		}
		else if(dayC >= 1){
			disp = parseInt(dayC) +"天前";
		}
		else if(hourC >= 1){
			disp = parseInt(hourC) +"个小时前";
		}
		else if(minC >= 1){
			disp = parseInt(minC) +"分钟前";
		}else{
			disp = "刚刚";
		}
		return disp;
	}
	
}