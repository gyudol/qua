import Image from "next/image";
import Link from "next/link";
import { Fish } from "lucide-react";
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
  if (link)
    return (
      <Link href={`/profile/${nickname}`}>
        <ProfileImage {...{ size, profileImageUrl, nickname }} />
      </Link>
    );
  return <ProfileImage {...{ size, profileImageUrl, nickname }} />;
}

type ProfileImageProps = Pick<
  MemberProfileImageProps,
  "size" | "profileImageUrl" | "nickname"
>;

function ProfileImage({ size, profileImageUrl, nickname }: ProfileImageProps) {
  return profileImageUrl ? (
    <figure className="relative" style={{ width: size, height: size }}>
      <Image
        className="rounded-full"
        sizes={size}
        src={`https://media.qua.world/${profileImageUrl}`}
        alt={nickname}
        fill
      />
    </figure>
  ) : (
    <div
      className="rounded-full bg-teal-400 flex justify-center items-center"
      style={{ width: size, height: size }}
    >
      <Fish className="fill-white" />
    </div>
  );
}
