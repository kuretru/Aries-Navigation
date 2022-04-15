import { request } from 'umi';

export async function galaxyAuthorize(record: Galaxy.OAuth2.Client.OAuth2AuthorizeRequestDTO) {
  return request('/oauth2/galaxy/authorize', {
    method: 'POST',
    data: record,
  });
}

export async function galaxyCallback(record: Galaxy.OAuth2.Client.OAuth2AuthorizeResponseDTO) {
  return request('/oauth2/galaxy/callback', {
    method: 'POST',
    data: record,
  });
}
