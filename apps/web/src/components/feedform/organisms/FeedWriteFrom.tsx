"use client";

import { Button } from "@repo/ui/shadcn/button";
import { useRef, useState } from "react";
import { toast } from "sonner";
import { useRouter } from "next/navigation";
import type { CreateFeedType } from "@/types/request/requestType";
import { createFeed } from "@/actions/feed-service";
import FeedCreateFormFields from "./FeedCreateFormFields";

function FeedWriteFrom() {
  const router = useRouter();
  const [payload, setPayload] = useState<CreateFeedType>({
    memberUuid: "test",
    title: "",
    content: "",
    categoryName: "", // 기본값을 빈 문자열로 설정
    visibility: "VISIBLE", // 기본값
    hashtags: [],
    mediaList: [],
  });

  const formRef = useRef<HTMLFormElement>(null);

  const onSubmit = async (event: React.FormEvent) => {
    event.preventDefault(); // 기본 form 제출 방지

    await createFeed(payload);

    toast.success("성공적으로 피드가 생성되었습니다.");
    router.push("/");
  };

  return (
    <section className="size-full flex justify-center items-center">
      <form
        ref={formRef}
        className="px-[1rem] py-[2rem] size-full flex flex-col gap-2"
        onSubmit={(event) => {
          void onSubmit(event);
        }}
      >
        <FeedCreateFormFields payload={payload} setPayload={setPayload} />
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

export default FeedWriteFrom;
