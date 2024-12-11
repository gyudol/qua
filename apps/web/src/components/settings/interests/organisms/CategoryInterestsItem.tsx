import Link from "next/link";
import type { CategoryInterestsItem as CategoryInterestsItemType } from "@/types/member/member-service";
import { getCategory } from "@/actions/admin-service";

type CategoryInterestsItemProps = CategoryInterestsItemType;

export async function CategoryInterestsItem({
  id,
}: CategoryInterestsItemProps) {
  const { categoryName } = await getCategory({ categoryId: id });

  return (
    <Link
      href={`/category/${categoryName}`}
      className="text-teal-500 text-xs font-bold bg-slate-50 rounded-xl py-1 px-2"
      aria-label={`카테고리: ${categoryName}`}
    >
      ß 📚{categoryName}
    </Link>
  );
}
