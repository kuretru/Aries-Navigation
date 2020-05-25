import axios from 'axios'
import { Message } from 'element-ui'

const service = axios.create({
    baseURL: process.env.VUE_APP_BASE_API,
    withCredentials: true,
    timeout: 2000
})

service.interceptors.request.use(
    config => {
        return config
    },
    error => {
        console.log(error)
        return Promise.reject(error)
    }
)

service.interceptors.response.use(
    response => {
        const res = response.data
        console.log(res)
        return res
    },
    error => {
        const res = error.response.data
        if (res.code) {
            console.log(res.code + '-' + res.message + '：' + res.data)
        } else {
            console.log(res.status + '-' + res.error + '：' + res.message)
        }
        Message({
            message: res.data,
            type: 'error',
            duration: 3 * 1000
        })
        return Promise.reject(error)
    }
)

export default service
