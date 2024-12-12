import { useQuery } from "@tanstack/react-query";
import { getAllCategories, getCategory } from "@/actions/admin-service";
import type { GetCategoryReq } from "@/types/admin";

export function useGetAllCategories() {
  return useQuery({
    queryKey: [
      "admin-service",
      {
        type: "category",
        subtype: "all",
      },
    ],
    queryFn: () => getAllCategories(),
  });
}

export function useGetCategory({ categoryId }: GetCategoryReq) {
  return useQuery({
    queryKey: [
      "admin-service",
      {
        type: "category",
        categoryId,
      },
    ],
    queryFn: () => getCategory({ categoryId }),
  });
}
