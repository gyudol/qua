"use client";
import React from "react";
import { signIn } from "next-auth/react";
import { toast } from "sonner";
import {
  Bubbles,
  KakaoButton,
  Kitty,
  NaverButton,
} from "@/components/common/icons";

function SignInForm({ callbackUrl }: { callbackUrl?: string }) {
  const handleKakaoSignIn = () => {
    signIn("kakao", { callbackUrl: callbackUrl || "/" }).catch(() => null);
  };

  const handleNaverSignIn = () => {
    toast.info(
      "현재 네이버 로그인의 경우 '앱 인증' 절차가 진행되는 중 입니다.",
    );
    // signIn("naver", { callbackUrl: callbackUrl || "/" }).catch(() => null);
  };

  return (
    <div className="w-full h-full flex flex-col items-center justify-around">
      <div className="flex flex-col items-center">
        <h2 className="text-teal-400 text-[3rem]">꾸아를 즐겨보세요.</h2>
        <h3 className="text-slate-400 text-[1rem]">
          회원가입 필요없는 간편 로그인
        </h3>
      </div>

      <div className="flex flex-col items-center">
        <Bubbles />
        <Kitty width={70} height={70} />
      </div>

      <div
        className="
      w-full px-4
      flex flex-col items-center gap-[1rem]"
      >
        <button
          type="button"
          onClick={handleKakaoSignIn}
          className="
          w-full  bg-[#FEE500] 
          text-[1.125rem] 
          p-7 rounded-lg 
          flex justify-center items-center gap-4"
        >
          <KakaoButton />
          <span>카카오로 시작하기</span>
        </button>

        <button
          type="button"
          onClick={handleNaverSignIn}
          className="
          w-full  bg-[#47474A] 
          text-[1.125rem] text-white 
          p-7 rounded-lg 
          flex justify-center items-center gap-4"
        >
          <NaverButton />
          <span>네이버로 시작하기</span>
        </button>
      </div>
    </div>
  );
}

export default SignInForm;
