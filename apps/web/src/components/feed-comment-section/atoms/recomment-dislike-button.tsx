"use client";

import { ThumbsDown } from "lucide-react";
import { useSession } from "next-auth/react";
import type { Dispatch, SetStateAction } from "react";
import { useEffect, useState } from "react";
import { alertNotImplemented } from "@/functions/utils";
import { getDislikeStatus, postDislike } from "@/actions/utility-service";

interface RecommentDislikeButtonProps {
  recommentUuid: string;
  dislikeCount: number;
  isLikeOrDislike: "like" | "dislike" | "none";
  setIsLikeOrDislike: Dispatch<SetStateAction<"like" | "dislike" | "none">>;
}

export function RecommentDislikeButton({
  recommentUuid,
  dislikeCount,
  isLikeOrDislike,
  setIsLikeOrDislike,
}: RecommentDislikeButtonProps) {
  const { status, data } = useSession();

  const [isDislike, setIsDislike] = useState(false);

  useEffect(() => {
    if (status === "authenticated") {
      void getDislikeStatus({
        kind: "feed-recomment",
        kindUuid: recommentUuid,
      }).then((dislikeStatus) => setIsDislike(dislikeStatus));
    }
  }, [recommentUuid, status]);

  useEffect(() => {
    if (status === "authenticated" && isLikeOrDislike === "like" && isDislike) {
      setIsDislike((DislikeStatus) => !DislikeStatus);
      const session = data as { user: { memberUuid: string } };
      void postDislike({
        kind: "feed-recomment",
        kindUuid: recommentUuid,
        memberUuid: session.user.memberUuid,
      });
    }
  }, [recommentUuid, data, isDislike, isLikeOrDislike, status]);

  function toggleDislike() {
    if (status === "authenticated") {
      setIsDislike((DislikeStatus) => !DislikeStatus);
      setIsLikeOrDislike("dislike");

      const session = data as { user: { memberUuid: string } };

      void postDislike({
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
      onClick={toggleDislike}
      disabled={status === "loading"}
      className="flex"
    >
      <span>
        <ThumbsDown stroke={isDislike ? "pink" : "black"} />
      </span>
      <span>{dislikeCount + Number(isDislike)}</span>
    </button>
  );
}
