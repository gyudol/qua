import Image from "next/image";
import { useGetGrade } from "@/hooks/reward-service";

export default function GradeMark({
  gradeId,
  size,
}: {
  gradeId: number;
  size: string;
}) {
  const { data: grade, status } = useGetGrade({ gradeId });

  if (status === "error") return null;
  if (status === "pending") return null;
  return (
    <figure
      className="relative inline-block"
      style={{ width: size, height: size }}
    >
      <Image
        src={`https://media.qua.world/${grade.imageUrl}`}
        alt={grade.name}
        fill
      />
    </figure>
  );
}
