import React from 'react';
import { LoginForm } from '@ant-design/pro-form';
import Footer from '@/components/Footer';
import styles from './index.less';
import { galaxyAuthorize } from '@/services/galaxy-oauth2-client/service';

const Login: React.FC = () => {
  const handleSubmit = async () => {
    const record: Galaxy.OAuth2.Client.OAuth2AuthorizeRequestDTO = {
      scopes: ['email'],
    };
    const response = await galaxyAuthorize(record);
    window.location.href = response.data;
  };

  return (
    <div className={styles.container}>
      <div className={styles.content}>
        <LoginForm
          logo={<img alt="logo" src="/logo.svg" />}
          title="白羊导航"
          subTitle="基于Spring Boot+Ant Design Pro的练手项目"
          initialValues={{ autoLogin: true }}
          onFinish={handleSubmit}
        >
          转到【双子·身份验证中心】登录
        </LoginForm>
      </div>
      <Footer />
    </div>
  );
};

export default Login;
