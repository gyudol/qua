"use client";

import { useRef, useState } from "react";
import { toast } from "sonner";
import { useRouter } from "next/navigation";
import type { CreateFeedType } from "@/types/request/requestType";
import { createFeed } from "@/actions/feed-service";
import { feedFormSchema } from "@/schema/FeedFormSchema";
import FeedCreateFormFields from "./FeedCreateFormFields";

function FeedWriteFrom() {
  const router = useRouter();
  const [errors, setErrors] = useState<Record<string, string>>({});
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

    const validation = feedFormSchema.safeParse(payload);
    if (validation.success) {
      await createFeed(payload);

      toast.success("성공적으로 피드가 생성되었습니다.");
      router.push("/");
    } else {
      const validationErrors = validation.error.issues.reduce(
        (acc, issue) => ({
          ...acc,
          [issue.path[0]]: issue.message,
        }),
        {},
      );
      setErrors(validationErrors);
    }
  };

  return (
    <section className="w-full px-[1rem] py-[2rem] flex justify-center items-center">
      <form
        ref={formRef}
        className="w-full flex flex-col gap-4"
        onSubmit={(event) => {
          void onSubmit(event);
        }}
      >
        <FeedCreateFormFields
          {...{ errors, setErrors }}
          payload={payload}
          setPayload={setPayload}
        />
        <button
          type="submit"
          className="
        w-full text-[1rem] h-[2rem]
        flex justify-center items-center
        bg-teal-400 py-[25px] 
        rounded-lg text-white"
        >
          Upload now
        </button>
      </form>
    </section>
  );
}

export default FeedWriteFrom;
