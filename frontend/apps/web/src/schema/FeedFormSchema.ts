import { z } from "zod";

export const feedFormSchema = z.object({
  title: z
    .string()
    .min(1, "제목은 필수 항목입니다")
    .max(100, "제목은 100자 이하여야 합니다"),
  content: z
    .string()
    .min(1, "내용은 필수 항목입니다")
    .max(500, "내용은 500자 이하여야 합니다"),
  categoryName: z.string().min(1, "카테고리는 필수 항목입니다"),
  hashtags: z.array(
    z.object({
      name: z.string().min(1, "태그 이름은 필수입니다"),
    }),
  ),
}) as z.ZodSchema<{
  title: string;
  content: string;
  categoryName: string;
  hashtags: { name: string }[];
}>;
