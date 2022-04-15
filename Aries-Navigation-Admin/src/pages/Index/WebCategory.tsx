import ProCard from '@ant-design/pro-card';
import WebSiteView from './WebSite';

interface WebCategoryProps {
  category: API.Web.WebCategoryVO;
}

const WebCategoryView: React.FC<WebCategoryProps> = ({ category }) => {
  return (
    <ProCard split="vertical">
      <ProCard>{category.name}</ProCard>
      <ProCard colSpan={24} wrap>
        {category.sites.map((item) => {
          return (
            <ProCard colSpan={4} key={item.id} size="small">
              <WebSiteView site={item} />
            </ProCard>
          );
        })}
      </ProCard>
    </ProCard>
  );
};

export default WebCategoryView;
