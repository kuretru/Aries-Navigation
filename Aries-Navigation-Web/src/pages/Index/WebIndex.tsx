import React from 'react';
import { GridContent } from '@ant-design/pro-layout';
import ProCard from '@ant-design/pro-card';
import { Menu } from 'antd';
import type { ItemType, MenuInfo } from 'rc-menu/lib/interface';
import { getRecords } from '@/services/aries-navigation';
import WebTagView from './WebTag';
import styles from './index.less';
import { SwapOutlined } from '@ant-design/icons';
interface IWebIndexState {
  tags: API.Web.WebTagVO[];
  menuItems: ItemType[];
  currentIndex: number;
  showHiddenTag: boolean;
}
class WebIndex extends React.Component<Record<string, never>, IWebIndexState> {
  constructor(props: Record<string, never>) {
    super(props);
    this.state = {
      tags: [],
      menuItems: [],
      currentIndex: 0,
      showHiddenTag: false,
    };
    this.fetchData().then(() => this.renderMenu(this.state.showHiddenTag));
  }

  fetchData = async () => {
    await getRecords().then((response) => {
      this.setState({ tags: response.data });
    });
  };

  renderMenu = (showHiddenTag: boolean) => {
    const menuItems: ItemType[] = [];
    this.state.tags.forEach((tag) => {
      if (!tag.hidden || tag.hidden == showHiddenTag) {
        menuItems.push({ key: tag.id, label: tag.name });
      }
    })
    menuItems.push({ itemIcon: <SwapOutlined />, key: 'switch' });
    this.setState({ menuItems: menuItems, showHiddenTag: showHiddenTag });
  }

  onMenuClick = (props: MenuInfo) => {
    if (props.key == 'switch') {
      this.renderMenu(!this.state.showHiddenTag);
      return;
    }
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
