import { Fig, Skeleton, withLink } from "@/components/common/atoms";

const className = "w-[48px] h-[48px] rounded-full overflow-hidden";

interface ProfilePictureProp
  extends Omit<React.ComponentProps<typeof Fig>, "className"> {
  className?: string;
}

function Picture({ src, alt }: ProfilePictureProp) {
  return <Fig {...{ className, src, alt }} />;
}

function PictureSkeleton() {
  return <Skeleton {...{ className }} />;
}

const PictureWithLink = withLink(Picture);

const ProfilePicture = {
  Picture,
  PictureSkeleton,
  PictureWithLink,
};

export default ProfilePicture;
