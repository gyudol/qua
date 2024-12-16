import type { Datetime } from "../common";

export interface PutLastReadReq {
  roomUuid: string;
}

export interface GetChatroomByRoomUuidReq {
  roomUuid: string;
}

export interface DeleteChatroomReq {
  roomUuid: string;
}

export interface PostChatroomByCounterPartUuidReq {
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

export interface CounterPartChatroomInfo {
  roomUuid: string;
  participants: ChatroomParticipant[];
  createdAt: string;
  updatedAt: string;
  newRoom: boolean;
}

// ---------------------------------------------

export interface PostChatReq {
  roomUuid: string;
  message: string;
}

export interface GetChatHistoryReq {
  roomUuid: string;
}

export interface GetChatStreamReq {
  roomUuid: string;
}

export interface Chat {
  roomUuid: string;
  messageType: "TEXT";
  message: string;
  senderId: string;
  createdAt: Datetime;
}
