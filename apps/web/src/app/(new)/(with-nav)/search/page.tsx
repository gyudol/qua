import { List, Search, UserRound, Video } from "lucide-react";
import PageContainer from "@/components/@new/layouts/containers/PageContainer";
import { FeedPageFeedListSection } from "@/components/feed/pages/FeedPageFeedListSection";
import { Kitty } from "@/components/common/icons";

export default function page({
  searchParams: { tag: _tag, sortBy: _sortBy, view: _view },
}: {
  searchParams: {
    tag?: string;
    sortBy?: string;
    view?: string;
  };
}) {
  const tag = _tag ? decodeURI(_tag) : undefined;
  const sortBy = _sortBy === "likes" ? "likes" : "latest";
  const view = _view === "compact" ? "compact" : "card";

  return (
    <>
      <div className="w-full min-h-[6rem]" />
      <header
        className="
        absolute top-0
        w-full h-[6rem] 
        flex justify-center items-center"
      >
        <form className="w-full p-4">
          <div
            className="
            flex  gap-4  p-5 
            bg-slate-200  rounded-3xl
            border-2  border-slate-300
            "
          >
            <button type="submit">
              <Search className="stroke-slate-600" />
            </button>
            <input
              id="tag"
              name="tag"
              type="text"
              className="flex-1 bg-slate-200 focus:outline-none"
              placeholder="검색어를 입력하세요"
              defaultValue={tag}
            />
          </div>
        </form>
      </header>
      <PageContainer>
        <section>
          <div className="px-4 py-2">
            <ul className="flex gap-4 ">
              <li
                className="
                p-1 flex-1 w-full 
                flex justify-center 
                bg-teal-400 rounded-lg"
              >
                <List className="stroke-white" />
              </li>
              <li
                className="
                p-1 flex-1 w-full 
                flex justify-center 
                bg-slate-300 rounded-lg"
              >
                <Video className="stroke-white" />
              </li>
              <li
                className="
                p-1 flex-1 w-full 
                flex justify-center 
                bg-slate-300 rounded-lg"
              >
                <UserRound className="stroke-white" />
              </li>
            </ul>
          </div>
        </section>

        {tag ? (
          <FeedPageFeedListSection
            {...{
              sortBy,
              view,
              hashtagName: tag,
            }}
          />
        ) : (
          <div className="size-full flex justify-center items-center">
            <div className="flex flex-col items-center gap-4">
              <Kitty width={100} height={100} />
              <p>검색 결과가 없습니다.</p>
            </div>
          </div>
        )}
      </PageContainer>
    </>
  );
}
