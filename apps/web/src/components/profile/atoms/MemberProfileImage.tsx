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
  return (
    <figure className="relative" style={{ width: size, height: size }}>
      <Image
        className="rounded-full"
        sizes={size}
        src={`https://media.qua.world/${profileImageUrl}`}
        alt={nickname}
        fill
      />
    </figure>
  );
}
