import React from 'react';
import { PageContainer } from '@ant-design/pro-layout';
import type { ActionType, ProColumns } from '@ant-design/pro-table';
import ProTable from '@ant-design/pro-table';
import { Button, message, Modal } from 'antd';
import { DeleteOutlined, QuestionCircleOutlined } from '@ant-design/icons';
import type BaseService from '@/services/aries-navigation/base-service';

const { confirm } = Modal;

interface IBasePageProps<T extends API.BaseDTO, Q extends API.PaginationQuery> {
  pageName: string;
  service: BaseService<T, Q>;
  columns: ProColumns<T>[];
  deleteButtonText?: string;
  search?: false;
}

interface IBasePageState {
  tableLoading: boolean;
}

abstract class BaseTableOnlyPage<
  T extends API.BaseDTO,
  Q extends API.PaginationQuery,
> extends React.Component<IBasePageProps<T, Q>, IBasePageState> {
  columnsPrefix: ProColumns<T>[] = [
    {
      align: 'center',
      key: 'index',
      title: '序号',
      valueType: 'indexBorder',
      width: 60,
    },
  ];
  columnsSuffix: ProColumns<T>[] = [
    {
      align: 'center',
      key: 'action',
      title: '操作',
      valueType: 'option',
      width: 240,
      render: (_, record) => {
        return [
          <Button
            danger
            icon={<DeleteOutlined />}
            key="delete"
            onClick={() => this.onDeleteButtonClick(record.id!)}
            type="primary"
          >
            {this.props.deleteButtonText ?? '删除'}
          </Button>,
        ];
      },
    },
  ];
  tableRef: React.RefObject<ActionType>;

  constructor(props: IBasePageProps<T, Q>) {
    super(props);
    this.state = {
      tableLoading: false,
    };
    this.tableRef = React.createRef<ActionType>();
  }

  fetchData = async (params: API.PaginationQuery) => {
    return this.props.service
      .listByPage(params)
      .catch((error: any) => message.error(error.message));
  };

  onDeleteButtonClick = (id: string) => {
    const messageKey = 'delete';
    const service = this.props.service;
    const tableRef = this.tableRef;
    confirm({
      title: `确定删除这个${this.props.pageName}吗？`,
      icon: <QuestionCircleOutlined />,
      okType: 'danger',
      onOk() {
        message.loading({ content: '请求处理中...', duration: 0, key: messageKey });
        service
          .remove(id)
          .then(() => {
            tableRef.current?.reload();
            message.success({ content: '删除成功！', key: messageKey });
          })
          .catch((error: any) => {
            message.destroy(messageKey);
            message.error(error.message);
          });
      },
    });
  };

  render() {
    return (
      <PageContainer>
        <ProTable<T>
          actionRef={this.tableRef}
          bordered
          columns={this.columnsPrefix.concat(this.props.columns).concat(this.columnsSuffix)}
          headerTitle={`${this.props.pageName}`}
          loading={this.state.tableLoading}
          options={{ fullScreen: true, setting: true }}
          pagination={{ defaultPageSize: 20 }}
          request={this.fetchData}
          rowKey="id"
          search={this.props?.search}
          tooltip={`${this.props.pageName}`}
        />
      </PageContainer>
    );
  }
}

export default BaseTableOnlyPage;
