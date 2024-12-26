"use server";
import type {
  Chat,
  ChatroomInfo,
  CounterPartChatroomInfo,
  DeleteChatroomReq,
  GetChatHistoryReq,
  GetChatroomByRoomUuidReq,
  PostChatReq,
  PostChatroomByCounterPartUuidReq,
  PutLastReadReq,
} from "@/types/chat-service/chat-service";
import type { EmptyObject } from "@/types/common";
import { getHeaders, processResponse } from "../common/index";

const API_SERVER = process.env.BASE_API_URL;
const PREFIX = "chat-service";

export async function putLastRead({ roomUuid }: PutLastReadReq) {
  const URI = `${API_SERVER}/${PREFIX}/auth/v1/chatrooms/${roomUuid}/read`;

  const res: Response = await fetch(URI, {
    headers: await getHeaders(),
    method: "PUT",
    cache: "no-cache",
  });

  return processResponse<EmptyObject, false>({ res });
}

export async function getChatroomByRoomUuid({
  roomUuid,
}: GetChatroomByRoomUuidReq) {
  const URI = `${API_SERVER}/${PREFIX}/auth/v1/chatrooms/${roomUuid}`;

  const res: Response = await fetch(URI, {
    headers: await getHeaders(),
    method: "GET",
    cache: "no-cache",
  });

  return processResponse<EmptyObject, false>({ res });
}

export async function deleteChatroom({ roomUuid }: DeleteChatroomReq) {
  const URI = `${API_SERVER}/${PREFIX}/auth/v1/chatrooms/${roomUuid}`;

  const res: Response = await fetch(URI, {
    headers: await getHeaders(),
    method: "DELETE",
    cache: "no-cache",
  });

  return processResponse<EmptyObject, false>({ res });
}

export async function postChatroomByCounterPartUuid({
  counterPartUuid,
}: PostChatroomByCounterPartUuidReq) {
  const URI = `${API_SERVER}/${PREFIX}/auth/v1/chatrooms/counterpart/${counterPartUuid}`;
  const res: Response = await fetch(URI, {
    headers: await getHeaders(),
    method: "POST",
    cache: "no-cache",
  });

  return processResponse<CounterPartChatroomInfo, false>({ res });
}

export async function getAllMyChatrooms() {
  const URI = `${API_SERVER}/${PREFIX}/auth/v1/chatrooms/my`;

  const res: Response = await fetch(URI, {
    headers: await getHeaders(),
    method: "GET",
    cache: "no-cache",
  });

  return processResponse<ChatroomInfo[], false>({ res });
}

// ------------------------------------
export async function postChat({ roomUuid, message }: PostChatReq) {
  const URI = `${API_SERVER}/${PREFIX}/auth/v1/chatrooms/${roomUuid}`;

  const res: Response = await fetch(URI, {
    headers: await getHeaders(),
    method: "POST",
    cache: "no-cache",
    body: JSON.stringify(message),
  });

  return processResponse<EmptyObject, false>({ res });
}

export async function getChatHistory({ roomUuid }: GetChatHistoryReq) {
  const URI = `${API_SERVER}/${PREFIX}/auth/v1/chatrooms/${roomUuid}/previous`;

  const res: Response = await fetch(URI, {
    headers: await getHeaders(),
    method: "GET",
    cache: "no-cache",
  });

  return (await res.json()) as Chat[];
}
