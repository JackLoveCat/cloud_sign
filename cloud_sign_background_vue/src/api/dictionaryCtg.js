import http from '@/utils/httpRequest'
export function fetchList (params) {
  return http({
    url: '/api/dictionaryCtg/list',
    method: 'get',
    params: params
  })
}
export function addEntity (data) {
  return http({
    url: '/api/dictionaryCtg/save',
    method: 'post',
    data: data
  })
}

export function deleteEntity (id) {
  return http({
    url: '/api/dictionaryCtg/delete?ids=' + id,
    method: 'get'
  })
}

export function getEntity (id) {
  return http({
    url: '/api/dictionaryCtg/get?id=' + id,
    method: 'get'
  })
}

export function updateEntity (data) {
  return http({
    url: '/api/dictionaryCtg/update',
    method: 'post',
    data: data
  })
}
