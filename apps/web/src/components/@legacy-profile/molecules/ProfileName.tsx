import { withLink } from "@/components/common/atoms";

interface ProfileNameProp {
  className?: string;
  nickname: string;
}

function Name({ className, nickname }: ProfileNameProp) {
  return <div {...{ className }}>{nickname}</div>;
}

const NameWithLink = withLink(Name);

const ProfileName = {
  Name,
  NameWithLink,
};

export default ProfileName;
