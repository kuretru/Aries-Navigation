import { request } from 'umi';

export async function get(id: string): Promise<API.ApiResponse<Galaxy.OAuth2.System.UserDTO>> {
  return request<API.ApiResponse<Galaxy.OAuth2.System.UserDTO>>(`/api/users/${id}`, {
    method: 'get',
  });
}
