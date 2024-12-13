import { Pencil, XIcon } from "lucide-react";
import BackButton from "../../common/BackButton";

export default function DetailHeader({ title }: { title?: string }) {
  return (
    <>
      <div className="w-full min-h-[4rem]" />
      <header
        className="
      absolute top-0  z-30
      w-full  h-[4rem] bg
      flex    flex-row
      px-4    
      justify-between items-center
      shadow-md
      "
      >
        <div className="w-full flex flex-row justify-between">
          <div className="text-teal-400 text-[1.25rem] font-semibold flex gap-4 items-center">
            <Pencil />
            {title}
          </div>
          <h1 className="text-[0rem]">QUA</h1>
          <BackButton>
            <XIcon className="stroke-slate-400" />
          </BackButton>
        </div>
      </header>
    </>
  );
}
