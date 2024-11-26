"use client";

import {
  BookmarkIcon,
  EllipsisVerticalIcon,
  HeartIcon,
  LucideMessageSquareMore,
  SendIcon,
} from "lucide-react";
import { ButtonWithAuth, withLink } from "@/components/common/atoms";
import { alertNotImplemented } from "@/functions/utils";
import type { BaseFeed, FeedStatistics } from "@/types/contents";

interface FeedLikeButtonProp extends BaseFeed {
  likeCount: number;
}

function FeedLikeButton({ feedUuid, likeCount }: FeedLikeButtonProp) {
  function handleClick() {
    alertNotImplemented({ tag: `${feedUuid}-like: ` });
  }

  return (
    <ButtonWithAuth
      className="flex items-center gap-[8px]"
      onClick={handleClick}
    >
      <HeartIcon size="1rem" color="#B1B1B1" />
      <span className="text-sm text-gray-500">{likeCount}</span>
    </ButtonWithAuth>
  );
}

interface FeedCommentButtonProp {
  commentCount: number;
}

function FeedCommentButton({ commentCount }: FeedCommentButtonProp) {
  return (
    <div className="flex items-center gap-[8px]">
      <LucideMessageSquareMore size="1rem" color="#B1B1B1" />{" "}
      <span className="text-sm text-gray-500">{commentCount}</span>
    </div>
  );
}

type FeedShareButtonProp = BaseFeed;

function FeedShareButton({ feedUuid }: FeedShareButtonProp) {
  function handleClick() {
    alertNotImplemented({ tag: `${feedUuid}-share: ` });
  }

  return (
    <button type="button" className="flex items-center" onClick={handleClick}>
      <SendIcon size="0.95rem" color="#B1B1B1" />
    </button>
  );
}

type FeedSaveButtonProp = BaseFeed;

function FeedSaveButton({ feedUuid }: FeedSaveButtonProp) {
  function handleClick() {
    alertNotImplemented({ tag: `${feedUuid}-save: ` });
  }

  return (
    <ButtonWithAuth className="flex items-center" onClick={handleClick}>
      <BookmarkIcon size="1rem" color="#B1B1B1" />
    </ButtonWithAuth>
  );
}

type FeedDropdownButtonProp = BaseFeed;

function FeedDropdownButton({ feedUuid: _ }: FeedDropdownButtonProp) {
  return <EllipsisVerticalIcon size={16} color="#B1B1B1" />;
}

export const FeedButton = {
  Like: FeedLikeButton,
  Comment: withLink(FeedCommentButton),
  Share: FeedShareButton,
  Save: FeedSaveButton,
  Dropdown: FeedDropdownButton,
};

export default function FeedButtonGroup({
  feedUuid,
  likeCount,
  commentCount,
}: FeedStatistics) {
  return (
    <nav>
      <ul className="flex justify-between">
        <ul className="flex items-center gap-[1.2rem]">
          <li className="flex items-center gap-[4px]">
            <FeedButton.Like {...{ feedUuid, likeCount }} />
          </li>
          <li className="flex items-center gap-[4px]">
            <FeedButton.Comment
              {...{ href: `/feeds/${feedUuid}#comments`, commentCount }}
            />
          </li>
          <li>
            <FeedButton.Share {...{ feedUuid }} />
          </li>
        </ul>
        <li>
          <FeedButton.Save {...{ feedUuid }} />
        </li>
      </ul>
    </nav>
  );
}
