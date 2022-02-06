import { request } from 'umi';
import BaseService from './base-service';

abstract class BaseSequenceService<T extends API.BaseDTO, Q> extends BaseService<T, Q> {
  async reorder(idList: string[]): Promise<API.ApiResponse<string>> {
    return request<API.ApiResponse<string>>(`${API_URL}${this.url}/reorder`, {
      method: 'put',
      data: idList,
    });
  }
}

export default BaseSequenceService;
