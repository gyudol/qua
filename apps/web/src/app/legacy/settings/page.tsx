import Link from "next/link";
import { CommonLayout } from "@/components/common/atoms";

export default function MainPage() {
  return (
    <CommonLayout.Contents className="bg-white flex flex-col gap-4">
      <div className="flex flex-col">
        <Link href="/settings/liked-feed">좋아요한 피드</Link>
        <Link href="/settings/liked-shorts">좋아요한 쇼츠</Link>
        <Link href="/settings/bookmarked-feed">북마크한 피드</Link>
        <Link href="/settings/bookmarked-shorts">북마크한 쇼츠</Link>
        <Link href="/settings/follow">팔로잉 및 팔로우 목록 </Link>
      </div>

      <div className="flex flex-col">
        <Link href="/settings/point-history">포인트 히스토리</Link>
        <Link href="/settings/badge">
          뱃지 장착 상태 수정 특정 회원이 가진 모든 뱃지 조회 회원 뱃지 생성
        </Link>
        <Link href="/settings/interests">프로필페이지 관심사 수정</Link>
      </div>
    </CommonLayout.Contents>
  );
}
