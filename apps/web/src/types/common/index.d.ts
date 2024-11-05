export interface CommonRes<T> {
  httpStatus: string;
  isSuccess: boolean;
  message: string;
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

export type Datetime = `${string}-${string}-${string} ${string}:${string}:${string}`;

export type CommonPaginationRes<T> = CommonRes<Pagination<T>>;
