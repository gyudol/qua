import type { GetFollowingsReq } from "@/types/utility-service";
import { getHashtagInterests } from "@/actions/member-service/interests";
import { FeedHashtag } from "@/components/feed/atoms/FeedHashtag";
import HashtagInterestsSelector from "../organisms/HashtagInterestsSelector";

type HashtagInterestsSectionProps = Pick<GetFollowingsReq, "memberUuid">;

export async function HashtagInterestsSection({
  memberUuid,
}: HashtagInterestsSectionProps) {
  const hashtags = await getHashtagInterests({ memberUuid });

  return (
    <section>
      {hashtags.length
        ? hashtags.map(({ name }) => <FeedHashtag key={name} {...{ name }} />)
        : "관심 해시태그를 등록해보세요"}
      <HashtagInterestsSelector {...{ memberUuid, hashtags }} />
    </section>
  );
}
