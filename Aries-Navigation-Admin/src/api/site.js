import request from '@/utils/request'

export function list(tagId, categoryId, params) {
  return request({
    url: '/tags/' + tagId + '/categories/' + categoryId + '/sites/',
    method: 'get',
    params
  })
}

export function create(data) {
  delete data.tagList
  delete data.categoryList
  return request({
    url: '/tags/' + data.tagId + '/categories/' + data.categoryId + '/sites/',
    method: 'post',
    data
  })
}

export function update(data) {
  delete data.tagList
  delete data.categoryList
  return request({
    url: '/tags/' + data.tagId + '/categories/' + data.categoryId + '/sites/' + data.id,
    method: 'put',
    data
  })
}

export function remove(tagId, categoryId, id) {
  return request({
    url: '/tags/' + tagId + '/categories/' + categoryId + '/sites/' + id,
    method: 'delete'
  })
}

export function reorder(tagId, categoryId, ids) {
  return request({
    url: '/tags/' + tagId + '/categories/' + categoryId + '/sites/reorder',
    method: 'put',
    data: {
      idList: ids
    }
  })
}

export function fetchFavicon(tagId, categoryId, siteUrl) {
  return request({
    url: '/tags/' + tagId + '/categories/' + categoryId + '/sites/favicon',
    method: 'post',
    data: {
      url: siteUrl
    }
  })
}
