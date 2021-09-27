import request from '@/utils/request'

// 查询收支管理列表
export function listInfo(query) {
  return request({
    url: '/daily/financial/info/list',
    method: 'get',
    params: query
  })
}

// 查询收支管理详细
export function getInfo(financialInfoId) {
  return request({
    url: '/daily/financial/info/' + financialInfoId,
    method: 'get'
  })
}

// 新增收支管理
export function addInfo(data) {
  return request({
    url: '/daily/financial/info',
    method: 'post',
    data: data
  })
}

// 修改收支管理
export function updateInfo(data) {
  return request({
    url: '/daily/financial/info',
    method: 'put',
    data: data
  })
}

// 删除收支管理
export function delInfo(financialInfoId) {
  return request({
    url: '/daily/financial/info/' + financialInfoId,
    method: 'delete'
  })
}

// 导出收支管理
export function exportInfo(query) {
  return request({
    url: '/daily/financial/info/export',
    method: 'get',
    params: query
  })
}