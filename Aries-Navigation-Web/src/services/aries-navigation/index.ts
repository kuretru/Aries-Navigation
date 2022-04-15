import { request } from 'umi';

export async function getRecords(): Promise<API.ApiResponse<API.Web.WebTagVO[]>> {
  return request<API.ApiResponse<API.Web.WebTagVO[]>>(`/api/index`, {
    method: 'get',
  });
}
