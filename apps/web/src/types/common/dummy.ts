import type { CommonPaginationRes, CommonRes, Pagination } from ".";

export function commonRes<T>(result: T): CommonRes<T> {
  return {
    httpStatus: "OK",
    isSuccess: true,
    message: "요청에 성공하였습니다.",
    code: 200,
    result,
  };
}

interface PaginationParam<T> {
  content: T[];
  pageSize: number;
  pageNo: number;
}

export function pagination<T>({
  content,
  pageSize,
  pageNo,
}: PaginationParam<T>): Pagination<T> {
  return {
    content,
    nextCursor: -1,
    hasNext: true,
    pageSize,
    pageNo,
  };
}

export function commonPaginationRes<T>(
  args: PaginationParam<T>,
): CommonPaginationRes<T> {
  return commonRes(pagination(args));
}
