import { ErrorShowType, history } from 'umi';
import type { RequestConfig } from 'umi';
import type { RequestOptionsInit } from 'umi-request';
import { get as getUser } from '@/services/galaxy-oauth2-client/user';

const loginPath = '/users/login';

/**
 * 请求用户信息
 * @returns 用户信息
 */
const fetchUserInfo = async () => {
  try {
    const userId = localStorage.getItem('userId');
    if (!userId) return undefined;
    const msg = await getUser(userId);
    return msg.data;
  } catch (error) {
    history.push({
      pathname: loginPath,
      query: {
        redirect: history.location.pathname,
        ...history.location.query,
      },
    });
  }
  return undefined;
};

/**
 * Request的AccessToken拦截器
 * @param url 原始URL
 * @param options 原始选项
 * @returns 附加过AccessToken的请求
 */
const accessTokenInterceptor = (url: string, options: RequestOptionsInit) => {
  const id = localStorage.getItem('accessTokenId');
  if (!id) {
    return {
      url: `${url}`,
      options: options,
    };
  }
  const authHeader = {
    'Access-Token-ID': id,
    'Access-Token': localStorage.getItem('accessToken')!,
  };
  return {
    url: `${url}`,
    options: { ...options, interceptors: true, headers: authHeader },
  };
};

/**
 * 全局Request配置
 */
const requestConfig: RequestConfig = {
  errorConfig: {
    adaptor: (resData: any) => {
      if (resData.code && resData.code >= 10000) {
        return {
          ...resData,
          success: false,
          errorCode: String(resData.code),
          errorMessage: `${resData.message}: ${resData.data}`,
          showType: ErrorShowType.ERROR_MESSAGE,
        };
      }
      return {
        ...resData,
        success: true,
      };
    },
  },
  requestInterceptors: [accessTokenInterceptor],
};

export { fetchUserInfo, requestConfig };
