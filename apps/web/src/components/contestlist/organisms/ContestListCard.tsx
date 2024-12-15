"use client";
import React from "react";
import Image from "next/image";
import type { ContestPostList, ContestPostVote } from "@/types/contest/contest";
import { contestPostVote } from "@/actions/contest/contest";

function ContestCard({
  media,
  createdAt,
  voteCount,
  contestId,
  postUuid,
}: ContestPostList & { contestId: number; postUuid: string }) {
  const handleVote = async () => {
    try {
      // API 호출을 위한 데이터 정의
      const voteData: ContestPostVote = {
        contestId,
        postUuid,
      };

      const response = await contestPostVote(voteData);
      // console.log("투표", response);

      // 응답 성공 여부 처리
      if (response.isSuccess) {
        // alert("투표가 완료되었습니다!");
      } else {
        // alert(`투표에 실패했습니다: ${response.message}`);
      }
    } catch (error) {
      // console.error("투표 중 오류 발생:", error);
      // alert("투표 중 오류가 발생했습니다. 다시 시도해주세요.");
    }
  };

  return (
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
              unoptimized
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
        onClick={() => {
          void handleVote();
        }}
      >
        투표하기
      </button>

      <div className="flex justify-between items-center mt-1 text-sm text-gray-500 rounded-t-2xl">
        <span className="text-[0.65rem]">{createdAt}</span>
        <span className="text-[0.65rem]">투표수: {voteCount}</span>
      </div>
    </div>
  );
}

export default ContestCard;
