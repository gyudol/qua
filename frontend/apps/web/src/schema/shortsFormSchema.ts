import { z } from "zod";

export const shortsFormSchema = z.object({
  title: z
    .string()
    .min(1, "제목은 필수 항목입니다")
    .max(100, "제목은 100자 이하여야 합니다"),
  hashtags: z
    .array(
      z.object({
        name: z.string().min(1, "태그 이름은 필수입니다"),
      }),
    )
    .min(1, "태그는 1개 이상 입력해야 합니다"),
}) as z.ZodSchema<{
  title: string;
  hashtags: { name: string }[];
}>;
