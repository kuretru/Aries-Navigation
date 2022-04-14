// @ts-ignore
/* eslint-disable */

declare namespace API.Web {
  type WebTagDTO = API.BaseDTO & {
    name: string;
  };

  type WebTagQuery = API.PaginationQuery & {
    name?: string;
  };

  type WebCategoryDTO = API.BaseDTO & {
    tagId: string;
    name: string;
  };

  type WebCategoryQuery = API.PaginationQuery & {
    name?: string;
  };

  type WebSiteDTO = API.BaseDTO & {
    categoryId: string;
    name: string;
    imageUrl: string;
    siteUrl: string;
    description: string;
  };

  type WebSiteQuery = API.PaginationQuery & {
    name?: string;
  };
}
