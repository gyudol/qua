"use client";

import { useEffect } from "react";
import type { MediaContest } from "@/types/contest/contest";
import ContestUploader from "../molecule/ContestUploader";

function ContestFormFields({
  payload,
  setPayload,
}: {
  payload: Omit<MediaContest, "media"> & Partial<Pick<MediaContest, "media">>;
  setPayload: React.Dispatch<
    React.SetStateAction<
      Omit<MediaContest, "media"> & Partial<Pick<MediaContest, "media">>
    >
  >;
}) {
  useEffect(() => {
    // console.log("Payload updated:", payload);
  }, [payload]);

  return (
    <fieldset className="w-full flex flex-col gap-5 h-full">
      <ul className="border-2 border-dashed p-5 list-disc list-inside text-left mt-5 rounded-lg">
        <li className="pl-6">이미지만 업로드 가능합니다.</li>
        <li className="pl-6">관상어 관련 이미지만 업로드 가능합니다.</li>
        <li className="pl-6">이쁜 사진을 자랑해주세요</li>
      </ul>

      <ContestUploader setPayload={setPayload} />
    </fieldset>
  );
}

export default ContestFormFields;
