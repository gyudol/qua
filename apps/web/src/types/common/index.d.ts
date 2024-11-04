export interface CommonRes<T> {
  httpStatus: string;
  isSuccess: boolean;
  messaged: string;
  code: number;
  result: T;
}

export interface Pagination<T> {
  content: T[];
  nextCursor: number;
  hasNext: boolean;
  pageSize: number;
  pageNo: number;
}

export type CommonPaginationRes<T> = CommonRes<Pagination<T>>;
