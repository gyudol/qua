'use client';

import React, { Suspense, useState, useEffect } from 'react';
import { fishCategoryData } from '@/store/InitialData';

function RandomFish() {
  const [randomFish, setRandomFish] = useState(() => fishCategoryData[0]); // 초기값 설정

  useEffect(() => {
    const randomIndex = Math.floor(Math.random() * fishCategoryData.length);
    setRandomFish(fishCategoryData[randomIndex]);
  }, []);

  const FishComponent = randomFish.image;

  return (
    <Suspense fallback={<div>Loading...</div>}>
      <FishComponent size={30} />
    </Suspense>
  );
}

export default RandomFish;
