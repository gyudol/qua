import type { Datetime } from "@/types/common";

export interface GetMemberProfileReq {
  nickname: string;
}

export interface MemberProfile {
  memberUuid: string;
  nickname: string;
  profileImageUrl: string;
  createdAt: Datetime;
  point: number;
  grade: string;
  equippedBadge: {
    badgeId: number;
    badgeName: string;
    badgeImageUrl: string;
    badgeDescription: string;
  };
  followerCount: number;
  followingCount: number;
  feedCount: number;
  shortsCount: number;
}
