import { useState } from 'react';
import { Menu } from 'antd';
import type { MenuInfo } from 'rc-menu/lib/interface';
import { getRecords } from '@/services/aries-navigation';
import WebTagView from './WebTag';

const WebIndex: React.FC = () => {
  const [tags, setTags] = useState<API.Web.WebTagVO[]>([]);
  const [currentIndex, setCurrentIndex] = useState<number>(0);

  getRecords().then((response) => {
    setTags(response.data);
  });

  const onMenuClick = (props: MenuInfo) => {
    for (let i = 0; i < tags.length; i++) {
      if (props.key === tags[i].id) {
        setCurrentIndex(i);
        break;
      }
    }
  };

  return (
    <>
      <Menu mode="horizontal" onClick={onMenuClick}>
        {tags.map((item) => {
          return <Menu.Item key={item.id}>{item.name}</Menu.Item>;
        })}
      </Menu>
      {tags.length > 0 && <WebTagView tag={tags[currentIndex]} />}
    </>
  );
};

export default WebIndex;
