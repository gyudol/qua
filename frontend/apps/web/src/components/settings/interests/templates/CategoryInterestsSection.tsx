import type { GetFollowingsReq } from "@/types/utility-service";
import { getCategoryInterests } from "@/actions/member-service/interests";
import { CategoryInterestsItem } from "../organisms/CategoryInterestsItem";

type CategoryInterestsSectionProps = Pick<GetFollowingsReq, "memberUuid">;

export async function CategoryInterestsSection({
  memberUuid,
}: CategoryInterestsSectionProps) {
  const data = await getCategoryInterests({ memberUuid });

  return (
    <section>
      {data.length
        ? data.map(({ id }) => <CategoryInterestsItem key={id} {...{ id }} />)
        : "관심 카테고리를 등록해보세요"}
    </section>
  );
}
