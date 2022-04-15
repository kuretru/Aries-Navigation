import ProCard from '@ant-design/pro-card';
import { Avatar, Button, Tooltip } from 'antd';

interface WebSiteProps {
  site: API.Web.WebSiteVO;
}

const WebSiteView: React.FC<WebSiteProps> = ({ site }) => {
  return (
    <ProCard bordered colSpan={4} type="inner" size="small" split="vertical">
      <ProCard colSpan="64px">
        <Avatar src={site.imageUrl} style={{ width: 32, height: 32 }} />
      </ProCard>
      <ProCard>
        <Tooltip title={site.description} placement="bottomLeft">
          <Button href={site.siteUrl} target="_blank" type="link">
            {site.name}
          </Button>
        </Tooltip>
      </ProCard>
    </ProCard>
  );
};

export default WebSiteView;
