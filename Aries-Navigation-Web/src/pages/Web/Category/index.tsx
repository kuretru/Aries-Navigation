import React from 'react';
import { ProFormSelect, ProFormText } from '@ant-design/pro-form';
import type { ProColumns } from '@ant-design/pro-table';
import { Button } from 'antd';
import { SettingOutlined } from '@ant-design/icons';
import { history } from 'umi';
import type { RequestOptionsType } from '@ant-design/pro-utils';
import BaseSequencePage from '@/components/BaseSequencePage';
import WebTagService from '@/services/aries-navigation/web/web-tag';
import WebCategoryService from '@/services/aries-navigation/web/web-category';
import { getRequestParam } from '@/utils/request-utils';

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
      copyable: true,
      dataIndex: 'tagId',
      initialValue: getRequestParam('tagId'),
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
    {
      align: 'center',
      key: 'manager',
      title: '内容管理',
      width: 120,
      render: (_, record) => {
        return [
          <Button
            icon={<SettingOutlined />}
            key="manager"
            onClick={() => this.onManagerButtonClick(record)}
          >
            管理
          </Button>,
        ]
      }
    },
  ];

  onManagerButtonClick = (record: API.Web.WebCategoryDTO) => {
    history.push({
      pathname: '/admin/web/sites',
      query: {
        tagId: record.tagId,
        categoryId: record.id,
      }
    })
  };

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
