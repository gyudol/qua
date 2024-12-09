import { Separator } from "@repo/ui/shadcn/separator";
import { FeedSortSelector } from "../atoms/FeedSortSelector";
import { FeedViewTypeToggler } from "../atoms/FeedViewTypeToggler";

export function FeedListOptionGroup() {
  return (
    <>
      <div className="flex justify-between p-[1rem] items-center">
        <div>
          <FeedSortSelector />
        </div>
        <div>
          <FeedViewTypeToggler />
        </div>
      </div>
      <Separator className="h-[0.25rem]" />
    </>
  );
}
