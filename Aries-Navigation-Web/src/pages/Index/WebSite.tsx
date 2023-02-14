import ProCard from '@ant-design/pro-card';
import { Avatar, Button, Tooltip } from 'antd';
import { create as createClickHistory } from '@/services/aries-navigation/history/web-site-click-history';
import styles from './index.less';

interface WebSiteProps {
  site: API.Web.WebSiteVO;
}

const WebSiteView: React.FC<WebSiteProps> = ({ site }) => {
  const onMouseDown = (event: Record<string, any>) => {
    if (event.button != 2) {
      // 屏蔽右键
      createClickHistory({ siteId: site.id });
    }
  };

  return (
    <ProCard bordered colSpan={4} type="inner" size="small" split="vertical">
      <ProCard colSpan="64px" layout="center">
        <Avatar src={site.imageUrl} style={{ width: 32, height: 32 }} />
      </ProCard>
      <div className={styles.site_link}>
        <ProCard bodyStyle={{ padding: "12px 6px" }}>
          <Tooltip title={site.description} placement="bottomLeft">
            <Button onMouseDown={onMouseDown} href={site.siteUrl} target="_blank" type="link">
              {site.name}
            </Button>
          </Tooltip>
        </ProCard>
      </div>
    </ProCard>
  );
};

export default WebSiteView;
