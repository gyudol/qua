import { cn } from "@repo/ui/lib/utils";

interface FeedContentProps {
  content: string;
  detail?: boolean;
}

export default function FeedContent({ content, detail }: FeedContentProps) {
  return (
    <p className={cn(!detail && "text-ellipsis line-clamp-5 overflow-hidden")}>
      {content}
    </p>
  );
}
