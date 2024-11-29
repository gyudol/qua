"use client";

import { ThumbsUp } from "lucide-react";
import { useSession } from "next-auth/react";
import type { Dispatch, SetStateAction } from "react";
import { useEffect, useState } from "react";
import { alertNotImplemented } from "@/functions/utils";
import { getLikeStatus, postLike } from "@/actions/utility-service";

interface RecommentLikeButtonProps {
  recommentUuid: string;
  likeCount: number;
  isLikeOrDislike: "like" | "dislike" | "none";
  setIsLikeOrDislike: Dispatch<SetStateAction<"like" | "dislike" | "none">>;
}

export function RecommentLikeButton({
  recommentUuid,
  likeCount,
  isLikeOrDislike,
  setIsLikeOrDislike,
}: RecommentLikeButtonProps) {
  const { status, data } = useSession();

  const [isLike, setIsLike] = useState(false);

  useEffect(() => {
    if (status === "authenticated") {
      void getLikeStatus({
        kind: "feed-recomment",
        kindUuid: recommentUuid,
      }).then((likeStatus) => setIsLike(likeStatus));
    }
  }, [recommentUuid, status]);

  useEffect(() => {
    if (status === "authenticated" && isLikeOrDislike === "dislike" && isLike) {
      setIsLike((likeStatus) => !likeStatus);
      const session = data as { user: { memberUuid: string } };
      void postLike({
        kind: "feed-recomment",
        kindUuid: recommentUuid,
        memberUuid: session.user.memberUuid,
      });
    }
  }, [recommentUuid, data, isLike, isLikeOrDislike, status]);

  function toggleLike() {
    if (status === "authenticated") {
      setIsLike((likeStatus) => !likeStatus);
      setIsLikeOrDislike("like");

      const session = data as { user: { memberUuid: string } };

      void postLike({
        kind: "feed-recomment",
        kindUuid: recommentUuid,
        memberUuid: session.user.memberUuid,
      });
    } else if (status === "unauthenticated") {
      alertNotImplemented({ message: "로그인 하세요" });
    }
  }

  return (
    <button
      type="button"
      onClick={toggleLike}
      disabled={status === "loading"}
      className="flex"
    >
      <span>
        <ThumbsUp stroke={isLike ? "skyblue" : "black"} />
      </span>
      <span>{likeCount + Number(isLike)}</span>
    </button>
  );
}
