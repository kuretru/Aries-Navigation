import ProCard from '@ant-design/pro-card';
import WebSiteView from './WebSite';
import styles from './index.less';

interface WebCategoryProps {
  category: API.Web.WebCategoryVO;
}

const WebCategoryView: React.FC<WebCategoryProps> = ({ category }) => {
  return (
    <ProCard bordered split="vertical" key={category.id}>
      <div className={styles.category_head}>{category.name}</div>
      <ProCard className={styles.category_body} colSpan={24} wrap>
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
