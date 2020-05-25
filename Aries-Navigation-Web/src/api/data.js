import request from '../utils/request'

export function list() {
    return request({
        url: '/api/data?use_cache=false',
        method: 'get'
    })
}
