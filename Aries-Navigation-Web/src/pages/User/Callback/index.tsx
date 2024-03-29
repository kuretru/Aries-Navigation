import React from 'react';
import { history, useModel } from 'umi';
import ProSkeleton from '@ant-design/pro-skeleton';
import { message } from 'antd';
import { galaxyCallback } from '@/services/galaxy-oauth2-client/service';
import { getRequestParams } from '@/utils/request-utils';

const Callback: React.FC = () => {
  const { initialState, setInitialState } = useModel('@@initialState');

  const fetchUserInfo = async () => {
    const userInfo = await initialState?.fetchUserInfo?.();

    if (userInfo) {
      await setInitialState((state: any) => ({ ...state, currentUser: userInfo }));
    }
  };

  const login = async () => {
    if (localStorage.getItem('alreadyLogin')) {
      // setInitialState()后本页面会刷新一次，重走一遍所有的逻辑，
      // 这里不得已而为之，等待更优雅的解决方法
      localStorage.removeItem('alreadyLogin');
      return;
    }

    const requestParams = getRequestParams();
    const record: Galaxy.OAuth2.Client.OAuth2AuthorizeResponseDTO = {
      code: requestParams.code,
      state: requestParams.state,
    };
    const response = await galaxyCallback(record);
    console.log(response);

    if (response.code === 100) {
      const defaultLoginSuccessMessage = '登录成功！';
      message.success(defaultLoginSuccessMessage);
      localStorage.setItem('userId', response.data.userId);
      localStorage.setItem('accessTokenId', response.data.accessToken.id);
      localStorage.setItem('accessToken', response.data.accessToken.secret);
      localStorage.setItem('alreadyLogin', 'true');

      await fetchUserInfo();

      // 向redirect跳转并传递其余query参数
      if (!history) return;
      const { query } = history.location;
      const { redirect } = query as {
        redirect: string;
      };
      if (redirect) {
        if (query) {
          delete query.redirect;
        }
        history.push({
          pathname: redirect,
          query: query,
        });
      } else {
        history.push('/welcome');
      }
    }
  };

  login();

  return (
    <div
      style={{
        background: '#fafafa',
        padding: 24,
      }}
    >
      <ProSkeleton type="result" />
    </div>
  );
};

export default Callback;
