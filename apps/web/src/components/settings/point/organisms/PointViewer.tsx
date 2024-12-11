"use client";

import { useGetMemberPoint } from "@/hooks/reward-service";
import type { GetMemberPointReq } from "@/types/reward-service";

type PointViewerProps = Pick<GetMemberPointReq, "memberUuid">;

export function PointViewer({ memberUuid }: PointViewerProps) {
  const { data, status, error } = useGetMemberPoint({ memberUuid });

  if (status === "error") throw Error(error.message);
  if (status === "pending") return null;

  return <div>현재 포인트는 {data.point} 입니다.</div>;
}
