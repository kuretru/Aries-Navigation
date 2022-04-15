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
          title="天秤财富"
          subTitle="可供多人协作的家庭账本"
          initialValues={{ autoLogin: true }}
          onFinish={handleSubmit}
        >
          占位文字
        </LoginForm>
      </div>
      <Footer />
    </div>
  );
};

export default Login;
