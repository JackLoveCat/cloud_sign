import http from '@/utils/httpRequest'
export function listMenu (params) {
  return http({
    url: http.adornUrl('system/menu/list'),
    method: 'get',
    params: params
  })
}
export function treeMenu (params) {
  return http({
    url: http.adornUrl('system/menu/treeselect'),
    method: 'get',
    params: params
  })
}
export function addMenu (params) {
  return http({
    url: http.adornUrl('system/menu'),
    method: 'post',
    params: params
  })
}
export function editMenu (params) {
  return http({
    url: http.adornUrl('system/menu'),
    method: 'put',
    params: params
  })
}
export function deleteMenu (id) {
  return http({
    url: http.adornUrl('system/menu/' + id),
    method: 'delete'
  })
}
