'use client';
import React from 'react';
import { XCircle } from 'lucide-react';
import { useRouter } from 'next/navigation';
import { CommonLayout } from '../common/atoms';

function CloseHeader() {
  const router = useRouter();
  return (
    <CommonLayout.Header
      className={`fixed flex justify-end items-center px-[1rem] md:px-[2rem] py-[1.5rem] transition-transform duration-300 
        md:max-w-md md:rounded-t-xl
        drop-shadow-primary
        translate-y-0`}
    >
      <XCircle
        color="#48d0bf"
        strokeWidth={2}
        className="w-[1.5rem] h-[1.5rem]"
        onClick={() => router.back()}
      />
    </CommonLayout.Header>
  );
}

export default CloseHeader;
