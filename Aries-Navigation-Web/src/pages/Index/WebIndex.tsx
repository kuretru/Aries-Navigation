import React from 'react';
import { Menu } from 'antd';
import type { MenuInfo } from 'rc-menu/lib/interface';
import { getRecords } from '@/services/aries-navigation';
import WebTagView from './WebTag';

interface IWebIndexProps {}
interface IWebIndexState {
  tags: API.Web.WebTagVO[];
  currentIndex: number;
}
class WebIndex extends React.Component<IWebIndexProps, IWebIndexState> {
  constructor(props: IWebIndexProps) {
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
    <>
      <Menu mode="horizontal" onClick={this.onMenuClick}>
        {this.state.tags.map((item) => {
          return <Menu.Item key={item.id}>{item.name}</Menu.Item>;
        })}
      </Menu>
      {this.state.tags.length > 0 && <WebTagView tag={this.state.tags[this.state.currentIndex]} />}
    </>
  );
}

export default WebIndex;
