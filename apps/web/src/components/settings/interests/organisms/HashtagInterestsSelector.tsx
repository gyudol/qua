"use client";

import React, { useState } from "react";
import { XIcon } from "lucide-react";
import type {
  HashtagInterestsItem,
  PutHashtagInterests,
} from "@/types/member/member-service";
import { QuaInputUI } from "@/components/common/atoms/QuaInputUi";
import type { FeedHashtag } from "@/types/request/requestType";
import { usePutHashtagInterestsMutation } from "@/hooks/member-service";

type HashtagInterestsSelectorProps = PutHashtagInterests;

export default function HashtagInterestsSelector({
  memberUuid,
  hashtags,
}: HashtagInterestsSelectorProps) {
  const [payload, setPayload] = useState<{ hashtags: HashtagInterestsItem[] }>({
    hashtags,
  });
  const [tagsInput, setTagsInput] = useState<string>("");
  const [isComposing, setIsComposing] = useState<boolean>(false);

  const handleChange = (
    event: React.ChangeEvent<
      HTMLInputElement | HTMLTextAreaElement | HTMLSelectElement
    >,
  ) => {
    const { name, value } = event.target;
    // console.log('name', name, value);
    // console.log('payload', payload);

    if (name === "tags") {
      setTagsInput(value); // 태그 입력 필드 값만 업데이트
    } else {
      const updatedPayload = {
        ...payload,
        [name]: value,
      };
      setPayload(updatedPayload); // payload 업데이트
      // validatePayload(updatedPayload); // Zod 스키마로 검증
    }
  };

  const handleAddTag = () => {
    if (tagsInput.trim() === "") return;

    const newTag = { name: tagsInput.trim() };
    if (payload.hashtags.some((tag) => tag.name === newTag.name)) {
      setTagsInput(""); // 중복 태그는 추가하지 않음
      return;
    }

    // console.log('newTag', newTag);

    const updatedTags = [...payload.hashtags, newTag];
    const updatedPayload = {
      ...payload,
      hashtags: updatedTags,
    };

    setTagsInput(""); // 입력 필드 초기화
    setPayload(updatedPayload); // payload 업데이트
    // validatePayload(updatedPayload); // Zod 스키마로 검증
  };

  const handleDeleteTag = (index: number) => {
    const updatedTags = payload.hashtags.filter((_, i) => i !== index);
    const updatedPayload = {
      ...payload,
      hashtags: updatedTags,
    };

    setPayload(updatedPayload); // payload 업데이트
    // validatePayload(updatedPayload); // Zod 스키마로 검증
  };

  const handleKeyDown = (event: React.KeyboardEvent<HTMLInputElement>) => {
    if (isComposing) return;
    if (event.key === "Enter" || event.key === ",") {
      event.preventDefault(); // 기본 동작 방지
      handleAddTag(); // 태그 추가
    }
  };

  const { mutate } = usePutHashtagInterestsMutation({ memberUuid });

  const handleSubmit = () => {
    mutate({ hashtags: payload.hashtags });
  };

  return (
    <>
      <div className="flex flex-col space-y-2">
        <QuaInputUI
          type="text"
          name="tags"
          placeholder="태그를 입력해주세요 (쉼표 또는 Enter로 구분)"
          value={tagsInput}
          onChange={handleChange}
          onKeyDown={handleKeyDown} // Enter 또는 쉼표로 태그 추가
          onCompositionStart={() => setIsComposing(true)} // IME 입력 시작
          onCompositionEnd={() => setIsComposing(false)} // IME 입력 종료
        />
        <div className="flex flex-wrap gap-2">
          {payload.hashtags.map((tag: FeedHashtag, index: number) => (
            <div
              key={`index-${tag.name}`}
              className="bg-primary text-white text-xs px-2 py-[0.25rem] flex items-center gap-2 opacity-80"
              style={{ borderRadius: "0.35rem" }}
              onClick={() => handleDeleteTag(index)}
              role="button"
              tabIndex={0}
              onKeyDown={(e) => {
                if (e.key === "Enter" || e.key === " ") {
                  handleDeleteTag(index);
                }
              }}
            >
              <span>{tag.name}</span>
              <XIcon size={12} />
            </div>
          ))}
        </div>
      </div>
      <button type="button" onClick={handleSubmit}>
        제출
      </button>
    </>
  );
}
