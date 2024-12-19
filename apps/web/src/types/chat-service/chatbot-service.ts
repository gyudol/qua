import type { CommonPaginationReq } from "../common";

export type ChatbotCharacter = "nimo" | "dori";

export type ChatbotChatRole = "user" | "assistant";

export interface GetChatbotChatReq {
  character: ChatbotCharacter;
  message: string;
}

export interface DeleteChatbotHistoryByCharacterReq {
  character: ChatbotCharacter;
}

export interface DeleteChatbotHistoryByChatRoomUuidReq {
  chatRoomUuid: string;
}

export interface GetChatbotHistoryByCharacterReq extends CommonPaginationReq {
  character: ChatbotCharacter;
}

export interface GetChatbotHistoryByChatRoomUuidReq
  extends CommonPaginationReq {
  chatRoomUuid: string;
}

export interface ChatbotChatRecord {
  memberUuid: string;
  character: ChatbotCharacter;
  role: ChatbotChatRole;
  message: string;
  chatRoomUuid: string;
  createdAt: string;
}

export interface ChatbotChatRoomInfo {
  memberUuid: string;
  character: string;
  chatRoomUuid: string;
}
