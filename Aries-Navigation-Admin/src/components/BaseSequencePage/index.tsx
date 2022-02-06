import React from 'react';
import { PageContainer } from '@ant-design/pro-layout';
import type { ActionType, ProColumns } from '@ant-design/pro-table';
import ProTable from '@ant-design/pro-table';
import { ModalForm, ProFormText } from '@ant-design/pro-form';
import type { FormInstance } from 'antd';
import { Button, message, Modal } from 'antd';
import {
  DeleteOutlined,
  DragOutlined,
  EditOutlined,
  PlusOutlined,
  QuestionCircleOutlined,
  SortAscendingOutlined,
} from '@ant-design/icons';
import type BaseSequenceService from '@/services/aries-navigation/base-sequence-service';
import { SortableContainer, SortableElement, SortableHandle } from 'react-sortable-hoc';
import { arrayMoveImmutable } from 'array-move';
import './drag.less';

const { confirm } = Modal;
const DragHandle = SortableHandle(() => <DragOutlined style={{ color: '#999', cursor: 'grab' }} />);
const SortableItem = SortableElement((props: any) => <tr {...props} />);
const SortContainer = SortableContainer((props: any) => <tbody {...props} />);

interface IBaseSequencePageProps<T extends API.BaseDTO, Q> {
  pageName: string;
  service: BaseSequenceService<T, Q>;
  columns: ProColumns<T>[];
  formItem: JSX.Element;
  onFormValuesChange?: (
    changedValues: any,
    values: T,
    formRef: React.MutableRefObject<FormInstance>,
  ) => void;
}

interface IBaseSequencePageState<T> {
  modalVisible: boolean;
  tableLoading: boolean;
  useLocalData: boolean;
  dataSource: API.ProTableData<T>;
}

class BaseSequencePage<T extends API.BaseDTO, Q> extends React.Component<
  IBaseSequencePageProps<T, Q>,
  IBaseSequencePageState<T>
> {
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
            icon={<EditOutlined />}
            key="edit"
            onClick={() => this.onEditButtonClick(record)}
            type="primary"
          >
            编辑
          </Button>,
          <Button
            danger
            icon={<DeleteOutlined />}
            key="delete"
            onClick={() => this.onDeleteButtonClick(record.id!)}
            type="primary"
          >
            删除
          </Button>,
        ];
      },
    },
    {
      align: 'center',
      dataIndex: 'sort',
      search: false,
      title: '排序',
      width: 60,
      render: () => <DragHandle />,
    },
  ];
  formRef: React.MutableRefObject<FormInstance>;
  tableRef: React.MutableRefObject<ActionType>;

  constructor(props: IBaseSequencePageProps<T, Q>) {
    super(props);
    this.state = {
      modalVisible: false,
      tableLoading: false,
      useLocalData: false,
      dataSource: { success: false, data: [], current: 0, pageSize: 0, total: 0 },
    };
    this.formRef = React.createRef<FormInstance>() as React.MutableRefObject<FormInstance>;
    this.tableRef = React.createRef<ActionType>() as React.MutableRefObject<ActionType>;
  }

  fetchData = async (params: API.PaginationQuery) => {
    if (this.state.useLocalData) {
      this.setState({ useLocalData: false });
      return this.state.dataSource;
    }
    const response = await this.props.service
      .listByPage(params)
      .catch((error: any) => message.error(error.message));
    this.setState({ dataSource: response });
    return response;
  };

  onAddButtonClick = () => {
    this.formRef.current.resetFields();
    this.setState({ modalVisible: true });
  };

  onEditButtonClick = (record: T) => {
    this.formRef.current.setFieldsValue(record);
    this.setState({ modalVisible: true });
  };

  onDeleteButtonClick = (id: string) => {
    const messageKey = 'delete';
    const that = this;
    confirm({
      title: `确定删除这个${this.props.pageName}吗？`,
      icon: <QuestionCircleOutlined />,
      okType: 'danger',
      onOk() {
        message.loading({ content: '请求处理中...', duration: 0, key: messageKey });
        that.props.service
          .remove(id)
          .then(() => {
            that.tableRef.current?.reload();
            message.success({ content: '删除成功！', key: messageKey });
          })
          .catch((error: any) => {
            message.destroy(messageKey);
            message.error(error.message);
          });
      },
    });
  };

  onReorderButtonClick = () => {
    const idList: string[] = [];
    this.state.dataSource.data.forEach((record) => {
      idList.push(record.id!);
    });
    this.props.service
      .reorder(idList)
      .then((response) => {
        message.success(response.data);
      })
      .catch((error: any) => {
        message.error(error.message);
      });
    this.tableRef.current.reload();
  };

  onFormValuesChange = (changedValues: any, values: T) => {
    if (this.props.onFormValuesChange) {
      this.props.onFormValuesChange(changedValues, values, this.formRef);
    }
  };

  onFormFinish = async (record: T) => {
    const messageKey = 'create';
    const that = this;
    let result = false;
    message.loading({ content: '请求处理中...', duration: 0, key: messageKey });
    if (record.id) {
      await this.props.service
        .update(record)
        .then(() => {
          that.tableRef.current?.reload();
          message.success({ content: '修改成功！', key: messageKey });
          result = true;
        })
        .catch((error: any) => {
          message.destroy(messageKey);
          message.error(error.message);
        });
    } else {
      await this.props.service
        .create(record)
        .then(() => {
          that.tableRef.current?.reload();
          message.success({ content: '新增成功！', key: messageKey });
          result = true;
        })
        .catch((error: any) => {
          message.destroy(messageKey);
          message.error(error.message);
        });
    }
    return result;
  };

  render() {
    return (
      <PageContainer>
        <ProTable<T>
          actionRef={this.tableRef}
          bordered
          columns={this.props.columns.concat(this.columnsSuffix)}
          components={{ body: { row: this.DraggableBodyRow, wrapper: this.DraggableContainer } }}
          dataSource={this.state.dataSource.data}
          headerTitle={`${this.props.pageName}管理`}
          loading={this.state.tableLoading}
          options={{ fullScreen: true, setting: true }}
          pagination={{ defaultPageSize: 20 }}
          request={this.fetchData}
          rowKey="id"
          tooltip={`${this.props.pageName}管理`}
          toolBarRender={() => [
            <Button
              key={'add'}
              icon={<PlusOutlined />}
              onClick={this.onAddButtonClick}
              type="primary"
            >
              新增{this.props.pageName}
            </Button>,
            <Button
              key={'reorder'}
              icon={<SortAscendingOutlined />}
              onClick={this.onReorderButtonClick}
            >
              重新排序
            </Button>,
          ]}
        />
        <ModalForm<T>
          formRef={this.formRef}
          onFinish={this.onFormFinish}
          onValuesChange={this.onFormValuesChange}
          onVisibleChange={(visible) => this.setState({ modalVisible: visible })}
          title={
            this.formRef.current?.getFieldValue('id')
              ? `编辑${this.props.pageName}`
              : `新增${this.props.pageName}`
          }
          visible={this.state.modalVisible}
          submitter={{
            render: (props, defaultDoms) => {
              return [
                defaultDoms[0],
                <Button danger key="reset" onClick={() => props.reset()}>
                  重置
                </Button>,
                defaultDoms[1],
              ];
            },
            searchConfig: { resetText: '取消', submitText: '提交' },
          }}
        >
          <ProFormText disabled label="ID" name="id" width="lg" />
          {this.props.formItem}
        </ModalForm>
      </PageContainer>
    );
  }

  onSortEnd = ({ oldIndex, newIndex }: { oldIndex: number; newIndex: number }) => {
    if (oldIndex !== newIndex) {
      const newData = arrayMoveImmutable(
        [...this.state.dataSource.data],
        oldIndex,
        newIndex,
      ).filter((el) => !!el);
      this.state.dataSource.data = newData;
      this.setState({ useLocalData: true });
    }
  };

  DraggableContainer = (props: any) => (
    <SortContainer
      useDragHandle
      disableAutoscroll
      helperClass="row-dragging"
      onSortEnd={this.onSortEnd}
      {...props}
    />
  );

  DraggableBodyRow = (props: any) => {
    const { className, style, ...restProps } = props;
    const index = this.state.dataSource.data.findIndex((x) => x.id === restProps['data-row-key']);
    return <SortableItem index={index} {...restProps} />;
  };
}

export default BaseSequencePage;
