import { toURLSearchParams } from "@/functions/utils";
import type {
  DeleteFeedBookmarkReq,
  DeleteShortsBookmarkReq,
  GetFeedBookmarksReq,
  GetShortsBookmarksReq,
  PostFeedBookmarkReq,
  PostShortsBookmarkReq,
} from "@/types/utility-service/bookmark-service";
import type { ShortsUuid } from "@/types/shorts-service";
import type { EmptyObject } from "@/types/common";
import type { FeedUuid } from "@/types/feed-service";
import { getHeaders, processResponse } from "../common";

const API_SERVER = process.env.BASE_API_URL;
const PREFIX = "utility-service";

export async function getShortsBookmarks({ ...query }: GetShortsBookmarksReq) {
  const URI = `${API_SERVER}/${PREFIX}/v1/members/shorts/bookmarks?${toURLSearchParams(query)}`;

  const res: Response = await fetch(URI, {
    headers: await getHeaders(),
    method: "GET",
    cache: "no-cache",
  });

  return processResponse<ShortsUuid, true>({ res });
}

export async function getFeedBookmarks({ ...query }: GetFeedBookmarksReq) {
  const URI = `${API_SERVER}/${PREFIX}/v1/members/feeds/bookmarks?${toURLSearchParams(query)}`;

  const res: Response = await fetch(URI, {
    headers: await getHeaders(),
    method: "GET",
    cache: "no-cache",
  });

  return processResponse<FeedUuid, true>({ res });
}

export async function postShortsBookmark({
  shortsUuid,
}: PostShortsBookmarkReq) {
  const URI = `${API_SERVER}/${PREFIX}/v1/members/shorts/bookmarks}`;

  const res: Response = await fetch(URI, {
    headers: await getHeaders(),
    method: "POST",
    cache: "no-cache",
    body: JSON.stringify({ shortsUuid }),
  });

  return processResponse<EmptyObject, false>({ res });
}

export async function postFeedBookmark({ feedUuid }: PostFeedBookmarkReq) {
  const URI = `${API_SERVER}/${PREFIX}/v1/members/feeds/bookmarks}`;

  const res: Response = await fetch(URI, {
    headers: await getHeaders(),
    method: "POST",
    cache: "no-cache",
    body: JSON.stringify({ feedUuid }),
  });

  return processResponse<EmptyObject, false>({ res });
}

export async function deleteShortsBookmark({
  shortsUuid,
}: DeleteShortsBookmarkReq) {
  const URI = `${API_SERVER}/${PREFIX}/v1/members/shorts/${shortsUuid}/bookmarks}`;

  const res: Response = await fetch(URI, {
    headers: await getHeaders(),
    method: "DELETE",
    cache: "no-cache",
  });

  return processResponse<EmptyObject, false>({ res });
}

export async function deleteFeedBookmark({ feedUuid }: DeleteFeedBookmarkReq) {
  const URI = `${API_SERVER}/${PREFIX}/v1/members/feeds/${feedUuid}/bookmarks}`;

  const res: Response = await fetch(URI, {
    headers: await getHeaders(),
    method: "DELETE",
    cache: "no-cache",
  });

  return processResponse<EmptyObject, false>({ res });
}
