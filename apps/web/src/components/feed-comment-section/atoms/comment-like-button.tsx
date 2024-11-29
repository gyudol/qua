"use client";

import { ThumbsUp } from "lucide-react";
import { useSession } from "next-auth/react";
import type { Dispatch, SetStateAction } from "react";
import { useEffect, useState } from "react";
import { alertNotImplemented } from "@/functions/utils";
import { getLikeStatus, postLike } from "@/actions/utility-service";

interface CommentLikeButtonProps {
  commentUuid: string;
  likeCount: number;
  isLikeOrDislike: "like" | "dislike" | "none";
  setIsLikeOrDislike: Dispatch<SetStateAction<"like" | "dislike" | "none">>;
}

export function CommentLikeButton({
  commentUuid,
  likeCount,
  isLikeOrDislike,
  setIsLikeOrDislike,
}: CommentLikeButtonProps) {
  const { status, data } = useSession();

  const [isLike, setIsLike] = useState(false);

  useEffect(() => {
    if (status === "authenticated") {
      void getLikeStatus({ kind: "feed-comment", kindUuid: commentUuid }).then(
        (likeStatus) => setIsLike(likeStatus),
      );
    }
  }, [commentUuid, status]);

  useEffect(() => {
    if (status === "authenticated" && isLikeOrDislike === "dislike" && isLike) {
      setIsLike((likeStatus) => !likeStatus);
      const session = data as { user: { memberUuid: string } };
      void postLike({
        kind: "feed-comment",
        kindUuid: commentUuid,
        memberUuid: session.user.memberUuid,
      });
    }
  }, [commentUuid, data, isLike, isLikeOrDislike, status]);

  function toggleLike() {
    if (status === "authenticated") {
      setIsLike((likeStatus) => !likeStatus);
      setIsLikeOrDislike("like");

      const session = data as { user: { memberUuid: string } };

      void postLike({
        kind: "feed-comment",
        kindUuid: commentUuid,
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
