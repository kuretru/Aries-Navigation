import axios from 'axios'
import { Message } from 'element-ui'
import store from '@/store'
import { getToken } from '@/utils/auth'

const service = axios.create({
  baseURL: process.env.VUE_APP_BASE_API,
  withCredentials: true,
  timeout: 5000
})

service.interceptors.request.use(
  config => {
    if (store.getters.token) {
      config.headers['Access-Token'] = getToken()
    }
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
      duration: 5 * 1000
    })

    if (res.code === 4011) {
      store.dispatch('user/resetToken').then(() => {
        location.reload()
      })
    }
    return Promise.reject(error)
  }
)

export default service
