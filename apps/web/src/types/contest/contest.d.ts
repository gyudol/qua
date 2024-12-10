export interface ContestListRes {
  contestPostUuid: "string";
}
export interface ContestId {
  contestId: "string";
}
export interface CreateContestType {
  mediaUrl: string;
  mediaType: string;
}

// 현재 콘테스트 타입
export interface ContestContent {
  contestId: number;
  contestName: string;
  imgUrl: string;
}

export interface Contest {
  content: ContestContent[];
  nextCursor: string;
  hasNext: boolean;
  pageSize: number;
  pageNo: number;
}
