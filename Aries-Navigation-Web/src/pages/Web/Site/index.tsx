import React from 'react';
import type { ProColumns } from '@ant-design/pro-table';
import { ProFormSelect, ProFormText } from '@ant-design/pro-form';
import type { RequestOptionsType } from '@ant-design/pro-utils';
import { Image } from 'antd';
import WebSiteService from '@/services/aries-navigation/web/web-site';
import BaseSequencePage from '@/components/BaseSequencePage';
import WebCategoryService from '@/services/aries-navigation/web/web-category';

class WebSite extends React.Component {
  webCategoryService = new WebCategoryService();

  fetchWebCategories = async () => {
    const response = await this.webCategoryService.list();
    const result: RequestOptionsType[] = [];
    response.data.forEach((record) => {
      result.push({ label: record.name, value: record.id });
    });
    return result;
  };

  columns: ProColumns<API.Web.WebSiteDTO>[] = [
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
      dataIndex: 'categoryId',
      title: '所属分类',
      request: this.fetchWebCategories,
      valueType: 'select',
      width: 160,
    },
    {
      align: 'center',
      copyable: true,
      dataIndex: 'imageUrl',
      search: false,
      title: '站点图标',
      width: 80,
      render: (_, entity) => <Image src={entity.imageUrl} width={25} height={25} />,
    },
    {
      align: 'center',
      copyable: true,
      dataIndex: 'name',
      title: '站点名称',
      width: 160,
      render: (_, entity) => (
        <a href={entity.siteUrl} rel="noreferrer" target="_blank">
          {entity.name}
        </a>
      ),
    },
    {
      copyable: true,
      dataIndex: 'description',
      search: false,
      title: '站点描述',
    },
  ];

  formItem = () => {
    return (
      <>
        <ProFormSelect
          label="所属分类"
          name="categoryId"
          placeholder="请选择所属标签"
          request={this.fetchWebCategories}
          rules={[{ required: true }]}
          width="lg"
        />
        <ProFormText
          label="站点名称"
          name="name"
          placeholder="请输入名称"
          rules={[{ max: 16, required: true }]}
          tooltip="最长16位"
          width="lg"
        />
        <ProFormText
          label="站点链接"
          name="siteUrl"
          placeholder="请输入站点链接"
          rules={[{ max: 200, required: true }]}
          tooltip="最长200位"
          width="lg"
        />
        <ProFormText
          label="站点图标"
          name="imageUrl"
          placeholder="请输入站点图标链接"
          rules={[{ max: 250, required: true }]}
          tooltip="最长250位"
          width="lg"
        />
        <ProFormText
          label="站点描述"
          name="description"
          placeholder="请输入站点描述"
          rules={[{ max: 50, required: true }]}
          tooltip="最长50位"
          width="lg"
        />
      </>
    );
  };

  render() {
    return (
      <BaseSequencePage<API.Web.WebSiteDTO, API.Web.WebSiteQuery>
        pageName="站点"
        service={new WebSiteService()}
        columns={this.columns}
        formItem={this.formItem()}
      />
    );
  }
}

export default WebSite;
