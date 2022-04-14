import { request } from 'umi';

abstract class BaseService<T extends API.BaseDTO, Q extends API.PaginationQuery> {
  protected url: string;

  constructor(url: string) {
    this.url = url;
  }

  async get(id: string): Promise<API.ApiResponse<T>> {
    return request<API.ApiResponse<T>>(`/api${this.url}/${id}`, {
      method: 'get',
    });
  }

  camelToUnderscore(params?: API.PaginationQuery) {
    if (params?.pageSize) {
      params['page_size'] = params.pageSize;
      delete params.pageSize;
    }
  }

  async list(params?: Q): Promise<API.ApiResponse<T[]>> {
    this.camelToUnderscore(params);
    return request<API.ApiResponse<T[]>>(`/api${this.url}`, {
      method: 'get',
      params,
    });
  }

  async listByPage(params: API.PaginationQuery): Promise<API.ProTableData<T>> {
    this.camelToUnderscore(params);
    return request<API.ApiResponse<API.PaginationResponse<T>>>(`/api${this.url}`, {
      method: 'get',
      params,
    }).then((response: API.ApiResponse<API.PaginationResponse<T>>) => {
      return {
        success: true,
        data: response.data.list,
        current: response.data.current,
        pageSize: response.data.pageSize,
        total: response.data.total,
      };
    });
  }

  async create(record: T): Promise<API.ApiResponse<T>> {
    return request<API.ApiResponse<T>>(`/api${this.url}`, {
      method: 'post',
      data: record,
    });
  }

  async update(record: T): Promise<API.ApiResponse<T>> {
    return request<API.ApiResponse<T>>(`/api${this.url}/${record.id}`, {
      method: 'put',
      data: record,
    });
  }

  async remove(id: string): Promise<API.ApiResponse<string>> {
    return request<API.ApiResponse<string>>(`/api${this.url}/${id}`, {
      method: 'delete',
    });
  }
}

export default BaseService;
