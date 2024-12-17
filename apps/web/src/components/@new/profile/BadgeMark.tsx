import Image from "next/image";
import { useGetBadge } from "@/hooks/reward-service";

export default function BadgeMark({
  badgeId,
  size,
}: {
  badgeId: number;
  size: string;
}) {
  const { data: badge, status } = useGetBadge({ badgeId });

  if (status === "error") return null;
  if (status === "pending") return null;
  return (
    <figure
      className="relative inline-block"
      style={{ width: size, height: size }}
    >
      <Image
        src={`https://media.qua.world/${badge.imageUrl}`}
        alt={badge.name}
        fill
      />
    </figure>
  );
}
