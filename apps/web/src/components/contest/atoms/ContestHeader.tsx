"use client";
import React from "react";
import { XCircle } from "lucide-react";
import { useRouter } from "next/navigation";
import { CommonLayout } from "@/components/common/atoms";

function ContestHeader() {
  const router = useRouter();
  return (
    <CommonLayout.Header
      className={`flex justify-between items-center bg-[#FDFCFC] px-[1rem] md:px-[2rem] py-[1.5rem] transition-transform duration-300 
        md:max-w-md md:rounded-t-xl
        drop-shadow-primary
        translate-y-0`}
    >
      <p className="text-xl md:text-2xl font-bold text-center text-gray-900 flex-grow">
        콘테스트
      </p>
      <XCircle
        className="w-[2rem] h-[2rem] text-gray-600 cursor-pointer hover:text-gray-800"
        onClick={() => router.back()}
      />
    </CommonLayout.Header>
  );
}

export default ContestHeader;
