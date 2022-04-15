import ProCard from '@ant-design/pro-card';
import WebCategoryView from './WebCategory';

interface WebTagProps {
  tag: API.Web.WebTagVO;
}

const WebTagView: React.FC<WebTagProps> = ({ tag }) => {
  return (
    <ProCard>
      {tag.categories.map((item) => {
        return <WebCategoryView category={item} key={item.id} />;
      })}
    </ProCard>
  );
};

export default WebTagView;
