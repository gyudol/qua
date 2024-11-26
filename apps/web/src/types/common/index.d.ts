export interface CommonRes<T> {
  httpStatus: string;
  isSuccess: boolean;
  message: string;
  code: number;
  result: T | string;
}

export interface Pagination<T> {
  content: T[];
  nextCursor: number;
  hasNext: boolean;
  pageSize: number;
  pageNo: number;
}

type ResponseType<T, IsPagination extends boolean> = IsPagination extends true
  ? CommonPaginationRes<T>
  : CommonRes<T>;

type ResultType<T, IsPagination extends boolean> = IsPagination extends true
  ? Pagination<T>
  : T;

export type Datetime =
  `${string}-${string}-${string} ${string}:${string}:${string}`;

export type CommonPaginationRes<T> = CommonRes<Pagination<T>>;

export interface CommonPaginationReq {
  nextCursor?: string;
  pageSize?: number;
  pageNo?: number;
}

export type EmptyObject = Record<string, never>;
