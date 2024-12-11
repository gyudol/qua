export type MemberUuid = string;

export interface MemberReq {
  memberUuid: string;
}

export interface MemberSignInResType {
  memberUuid: string;
  accessToken: string;
  refreshToken: string;
  signUp: boolean;
  signIn: boolean;
}
