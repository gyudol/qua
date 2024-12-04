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
    const res = await createFeed(payload);
    // eslint-disable-next-line no-alert -- alert 사용
    if (res) alert('good'); // 피드 생성 API 호출
  };

  return (
    <form
      ref={formRef}
      className="w-full h-[90%] flex flex-col gap-2 relative"
      onSubmit={(event) => {
        void onSubmit(event);
      }}
    >
      <FeedCreateFormFields payload={payload} setPayload={setPayload} />
      <Button
        type="submit"
        className="w-[90%] md:w-2/5 text-[1rem] bg-[#47D0BF] py-[25px] rounded-lg text-white text-center mb-20 fixed bottom-10 left-1/2 translate-x-[-50%]"
      >
        Upload now
      </Button>
    </form>
  );
}

export default FeedWriteFrom;
