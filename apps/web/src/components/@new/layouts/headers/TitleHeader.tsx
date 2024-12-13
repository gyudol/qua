import { ChevronLeft } from "lucide-react";
import Bubbles from "../../features/Bubbles";
import BackButton from "../../common/BackButton";

export default function TitleHeader({
  title,
  back,
}: {
  title: string;
  back?: boolean;
}) {
  return (
    <>
      <div className="w-full min-h-[5rem]" />
      <header
        className="
      absolute top-0  z-30
      w-full  h-[5rem] bg
      flex    flex-row
      px-4    bg-teal-400  
      justify-between items-center
      shadow-md   rounded-b-xl
      "
      >
        <div className="w-full flex flex-row justify-between">
          <div className="flex gap-4">
            {back ? (
              <BackButton>
                <ChevronLeft className="stroke-white" />
              </BackButton>
            ) : null}
            <h2 className="text-xl font-extrabold text-white">{title}</h2>
          </div>
          <h1 className="text-[0rem]">QUA</h1>
        </div>

        <Bubbles {...{ count: 500 }} />
      </header>
    </>
  );
}
