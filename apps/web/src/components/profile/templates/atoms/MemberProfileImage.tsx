import Image from "next/image";
import Link from "next/link";
import type { MemberProfile } from "@/types/member/member-read-service";

interface MemberProfileImageProps
  extends Pick<MemberProfile, "profileImageUrl" | "nickname"> {
  size: string;
  link?: boolean;
}

export function MemberProfileImage({
  profileImageUrl,
  nickname,
  size,
  link,
}: MemberProfileImageProps) {
  const profileImage = (
    <figure className={`relative w-[${size}] h-[${size}] rounded-full`}>
      <Image src={profileImageUrl} alt={nickname} fill />
    </figure>
  );

  if (link) return <Link href={`/profile/${nickname}`}>{profileImage}</Link>;
  return profileImage;
}
