"use client";
import { createContext, useContext } from "react";

export interface FeedCommentDrawerData {
  open?: boolean;
  setOpen?: React.Dispatch<React.SetStateAction<boolean>>;
}

export const FeedCommentDrawerContext = createContext<FeedCommentDrawerData>(
  {} as FeedCommentDrawerData,
);

export const useFeedCommentDrawerContext = () =>
  useContext(FeedCommentDrawerContext);
