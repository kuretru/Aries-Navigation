import { request } from 'umi';

export async function create(record: API.History.WebSiteClickHistoryDTO): Promise<API.ApiResponse<string>> {
  return request<API.ApiResponse<string>>('/api/sites/history', {
    method: 'post',
    data: record,
  });
}
