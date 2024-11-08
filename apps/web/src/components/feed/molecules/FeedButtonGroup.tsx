"use client";

import { ButtonWithAuth, withLink } from "@/components/common/atoms";
import {
  Comment,
  Ellipsis,
  Heart,
  Save,
  Share,
} from "@/components/common/icons";
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
      <Heart fill="none" />
      <span>{likeCount}</span>
    </ButtonWithAuth>
  );
}

interface FeedCommentButtonProp {
  commentCount: number;
}

function FeedCommentButton({ commentCount }: FeedCommentButtonProp) {
  return (
    <div className="flex items-center gap-[8px]">
      <Comment /> <span>{commentCount}</span>
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
      <Share />
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
      <Save />
    </ButtonWithAuth>
  );
}

type FeedDropdownButtonProp = BaseFeed;

function FeedDropdownButton({ feedUuid }: FeedDropdownButtonProp) {
  function handleClick() {
    alertNotImplemented({ tag: `${feedUuid}-dropdown: ` });
  }

  return (
    <button type="button" className="flex items-center" onClick={handleClick}>
      <Ellipsis />
    </button>
  );
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
    <div className="flex justify-between">
      <div className="flex gap-[24px]">
        <div className="flex gap-[8px]">
          <FeedButton.Like {...{ feedUuid, likeCount }} />
        </div>
        <div className="flex gap-[8px]">
          <FeedButton.Comment
            {...{ href: `/feeds/${feedUuid}#comments`, commentCount }}
          />
        </div>
        <FeedButton.Share {...{ feedUuid }} />
      </div>
      <div>
        <FeedButton.Save {...{ feedUuid }} />
      </div>
    </div>
  );
}
