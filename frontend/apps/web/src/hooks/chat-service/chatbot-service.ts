import {
  useInfiniteQuery,
  useMutation,
  useQueryClient,
} from "@tanstack/react-query";
import {
  getChatbotChat,
  getChatbotHistoryByCharacter,
  getChatbotHistoryByChatRoomUuid,
} from "@/actions/chat-service/chatbot-service";
import type {
  ChatbotChatRecord,
  GetChatbotChatReq,
  GetChatbotHistoryByCharacterReq,
  GetChatbotHistoryByChatRoomUuidReq,
} from "@/types/chat-service/chatbot-service";

export function useGetChatbotHistoryByCharacterInfiniteQuery({
  character,
  ...query
}: GetChatbotHistoryByCharacterReq) {
  const { pageNo, pageSize, nextCursor } = query;
  return useInfiniteQuery({
    queryKey: [
      "chat-service",
      {
        type: "chatbot",
        subtype: "chatbot-history",
      },
    ],
    queryFn: ({ pageParam }) =>
      getChatbotHistoryByCharacter({ character, ...pageParam }),
    getNextPageParam: ({
      hasNext,
      ...nextQuery
    }: {
      pageNo: number;
      pageSize: number;
      nextCursor: string;
      hasNext: boolean;
    }) => (hasNext ? { ...nextQuery } : null),
    initialPageParam: {
      pageNo: pageNo || 1,
      pageSize: pageSize || 10,
      nextCursor: nextCursor || undefined,
    },
  });
}

export function useGetChatbotHistoryByChatRoomUuidInfiniteQuery({
  chatRoomUuid,
  ...query
}: GetChatbotHistoryByChatRoomUuidReq) {
  const { pageNo, pageSize, nextCursor } = query;
  return useInfiniteQuery({
    queryKey: [
      "chat-service",
      {
        type: "chatbot",
        subtype: "chatbot-history",
      },
    ],
    queryFn: ({ pageParam }) =>
      getChatbotHistoryByChatRoomUuid({ chatRoomUuid, ...pageParam }),
    getNextPageParam: ({
      hasNext,
      ...nextQuery
    }: {
      pageNo: number;
      pageSize: number;
      nextCursor: string;
      hasNext: boolean;
    }) => (hasNext ? { ...nextQuery } : null),
    initialPageParam: {
      pageNo: pageNo || 1,
      pageSize: pageSize || 10,
      nextCursor: nextCursor || undefined,
    },
  });
}

export function useGetChatbotChatMutation({
  character,
}: Pick<GetChatbotChatReq, "character">) {
  const QC = useQueryClient();
  const queryKey = [
    "chat-service",
    {
      type: "chatbot",
      subtype: "chatbot-history",
    },
  ];
  return useMutation({
    mutationFn: ({ message }: { message: string }) => {
      return getChatbotChat({ character, message });
    },
    onMutate: async ({ message }) => {
      await QC.cancelQueries({ queryKey });
      const prevHistory = [
        ...(QC.getQueryData<ChatbotChatRecord[]>(queryKey) || []),
      ];
      QC.setQueryData<ChatbotChatRecord[]>(queryKey, [
        ...prevHistory,
        {
          character,
          role: "user",
          message,
          createdAt: new Date().toString(),
        } as ChatbotChatRecord,
      ]);
      return { prevHistory };
    },
    onError: (error, variables, context) => {
      QC.setQueryData<ChatbotChatRecord[]>(queryKey, context?.prevHistory);
    },
    onSettled: async () => {
      await QC.invalidateQueries({ queryKey });
    },
  });
}
