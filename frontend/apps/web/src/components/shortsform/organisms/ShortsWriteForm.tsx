"use client";

import { Button } from "@repo/ui/shadcn/button";
import { useRef, useState } from "react";
import { toast } from "sonner";
import { useRouter } from "next/navigation";
import type { PostShortsReq } from "@/types/shorts/shorts-service";
import { postShorts } from "@/actions/shorts-service";
import { useSessionContext } from "@/context/SessionContext";
import ShortsCreateFormFields from "./ShortsCreateFormFields";

function ShortsWriteForm() {
  const router = useRouter();
  const { memberUuid } = useSessionContext();
  const [payload, setPayload] = useState<
    Omit<PostShortsReq, "media"> & Partial<Pick<PostShortsReq, "media">>
  >({
    memberUuid: memberUuid || "",
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
    if (payload.title === "") return toast.error("제목을 입력해주세요.");
    try {
      await postShorts({ ...(payload as PostShortsReq) });
      toast.success("성공적으로 업로드되었습니다.");
      router.push("/");
    } catch (error) {
      toast.error((error as Error).message);
    }
  };

  return (
    <section className="w-full p-[1rem] flex justify-center items-center">
      <form
        ref={formRef}
        className="px-[1rem] py-[2rem] size-full flex flex-col gap-2"
        onSubmit={(event) => {
          void onSubmit(event);
        }}
      >
        <ShortsCreateFormFields payload={payload} setPayload={setPayload} />
        <Button
          type="submit"
          className="
        w-full text-[1rem] 
        bg-teal-400 py-[25px] 
        rounded-lg text-white"
        >
          Upload now
        </Button>
      </form>
    </section>
  );
}

export default ShortsWriteForm;
