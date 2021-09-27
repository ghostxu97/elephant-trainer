import request from '@/utils/request'

// 查询收支明细列表
export function listDetail(query) {
  return request({
    url: '/daily/financial/detail/list',
    method: 'get',
    params: query
  })
}

// 查询收支明细详细
export function getDetail(financialDetailId) {
  return request({
    url: '/daily/financial/detail/' + financialDetailId,
    method: 'get'
  })
}

// 新增收支明细
export function addDetail(data) {
  return request({
    url: '/daily/financial/detail',
    method: 'post',
    data: data
  })
}

// 修改收支明细
export function updateDetail(data) {
  return request({
    url: '/daily/financial/detail',
    method: 'put',
    data: data
  })
}

// 删除收支明细
export function delDetail(financialDetailId) {
  return request({
    url: '/daily/financial/detail/' + financialDetailId,
    method: 'delete'
  })
}

// 导出收支明细
export function exportDetail(query) {
  return request({
    url: '/daily/financial/detail/export',
    method: 'get',
    params: query
  })
}