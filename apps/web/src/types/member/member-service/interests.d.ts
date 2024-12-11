import type { MemberReq } from "../common";

export interface CategoryInterestsItem {
  id: number;
}

export type GetCategoryInterests = MemberReq;
export interface PutCategoryInterests extends MemberReq {
  categories: CategoryInterestsItem[];
}
export interface PostCategoryInterests extends MemberReq {
  categories: CategoryInterestsItem[];
}

export interface HashtagInterestsItem {
  name: string;
}

export type GetHashtagInterests = MemberReq;
export interface PutHashtagInterests extends MemberReq {
  hashtags: HashtagInterestsItem[];
}
export interface PostHashtagInterests extends MemberReq {
  hashtags: HashtagInterestsItem[];
}
