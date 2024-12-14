export interface PutLastReadReq {
  roomUuid: string;
}

export interface GetChatroomByRoomUuidReq {
  roomUuid: string;
}

export interface DeleteChatroomReq {
  roomUuid: string;
}

export interface GetChatroomByCounterPartUuidReq {
  counterPartUuid: string;
}

export interface ChatroomParticipant {
  memberUuid: string;
  deleteStatus: boolean;
  deletedAt: string | null;
  readTimeStamp: string;
}

export interface ChatroomInfo {
  roomUuid: string;
  participants: ChatroomParticipant[];
  createdAt: string;
  updatedAt: string;
  lastMessage: string | null;
  lastMessageTime: string | null;
  unreadCount: number;
}

export interface CouterPartChatroomInfo {
  roomUuid: string;
  participants: ChatroomParticipant[];
  createdAt: string;
  updatedAt: string;
  newRoom: boolean;
}

// 챠탕 매사자 전송운 그냥 쌩 문자열

export type MessageType = "TEXT";
