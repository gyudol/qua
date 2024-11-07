import { Fig, withLink } from "@/components/common/atoms";

interface PfPictureProp
  extends Omit<React.ComponentProps<typeof Fig>, "className"> {
  className?: string;
}

function PfPicture({ src, alt }: PfPictureProp) {
  return <Fig {...{ className: "w-[48px] h-[48px] rounded-full", src, alt }} />;
}

const PfPictureWithLink = withLink(PfPicture);

interface PfNameProp {
  className?: string;
  nickname: string;
}

function PfName({ className, nickname }: PfNameProp) {
  return <div {...{ className }}>{nickname}</div>;
}

const PfNameWithLink = withLink(PfName);

export default {
  Picture: PfPicture,
  PictureWithLink: PfPictureWithLink,
  Name: PfName,
  NameWithLink: PfNameWithLink,
};
