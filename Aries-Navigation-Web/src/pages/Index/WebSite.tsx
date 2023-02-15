import React from 'react';
import ProCard from '@ant-design/pro-card';
import { Avatar, Button, Tooltip } from 'antd';
import type { AnimObjectOrArray } from 'rc-tween-one';
import TweenOne from 'rc-tween-one';
import { create as createClickHistory } from '@/services/aries-navigation/history/web-site-click-history';
import styles from './index.less';

interface WebSiteProps {
  site: API.Web.WebSiteVO;
}

const WebSiteView: React.FC<WebSiteProps> = ({ site }) => {
  const linkBodyRef = React.useRef<HTMLDivElement>(null);
  const [animationPaused, setAnimationPaused] = React.useState(true);
  const [animationTranslateX, setAnimationTranslateX] = React.useState(0);

  const animation: AnimObjectOrArray = {
    duration: 800,
    translateX: animationTranslateX,
  };

  React.useEffect(() => {
    // 若存在滚动条则启用滚动
    if (linkBodyRef.current!.scrollWidth > linkBodyRef.current!.offsetWidth) {
      setAnimationPaused(false);
      setAnimationTranslateX(-(linkBodyRef.current!.scrollWidth - linkBodyRef.current!.offsetWidth));
    }
  }, [site.id]);

  const onMouseDown = (event: Record<string, any>) => {
    // 屏蔽右键
    if (event.button != 2) {
      createClickHistory({ siteId: site.id });
    }
  };

  return (
    <ProCard bordered colSpan={4} type="inner" size="small" split="vertical">
      <ProCard colSpan="64px" layout="center">
        <Avatar src={site.imageUrl} style={{ width: 32, height: 32 }} />
      </ProCard>
      <div className={styles.site_link}>
        <TweenOne animation={animation} paused={animationPaused} repeat={-1} yoyo>
          <ProCard bodyStyle={{ padding: '12px 6px' }} ref={linkBodyRef} >
            <Tooltip title={site.description} placement="bottomLeft">
              <Button onMouseDown={onMouseDown} href={site.siteUrl} target="_blank" type="link">
                {site.name}
              </Button>
            </Tooltip>
          </ProCard>
        </TweenOne>
      </div>
    </ProCard>
  );
};

export default WebSiteView;
