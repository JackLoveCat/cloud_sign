import http from '@/utils/httpRequest'
export function fetchList ({params}) {
  return http({
    url: '/api/dictionary/list',
    method: 'get',
    params: params
  })
}
export function addEntity (data) {
  return http({
    url: '/api/dictionary/save',
    method: 'post',
    data: data
  })
}

export function deleteEntity (id) {
  return http({
    url: '/api/dictionary/delete?ids=' + id,
    method: 'get'
  })
}

export function getEntity (id) {
  return http({
    url: '/api/dictionary/get?id=' + id,
    method: 'get'
  })
}

export function updateEntity (data) {
  return http({
    url: '/api/dictionary/update',
    method: 'post',
    data: data
  })
}
