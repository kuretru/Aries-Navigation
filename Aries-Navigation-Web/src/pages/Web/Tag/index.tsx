import React from 'react';
import { ProFormText } from '@ant-design/pro-form';
import type { ProColumns } from '@ant-design/pro-table';
import BaseSequencePage from '@/components/BaseSequencePage';
import WebTagService from '@/services/aries-navigation/web/web-tag';

class WebTag extends React.Component {
  columns: ProColumns<API.Web.WebTagDTO>[] = [
    {
      align: 'center',
      copyable: true,
      dataIndex: 'name',
      title: '标签名称',
    },
  ];

  formItem = () => {
    return (
      <>
        <ProFormText
          label="标签名称"
          name="name"
          placeholder="请输入名称"
          rules={[{ max: 16, required: true }]}
          tooltip="最长16位"
          width="lg"
        />
      </>
    );
  };

  render() {
    return (
      <BaseSequencePage<API.Web.WebTagDTO, API.Web.WebTagQuery>
        pageName="标签"
        service={new WebTagService()}
        columns={this.columns}
        formItem={this.formItem()}
      />
    );
  }
}

export default WebTag;
