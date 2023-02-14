// @ts-ignore
/* eslint-disable */

declare namespace API {
  type BaseDTO = {
    id?: string;
  };

  type BaseHistoryDTO = {};

  type ApiResponse<T> = {
    code: number;
    message: string;
    data: T;
  };

  type PaginationQuery = {
    current?: number;
    pageSize?: number;
    keryword?: string;
  };

  type PaginationResponse<T> = {
    list: T[];
    current: number;
    pageSize: number;
    total: number;
  };

  type ProTableData<T> = {
    success: boolean;
    data: T[];
    current: number;
    pageSize: number;
    total: number;
  };
}
