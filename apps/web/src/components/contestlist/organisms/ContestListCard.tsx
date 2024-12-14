"use client";
import React from "react";
import Image from "next/image";
import type { ContestPostList } from "@/types/contest/contest";
import ContestFilter from "../atoms/ContestFilter";

function ContestCard({ media, createdAt, voteCount }: ContestPostList) {
  return (
    <div className="min-h-screen bg-gray-100 pt-5 px-4 border-2">
      <ContestFilter />
      <div className="grid grid-cols-2 gap-4 w-full">
        <div className="flex flex-col">
          <div className="relative h-[200px] overflow-hidden round-t-lg">
            <div className="bg-slate-300 w-full h-full">
              {media.mediaType === "IMAGE" ? (
                <Image
                  src={media.assets.mediaUrl}
                  alt={media.assets.description || "Contest Image"}
                  className="w-full h-full object-cover rounded-lg"
                  width={300}
                  height={300}
                />
              ) : (
                <video
                  className="w-full h-full object-cover rounded-lg"
                  controls
                  src={media.assets.mediaUrl}
                >
                  <track
                    kind="captions"
                    src="path_to_captions.vtt"
                    srcLang="en"
                    label="English"
                    default
                  />
                </video>
              )}
            </div>
          </div>
          <button
            type="button"
            className="py-2 border-2 border-solid rounded-b-lg round-t-lg bg-[#47D0BF] text-white"
          >
            투표하기
          </button>
          <div className="flex justify-between items-center mt-1 text-sm text-gray-500 rounded-t-2xl">
            <span className="text-[0.65rem]">{createdAt}</span>
            <span className="text-[0.65rem]">투표수: {voteCount}</span>
          </div>
        </div>
      </div>
    </div>
  );
}

export default ContestCard;
