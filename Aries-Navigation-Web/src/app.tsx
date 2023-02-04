import type { RequestConfig, RunTimeLayoutConfig } from 'umi';
import { history } from 'umi';
import { PageLoading, SettingDrawer } from '@ant-design/pro-layout';
import type { Settings as LayoutSettings } from '@ant-design/pro-layout';
import RightContent from '@/components/RightContent';
import Footer from '@/components/Footer';
import { LinkOutlined } from '@ant-design/icons';
import defaultSettings from '../config/defaultSettings';
import { fetchUserInfo, requestConfig } from '@/utils/app-utils';

const isDev = process.env.NODE_ENV === 'development';
const indexPath = '/';
const loginPath = '/users/login';
const callbackPath = '/users/login/callback';
const nonLoginPaths = [indexPath, loginPath, callbackPath];

/** 获取用户信息比较慢的时候会展示一个 loading */
export const initialStateConfig = {
  loading: <PageLoading />,
};

/**
 * @see  https://umijs.org/zh-CN/plugins/plugin-initial-state
 */
export async function getInitialState(): Promise<{
  settings?: Partial<LayoutSettings>;
  currentUser?: Galaxy.OAuth2.System.UserDTO;
  loading?: boolean;
  fetchUserInfo?: () => Promise<Galaxy.OAuth2.System.UserDTO | undefined>;
}> {
  // 如果是登录页面，不执行
  if (!nonLoginPaths.includes(history.location.pathname)) {
    const currentUser = await fetchUserInfo();
    return {
      settings: defaultSettings,
      currentUser,
      fetchUserInfo,
    };
  }
  return {
    settings: defaultSettings,
    fetchUserInfo,
  };
}

/**
 * 全局Request配置
 */
export const request: RequestConfig = requestConfig;

// ProLayout 支持的api https://procomponents.ant.design/components/layout
export const layout: RunTimeLayoutConfig = ({ initialState, setInitialState }) => {
  return {
    rightContentRender: () => <RightContent />,
    disableContentMargin: false,
    waterMarkProps: {
      content: initialState?.currentUser?.nickname,
    },
    footerRender: () => <Footer />,
    onPageChange: () => {
      const { location } = history;
      // 如果没有登录，重定向到 login
      if (!initialState?.currentUser && !nonLoginPaths.includes(location.pathname)) {
        history.push({
          pathname: loginPath,
          query: {
            redirect: history.location.pathname,
            ...history.location.query,
          },
        });
      }
    },
    links: isDev
      ? [
          <a
            key="github"
            target="_blank"
            href="https://github.com/kuretru/Aries-Navigation/"
            rel="noreferrer"
          >
            <LinkOutlined />
            <span>GitHub</span>
          </a>,
        ]
      : [],
    menuHeaderRender: undefined,
    // 自定义 403 页面
    // unAccessible: <div>unAccessible</div>,
    // 增加一个 loading 的状态
    childrenRender: (children, props) => {
      // if (initialState?.loading) return <PageLoading />;
      return (
        <>
          {children}
          {!props.location?.pathname?.includes('/login') && (
            <SettingDrawer
              enableDarkTheme
              settings={initialState?.settings}
              onSettingChange={(settings) => {
                setInitialState((preInitialState: any) => ({
                  ...preInitialState,
                  settings,
                }));
              }}
            />
          )}
        </>
      );
    },
    ...initialState?.settings,
  };
};
