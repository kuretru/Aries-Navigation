import request from '@/utils/request'

export function list(tagId, params) {
  return request({
    url: '/tags/' + tagId + '/categories/',
    method: 'get',
    params
  })
}

export function create(data) {
  return request({
    url: '/tags/' + data.tagId + '/categories/',
    method: 'post',
    data
  })
}

export function update(data) {
  return request({
    url: '/tags/' + data.tagId + '/categories/' + data.id,
    method: 'put',
    data
  })
}

export function remove(tagId, id) {
  return request({
    url: '/tags/' + tagId + '/categories/' + id,
    method: 'delete'
  })
}

export function reorder(tagId, ids) {
  return request({
    url: '/tags/' + tagId + '/categories/reorder',
    method: 'put',
    data: {
      idList: ids
    }
  })
}
