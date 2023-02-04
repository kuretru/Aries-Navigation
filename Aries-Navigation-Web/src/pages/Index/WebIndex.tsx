import React from 'react';
import { GridContent } from '@ant-design/pro-layout';
import ProCard from '@ant-design/pro-card';
import { Menu } from 'antd';
import type { MenuInfo } from 'rc-menu/lib/interface';
import { getRecords } from '@/services/aries-navigation';
import WebTagView from './WebTag';
import styles from './index.less';
interface IWebIndexState {
  tags: API.Web.WebTagVO[];
  currentIndex: number;
}
class WebIndex extends React.Component<Record<string, never>, IWebIndexState> {
  constructor(props: Record<string, never>) {
    super(props);
    this.state = {
      tags: [],
      currentIndex: 0,
    };
    this.fetchData();
  }

  fetchData = () => {
    getRecords().then((response) => {
      this.setState({ tags: response.data });
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
          <Menu mode="horizontal" onClick={this.onMenuClick}>
            {this.state.tags.map((item) => {
              return <Menu.Item key={item.id}>{item.name}</Menu.Item>;
            })}
          </Menu>
          {this.state.tags.length > 0 && (
            <WebTagView tag={this.state.tags[this.state.currentIndex]} />
          )}
        </ProCard>
      </div>
    </GridContent>
  );
}

export default WebIndex;
