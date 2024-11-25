'use client';
import { useEffect, useState, useRef } from 'react';
import { CommonLayout } from '@/components/common/atoms/CommonLayout';
import HeaderLeftMenuAndLogo from './HeaderLeftMenuAndLogo';
import HeaderNav from './HeaderNav';

interface HeaderProps {
  onMenuClick: () => void;
}

export default function FeedTabHeader({ onMenuClick }: HeaderProps) {
  const [isView, setIsView] = useState(true);
  const prevScrollY = useRef(0);

  const handleScroll = () => {
    const currentScrollY = window.scrollY;
    if (currentScrollY > prevScrollY.current) {
      // 아래로 스크롤 중
      setIsView(false);
    } else if (currentScrollY < prevScrollY.current) {
      // 위로 스크롤 중
      setIsView(true);
    }
    prevScrollY.current = currentScrollY;
  };

  useEffect(() => {
    // 스크롤 이벤트 리스너 등록
    window.addEventListener('scroll', handleScroll);
    // 컴포넌트 언마운트 시 이벤트 리스너 제거
    return () => {
      window.removeEventListener('scroll', handleScroll);
    };
  }, []);

  return (
    <CommonLayout.Header
      className={`flex justify-between items-center px-[0.8rem] py-[1.5rem] bg-white transition-transform duration-300 
        fixed top-0 left-0 right-0 z-[20]
        drop-shadow-primary
        ${isView ? 'translate-y-0' : '-translate-y-full'}`}
    >
      <HeaderLeftMenuAndLogo onMenuClick={onMenuClick} />
      <HeaderNav />
    </CommonLayout.Header>
  );
}
