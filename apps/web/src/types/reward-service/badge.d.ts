export interface PutMemberBadgeStatusReq {
  memberUuid: string;
  badgeId: number;
  equipped: boolean;
}

export interface GetAllMemberBadgesReq {
  memberUuid: string;
}

export interface PostMemberBadgeReq {
  memberUuid: string;
  badgeId: number;
}

export interface GetMemberBadgeStatusReq {
  memberUuid: string;
  badgeId: number;
}

export interface BadgeStatus {
  badgeId: number;
  equipped: boolean;
}

export interface Badge {
  id: number;
  name: string;
  imageUrl: string;
  description: string;
}

export interface GetBadgeReq {
  badgeId: number;
}
