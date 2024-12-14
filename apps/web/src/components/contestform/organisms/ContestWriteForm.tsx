"use client";

import { Button } from "@repo/ui/shadcn/button";
import { useRef, useState } from "react";
import { contestPost } from "@/actions/contest/contest";
import type { MediaContest } from "@/types/contest/contest";
import ContestFormFields from "./ContestFormFields";

interface ContestFormProps {
  contestId: number;
}

function ContestWriteForm({ contestId }: ContestFormProps) {
  const [payload, setPayload] = useState<
    Omit<MediaContest, "media"> & Partial<Pick<MediaContest, "media">>
  >({
    contestId,
    media: undefined,
  });

  const formRef = useRef<HTMLFormElement>(null);
  // console.log("제출", payload);
  // console.log("제출", payload.contestId);

  const onSubmit = async (event: React.FormEvent) => {
    event.preventDefault(); // 기본 form 제출 방지

    await contestPost(payload as MediaContest); // 타입 강제 변환
  };

  return (
    <form
      ref={formRef}
      className="w-full h-[90%] flex flex-col gap-2 relative"
      onSubmit={(event) => {
        void onSubmit(event);
      }}
    >
      <ContestFormFields payload={payload} setPayload={setPayload} />
      <Button
        type="submit"
        className="w-[400px] text-[1rem] bg-[#47D0BF] py-[25px] rounded-lg text-white text-center mb-20 fixed bottom-10 left-1/2 translate-x-[-50%]"
      >
        Upload now
      </Button>
    </form>
  );
}

export default ContestWriteForm;
