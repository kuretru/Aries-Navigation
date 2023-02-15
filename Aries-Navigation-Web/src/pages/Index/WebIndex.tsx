import React from 'react';
import { GridContent } from '@ant-design/pro-layout';
import ProCard from '@ant-design/pro-card';
import { Menu } from 'antd';
import type { ItemType, MenuInfo } from 'rc-menu/lib/interface';
import { getRecords } from '@/services/aries-navigation';
import WebTagView from './WebTag';
import styles from './index.less';
interface IWebIndexState {
  tags: API.Web.WebTagVO[];
  menuItems: ItemType[];
  currentIndex: number;
}
class WebIndex extends React.Component<Record<string, never>, IWebIndexState> {
  constructor(props: Record<string, never>) {
    super(props);
    this.state = {
      tags: [],
      menuItems: [],
      currentIndex: 0,
    };
    this.fetchData();
  }

  fetchData = () => {
    getRecords().then((response) => {
      const menuItems: ItemType[] = [];
      response.data.forEach((tag: API.Web.WebTagVO) =>
        menuItems.push({ key: tag.id, label: tag.name }),
      );
      this.setState({ tags: response.data, menuItems: menuItems });
    });
  };

  onMenuClick = (props: MenuInfo) => {
    for (let i = 0; i < this.state.tags.length; i++) {
      if (props.key === this.state.tags[i].id) {
        this.setState({ currentIndex: i });
        break;
      }
    }
  };

  render = () => (
    <GridContent>
      <div className={styles.container}>
        <ProCard className={styles.content}>
          <Menu items={this.state.menuItems} mode="horizontal" onClick={this.onMenuClick} />
          {this.state.tags.length > 0 && (
            <WebTagView tag={this.state.tags[this.state.currentIndex]} />
          )}
        </ProCard>
      </div>
    </GridContent>
  );
}

export default WebIndex;
