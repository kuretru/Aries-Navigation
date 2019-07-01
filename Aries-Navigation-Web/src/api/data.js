import request from '../utils/request'

export function list() {
  return request({
    url: 'http://127.0.0.1:7010/api/data',
    method: 'get'
  })
}
