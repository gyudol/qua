import { useQuery } from "@tanstack/react-query";
import { getAllGrade, getGrade } from "@/actions/reward-service";
import type { GetGradeReq } from "@/types/reward-service";

export function useGetGrade({ gradeId }: GetGradeReq) {
  return useQuery({
    queryKey: ["reward-service", { type: "grade", subtype: "grade", gradeId }],
    queryFn: () => getGrade({ gradeId }),
  });
}

export function useGetAllGrade() {
  return useQuery({
    queryKey: ["reward-service", { type: "grade", subtype: "all-grade" }],
    queryFn: () => getAllGrade(),
  });
}
