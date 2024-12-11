"use client";
import React, { useState } from "react";
import { Switch } from "@repo/ui/shadcn/switch";
import { Label } from "@repo/ui/shadcn/label";
import { feedFormSchema } from "@/schema/FeedFormSchema";
import type { CreateFeedType, FeedHashtag } from "@/types/request/requestType";
import ImageUploader from "../molecule/ImageUploader";
import CategorySelector from "../molecule/CategorySelector";

function FeedCreateFormFields({
  payload,
  setPayload,
}: {
  payload: CreateFeedType;
  setPayload: React.Dispatch<React.SetStateAction<CreateFeedType>>;
}) {
  const [errors, setErrors] = useState<Record<string, string>>({});

  const handleChange = (
    event: React.ChangeEvent<
      HTMLInputElement | HTMLTextAreaElement | HTMLSelectElement
    >,
  ) => {
    const { name, value } = event.target;

    let updatedValue: unknown;

    if (name === "tags") {
      // 태그 처리
      updatedValue = value.split(",").map((tag) => ({
        name: tag.trim(),
      })); // FeedHashtag[] 변환
      setPayload((prev) => ({
        ...prev,
        hashtags: updatedValue as FeedHashtag[],
      }));
    } else if (name === "categoryName") {
      updatedValue = value;
      setPayload((prev) => ({ ...prev, categoryName: updatedValue as string }));
    } else if (name === "status") {
      updatedValue = value === "public" ? "VISIBLE" : "HIDDEN";
      setPayload((prev) => ({
        ...prev,
        visibility: updatedValue as "VISIBLE" | "HIDDEN",
      }));
    } else {
      updatedValue = value;
      setPayload((prev) => ({ ...prev, [name]: updatedValue }));
    }

    const validatedPayload = {
      ...payload,
      [name]: updatedValue,
    };

    const res = feedFormSchema.safeParse(validatedPayload); // 스키마 검증

    if (!res.success) {
      const validationErrors = res.error.issues.reduce(
        (acc, issue) => ({
          ...acc,
          [issue.path[0]]: issue.message,
        }),
        {},
      );
      setErrors(validationErrors); // 에러 메시지 저장
    } else {
      setErrors({}); // 에러 초기화
    }
  };

  return (
    <fieldset className="flex flex-col gap-5 h-full">
      <input
        type="text"
        name="title"
        placeholder="제목을 입력해주세요."
        onChange={handleChange}
        className="w-full h-[2.5rem] transition duration-300 rounded-[0.3rem] outline-none bg-white px-2 ring-1 ring-primary placeholder:text-xs placeholder:text-primary text-sm
        focus:bg-primary focus:ring-0 focus:ring-primary focus:ring-opacity-50 focus:text-white"
      />
      {errors.title ? <p style={{ color: "red" }}>{errors.title}</p> : null}

      <textarea
        id="post"
        name="content"
        className="w-full h-[5.5rem] transition duration-300 rounded-[0.3rem] outline-none bg-white p-2 ring-1 ring-primary placeholder:text-xs placeholder:text-primary text-sm
        focus:bg-primary focus:ring-0 focus:ring-primary focus:ring-opacity-50 focus:text-white"
        rows={4}
        placeholder="내용을 입력해주세요"
        onChange={handleChange}
      />
      {errors.content ? <p style={{ color: "red" }}>{errors.content}</p> : null}

      <CategorySelector id="categoryName" name="categoryName" value="test" />

      {errors.categoryName ? (
        <p style={{ color: "red" }}>{errors.categoryName}</p>
      ) : null}

      <input
        id="tags"
        name="tags"
        type="text"
        className="w-full h-[2.5rem] transition duration-300 rounded-[0.3rem] outline-none bg-white px-2 ring-1 ring-primary placeholder:text-xs placeholder:text-primary text-sm
        focus:bg-primary focus:ring-0 focus:ring-primary focus:ring-opacity-50 focus:text-white"
        placeholder="태그 (쉼표로 구분)"
        onChange={handleChange}
      />
      {errors.tags ? <p style={{ color: "red" }}>{errors.tags}</p> : null}
      <div className="flex items-center space-x-2">
        <Switch />
        <Label htmlFor="airplane-mode">Private Mode</Label>
      </div>
      <ImageUploader setPayload={setPayload} />
    </fieldset>
  );
}

export default FeedCreateFormFields;
