import React from 'react';
import type { ProColumns } from '@ant-design/pro-table';
import { ProFormSelect, ProFormText } from '@ant-design/pro-form';
import type { RequestOptionsType } from '@ant-design/pro-utils';
import WebCategoryService from '@/services/aries-navigation/web/web-category';
import BaseSequencePage from '@/components/BaseSequencePage';
import WebTagService from '@/services/aries-navigation/web/web-tag';

class WebCategory extends React.Component {
  webTagService = new WebTagService();

  fetchWebTags = async () => {
    const response = await this.webTagService.list();
    const result: RequestOptionsType[] = [];
    response.data.forEach((record) => {
      result.push({ label: record.name, value: record.id });
    });
    return result;
  };

  columns: ProColumns<API.Web.WebCategoryDTO>[] = [
    {
      align: 'center',
      key: 'index',
      title: '序号',
      valueType: 'indexBorder',
      width: 60,
    },
    {
      align: 'center',
      copyable: true,
      dataIndex: 'tagId',
      title: '所属标签',
      request: this.fetchWebTags,
      valueType: 'select',
      width: 160,
    },
    {
      align: 'center',
      copyable: true,
      dataIndex: 'name',
      title: '分类名称',
    },
  ];

  formItem = () => {
    return (
      <>
        <ProFormSelect
          label="所属标签"
          name="tagId"
          placeholder="请选择所属标签"
          request={this.fetchWebTags}
          rules={[{ required: true }]}
          width="lg"
        />
        <ProFormText
          label="分类名称"
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
      <BaseSequencePage<API.Web.WebCategoryDTO, API.Web.WebCategoryQuery>
        pageName="分类"
        service={new WebCategoryService()}
        columns={this.columns}
        formItem={this.formItem()}
      />
    );
  }
}

export default WebCategory;