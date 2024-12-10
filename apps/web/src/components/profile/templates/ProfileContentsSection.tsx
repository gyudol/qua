"use client";

import type { LucideProps } from "lucide-react";
import { Clapperboard, Copy } from "lucide-react";
import type { ForwardRefExoticComponent, RefAttributes } from "react";
import { useState } from "react";
import type { MemberProfile } from "@/types/member/member-read-service";
import { FeedTab } from "../organisms/FeedTab";
import { ShortsTab } from "../organisms/ShorsTab";

interface ContentsTab {
  Icon: ForwardRefExoticComponent<
    Omit<LucideProps, "ref"> & RefAttributes<SVGSVGElement>
  >;
  value: string;
  tab: React.ReactNode;
}

type ProfileContentsSectionProps = Pick<MemberProfile, "memberUuid">;

export function ProfileContentsSection({
  memberUuid,
}: ProfileContentsSectionProps) {
  const [tabIndex, setTabIndex] = useState<number>(0);
  const contentsTabList: ContentsTab[] = [
    {
      Icon: Copy,
      value: "feed",
      tab: <FeedTab {...{ memberUuid }} />,
    },
    {
      Icon: Clapperboard,
      value: "shorts",
      tab: <ShortsTab {...{ memberUuid }} />,
    },
  ];

  return (
    <div className="p-[1rem] w-full">
      <ul className="w-full flex">
        {contentsTabList.map(({ Icon, value }, idx) => {
          return (
            <li key={value} className="flex-1">
              <button
                className={`w-full flex justify-center pb-3 border-b-2 ${tabIndex === idx ? "border-teal-400" : "border-slate-400"}`}
                type="button"
                onClick={() => setTabIndex(idx)}
              >
                <Icon
                  size="1.8rem"
                  className={
                    tabIndex === idx ? " stroke-teal-400" : "stroke-slate-400"
                  }
                />
              </button>
            </li>
          );
        })}
      </ul>
      {contentsTabList[tabIndex].tab}
    </div>
  );
}
