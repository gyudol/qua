"use client";
import { useEffect, useState, useRef } from "react";
import { CommonLayout } from "@/components/common/atoms/CommonLayout";
import HeaderLeftMenuAndLogo from "./HeaderLeftMenuAndLogo";
import HeaderNav from "./HeaderNav";

interface HeaderProps {
  onMenuClick: () => void;
}

export default function FeedTabHeader({ onMenuClick }: HeaderProps) {
  const [isView, setIsView] = useState(true);
  const prevScrollY = useRef(0);
  const ticking = useRef(false);

  const handleScroll = () => {
    if (!ticking.current) {
      ticking.current = true;

      window.requestAnimationFrame(() => {
        const currentScrollY = Math.max(0, window.scrollY);
        const maxScrollY =
          document.documentElement.scrollHeight - window.innerHeight;

        if (currentScrollY === 0 || currentScrollY >= maxScrollY) {
          ticking.current = false;
          return;
        }

        if (currentScrollY > prevScrollY.current) {
          setIsView(false);
        } else if (currentScrollY < prevScrollY.current) {
          setIsView(true);
        }

        prevScrollY.current = currentScrollY;
        ticking.current = false;
      });
    }
  };

  useEffect(() => {
    window.addEventListener("scroll", handleScroll);
    return () => {
      window.removeEventListener("scroll", handleScroll);
    };
  }, []);

  return (
    <>
      <CommonLayout.Header
        className={`flex justify-between items-center px-[0.8rem] py-[1.5rem] bg-white transition-transform duration-300 
        fixed top-0 left-0 right-0 z-[20] md:z-[5] md:top-[2.4rem] md:max-w-md md:rounded-t-xl
        drop-shadow-primary
        ${isView ? "translate-y-0" : "-translate-y-full"}`}
      >
        <HeaderLeftMenuAndLogo onMenuClick={onMenuClick} />
        <HeaderNav />
      </CommonLayout.Header>
      <div className="h-[5.5rem] bg-white" />
    </>
  );
}
