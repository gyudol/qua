'use client';

import { Button } from '@repo/ui/shadcn/button';
import { useRef, useState } from 'react';
import type { CreateFeedType } from '@/types/request/requestType';
import { createFeed } from '@/actions/feed';
import FeedCreateFormFields from './FeedCreateFormFields';

function FeedWriteFrom() {
  const [payload, setPayload] = useState<CreateFeedType>({
    memberUuid: 'test',
    title: '',
    content: '',
    categoryName: '', // 기본값을 빈 문자열로 설정
    visibility: 'VISIBLE', // 기본값
    hashtags: [],
    mediaList: [],
  });

  const formRef = useRef<HTMLFormElement>(null);

  const onSubmit = async (event: React.FormEvent) => {
    event.preventDefault(); // 기본 form 제출 방지
    // console.log(payload);
    await createFeed(payload); // 피드 생성 API 호출
  };

  return (
    <form
      ref={formRef}
      className="w-full bg-[#FDFCFC] h-full px-[28px]"
      onSubmit={(event) => {
        void onSubmit(event);
      }}
    >
      <div className="flex flex-col gap-2">
        <FeedCreateFormFields payload={payload} setPayload={setPayload} />
        <Button
          type="submit"
          className="text-[20px] bg-[#47D0BF] py-[25px] rounded-lg text-white text-center w-full mb-20"
        >
          Upload now
        </Button>
      </div>
    </form>
  );
}

export default FeedWriteFrom;
