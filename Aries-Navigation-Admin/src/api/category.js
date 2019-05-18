import request from '@/utils/request'

export function listByTagId(tagId, params) {
  return request({
    url: '/tags/' + tagId + '/categories',
    method: 'get',
    params
  })
}

export function create(data) {
  return request({
    url: '/tags/',
    method: 'post',
    data
  })
}

export function update(data) {
  return request({
    url: '/tags/' + data.id,
    method: 'put',
    data
  })
}

export function remove(id) {
  return request({
    url: '/tags/' + id,
    method: 'delete'
  })
}

export function reorder(ids) {
  return request({
    url: '/tags/reorder',
    method: 'put',
    data: {
      idList: ids
    }
  })
}
