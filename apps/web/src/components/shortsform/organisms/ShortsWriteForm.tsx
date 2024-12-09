"use client";

import { Button } from "@repo/ui/shadcn/button";
import { useRef, useState } from "react";
import { toast } from "sonner";
import type { PostShortsReq } from "@/types/shorts/shorts-service";
import { postShorts } from "@/actions/shorts-service";
import ShortsCreateFormFields from "./ShortsCreateFormFields";

function ShortsWriteForm() {
  const [payload, setPayload] = useState<
    Omit<PostShortsReq, "media"> & Partial<Pick<PostShortsReq, "media">>
  >({
    memberUuid: "test",
    title: "",
    playtime: 0,
    visibility: "VISIBLE", // 기본값
    hashtags: [],
    media: undefined,
  });

  const formRef = useRef<HTMLFormElement>(null);

  const onSubmit = async (event: React.FormEvent) => {
    event.preventDefault(); // 기본 form 제출 방지
    // console.log(payload);
    if (!payload.media) return toast.error("동영상을 업로드해주세요.");
    try {
      await postShorts({ ...(payload as PostShortsReq) });
      toast.success("성공적으로 업로드되었습니다.");
    } catch (error) {
      toast.error((error as Error).message);
    }
  };

  return (
    <form
      ref={formRef}
      className="w-full h-[90%] flex flex-col gap-2 relative"
      onSubmit={(event) => {
        void onSubmit(event);
      }}
    >
      <ShortsCreateFormFields payload={payload} setPayload={setPayload} />
      <Button
        type="submit"
        className="w-[90%] md:w-2/5 text-[1rem] bg-[#47D0BF] py-[25px] rounded-lg text-white text-center mb-20 fixed bottom-10 left-1/2 translate-x-[-50%]"
      >
        Upload now
      </Button>
    </form>
  );
}

export default ShortsWriteForm;
