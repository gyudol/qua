'use client';

import React, { useState } from 'react';
import { Switch } from '@repo/ui/shadcn/switch';
import { Label } from '@repo/ui/shadcn/label';
import { XIcon } from 'lucide-react';
import { feedFormSchema } from '@/schema/FeedFormSchema';
import type { CreateFeedType, FeedHashtag } from '@/types/request/requestType';
import { QuaInputUI } from '@/components/common/atoms/QuaInputUi';
import { QuaTextAreaUI } from '@/components/common/atoms/QuaTextAreaUi';
import ErrorTextUi from '@/components/common/atoms/ErrorTextUi';
import ImageUploader from '../molecule/ImageUploader';
import CategorySelector from '../molecule/CategorySelector';

function FeedCreateFormFields({
  payload,
  setPayload,
}: {
  payload: CreateFeedType;
  setPayload: React.Dispatch<React.SetStateAction<CreateFeedType>>;
}) {
  const [errors, setErrors] = useState<Record<string, string>>({});
  const [tagsInput, setTagsInput] = useState<string>('');
  const [isComposing, setIsComposing] = useState<boolean>(false);
  const [isPrivate, setIsPrivate] = useState<boolean>(false);

  const validatePayload = (updatedPayload: CreateFeedType) => {
    const res = feedFormSchema.safeParse(updatedPayload); // Zod 스키마 검증
    if (!res.success) {
      const validationErrors = res.error.issues.reduce(
        (acc, issue) => ({
          ...acc,
          [issue.path[0]]: issue.message,
        }),
        {}
      );
      setErrors(validationErrors); // 에러 메시지 저장
    } else {
      setErrors({}); // 에러 초기화
    }
  };

  const handleChange = (
    event: React.ChangeEvent<
      HTMLInputElement | HTMLTextAreaElement | HTMLSelectElement
    >
  ) => {
    const { name, value } = event.target;
    // console.log('name', name, value);
    // console.log('payload', payload);

    if (name === 'tags') {
      setTagsInput(value); // 태그 입력 필드 값만 업데이트
    } else {
      const updatedPayload = {
        ...payload,
        [name]: value,
      };
      setPayload(updatedPayload); // payload 업데이트
      validatePayload(updatedPayload); // Zod 스키마로 검증
    }
  };

  const handleAddTag = () => {
    if (tagsInput.trim() === '') return;

    const newTag = { name: tagsInput.trim() };
    if (payload.hashtags.some((tag) => tag.name === newTag.name)) {
      setTagsInput(''); // 중복 태그는 추가하지 않음
      return;
    }

    // console.log('newTag', newTag);

    const updatedTags = [...payload.hashtags, newTag];
    const updatedPayload = {
      ...payload,
      hashtags: updatedTags,
    };

    setTagsInput(''); // 입력 필드 초기화
    setPayload(updatedPayload); // payload 업데이트
    validatePayload(updatedPayload); // Zod 스키마로 검증
  };

  const handleDeleteTag = (index: number) => {
    const updatedTags = payload.hashtags.filter((_, i) => i !== index);
    const updatedPayload = {
      ...payload,
      hashtags: updatedTags,
    };

    setPayload(updatedPayload); // payload 업데이트
    validatePayload(updatedPayload); // Zod 스키마로 검증
  };

  const handleKeyDown = (event: React.KeyboardEvent<HTMLInputElement>) => {
    if (isComposing) return;
    if (event.key === 'Enter' || event.key === ',') {
      event.preventDefault(); // 기본 동작 방지
      handleAddTag(); // 태그 추가
    }
  };

  const handleCategorySelect = (categoryName: string) => {
    // console.log('categoryName', categoryName);
    const updatedPayload = {
      ...payload,
      categoryName,
    };
    setPayload(updatedPayload);
    validatePayload(updatedPayload);
  };

  const handleVisibilityChange = (checked: boolean) => {
    // console.log('checked', checked);
    setIsPrivate(checked);
    const updatedPayload: CreateFeedType = {
      ...payload,
      visibility: checked ? 'HIDDEN' : 'VISIBLE',
    };
    setPayload(updatedPayload);
    validatePayload(updatedPayload);
  };

  return (
    <fieldset className="flex flex-col gap-4 h-full">
      <div className="flex items-center space-x-2 relative">
        <QuaInputUI
          type="text"
          name="title"
          placeholder="제목을 입력해주세요"
          value={payload.title}
          onChange={handleChange}
        />
        <ErrorTextUi errorText={errors.title} isView={Boolean(errors.title)} />
      </div>
      <div className="flex items-center space-x-2 relative">
        <QuaTextAreaUI
          rows={5}
          name="content"
          placeholder="내용을 입력해주세요"
          value={payload.content}
          onChange={handleChange}
        />
        <ErrorTextUi
          errorText={errors.content}
          isView={Boolean(errors.content)}
        />
      </div>
      <div className="flex items-center space-x-2 relative">
        <CategorySelector
          id="categoryName"
          name="categoryName"
          value={payload.categoryName}
          changeHandler={handleCategorySelect}
        />
        <ErrorTextUi
          errorText={errors.categoryName}
          isView={Boolean(errors.categoryName)}
        />
      </div>
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
              style={{ borderRadius: '0.35rem' }}
              onClick={() => handleDeleteTag(index)}
              role="button"
              tabIndex={0}
              onKeyDown={(e) => {
                if (e.key === 'Enter' || e.key === ' ') {
                  handleDeleteTag(index);
                }
              }}
            >
              <span>{tag.name}</span>
              <XIcon size={12} />
            </div>
          ))}
        </div>
        <ErrorTextUi errorText={errors.tags} isView={Boolean(errors.tags)} />
      </div>
      <div className="flex items-center space-x-2">
        <Switch
          onCheckedChange={() => handleVisibilityChange(!isPrivate)}
          name="visibility"
          checked={isPrivate}
        />
        <Label htmlFor="airplane-mode" className="text-primary text-xs">
          private mode {isPrivate ? 'on' : 'off'}
        </Label>
      </div>
      <ImageUploader setPayload={setPayload} />
    </fieldset>
  );
}

export default FeedCreateFormFields;
