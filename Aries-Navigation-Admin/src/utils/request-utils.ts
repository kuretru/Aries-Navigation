function getRequestParam(name: string) {
  const search = decodeURIComponent(window.location.search).replace('?', '');
  const reg = new RegExp(`(^|&)${name}=([^&]*)(&|$)`);
  const result = search.match(reg);
  return result != null ? result[2] : '';
}

function getRequestParams() {
  const result: { [key: string]: any } = {};
  const search = decodeURIComponent(window.location.search).replace('?', '');
  const pairs = search !== '' ? search.split('&') : [];
  pairs.forEach((item) => {
    if (item) {
      const pair = item.split('=');
      result[pair[0]] = pair[1];
    }
  });
  return result;
}

export { getRequestParam, getRequestParams };
