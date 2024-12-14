"use server";

import type { EmptyObject } from "@/types/common";
import type {
  ChatbotChatRecord,
  ChatbotChatRoomInfo,
  DeleteChatbotHistoryByCharacterReq,
  DeleteChatbotHistoryByChatRoomUuidReq,
  GetChatbotChatReq,
  GetChatbotHistoryByCharacterReq,
  GetChatbotHistoryByChatRoomUuidReq,
} from "@/types/chat-service/chatbot-service";
import { toURLSearchParams } from "@/functions/utils";
import { getHeaders, processResponse } from "../common/index";

const API_SERVER = process.env.BASE_API_URL;
const PREFIX = "chat-service";

export async function getChatbotChat({
  character,
  message,
}: GetChatbotChatReq) {
  const URI = `${API_SERVER}/${PREFIX}/auth/v1/chatrooms/chatbot?${toURLSearchParams({ character, message })}`;

  const res: Response = await fetch(URI, {
    headers: await getHeaders(),
    method: "GET",
    cache: "no-cache",
    // next: { revalidate: 3600 },
  });

  return processResponse<ChatbotChatRecord, false>({ res });
}

export async function getChatbotChatList() {
  const URI = `${API_SERVER}/${PREFIX}/auth/v1/chatrooms/chatbot/chat-list`;
  const res: Response = await fetch(URI, {
    headers: await getHeaders(),
    method: "GET",
    cache: "no-cache",
    // next: { revalidate: 3600 },
  });

  return processResponse<ChatbotChatRoomInfo[], false>({ res });
}

export async function deleteChatbotHistoryByCharacter({
  character,
}: DeleteChatbotHistoryByCharacterReq) {
  const URI = `${API_SERVER}/${PREFIX}/auth/v1/chatrooms/chatbot?${toURLSearchParams({ character })}`;

  const res: Response = await fetch(URI, {
    headers: await getHeaders(),
    method: "DELETE",
    cache: "no-cache",
    // next: { revalidate: 3600 },
  });

  return processResponse<EmptyObject, false>({ res });
}

export async function deleteChatbotHistoryByChatRoomUuid({
  chatRoomUuid,
}: DeleteChatbotHistoryByChatRoomUuidReq) {
  const URI = `${API_SERVER}/${PREFIX}/auth/v1/chatrooms/chatbot?${chatRoomUuid}`;

  const res: Response = await fetch(URI, {
    headers: await getHeaders(),
    method: "DELETE",
    cache: "no-cache",
    // next: { revalidate: 3600 },
  });

  return processResponse<EmptyObject, false>({ res });
}

export async function getChatbotHistoryByCharacter({
  character,
  ...query
}: GetChatbotHistoryByCharacterReq) {
  const URI = `${API_SERVER}/${PREFIX}/auth/v1/chatrooms/chatbot/history?${toURLSearchParams({ character, ...query })}`;

  const res: Response = await fetch(URI, {
    headers: await getHeaders(),
    method: "GET",
    cache: "no-cache",
    // next: { revalidate: 3600 },
  });

  return processResponse<ChatbotChatRecord, true>({ res });
}

export async function getChatbotHistoryByChatRoomUuid({
  chatRoomUuid,
  ...query
}: GetChatbotHistoryByChatRoomUuidReq) {
  const URI = `${API_SERVER}/${PREFIX}/auth/v1/chatrooms/chatbot/history${chatRoomUuid}?${toURLSearchParams(query)}`;

  const res: Response = await fetch(URI, {
    headers: await getHeaders(),
    method: "GET",
    cache: "no-cache",
    // next: { revalidate: 3600 },
  });

  return processResponse<ChatbotChatRecord, true>({ res });
}
