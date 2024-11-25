export interface Member {
  memberUuid: string;
  nickname: string;
  grade: string;
  profileImageUrl: string;
}

export interface MemberSignInResType {
  memberUuid: string;
  member: Member;
  accessToken: string;
  refreshToken: string;
}
