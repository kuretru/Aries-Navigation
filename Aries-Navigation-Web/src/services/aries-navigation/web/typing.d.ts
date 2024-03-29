// @ts-ignore
/* eslint-disable */

declare namespace API.Web {
  type WebTagDTO = API.BaseDTO & {
    name: string;
    hidden: boolean;
  };

  type WebTagQuery = API.PaginationQuery & {
    name?: string;
    hidden?: boolean;
  };

  type WebTagVO = {
    id: string;
    name: string;
    hidden: boolean;
    categories: WebCategoryVO[];
  };

  type WebCategoryDTO = API.BaseDTO & {
    tagId: string;
    name: string;
  };

  type WebCategoryQuery = API.PaginationQuery & {
    tagId?: string;
    name?: string;
  };

  type WebCategoryVO = {
    id: string;
    name: string;
    sites: WebSiteVO[];
  };

  type WebSiteDTO = API.BaseDTO & {
    categoryId: string;
    name: string;
    imageUrl: string;
    siteUrl: string;
    description: string;
  };

  type WebSiteQuery = API.PaginationQuery & {
    categoryId?: string;
    name?: string;
  };

  type WebSiteVO = {
    id: string;
    name: string;
    imageUrl: string;
    siteUrl: string;
    description: string;
  };
}
