import { cn } from "@repo/ui/lib/utils";
import type { StaticImport } from "next/dist/shared/lib/get-img-props";
import Image from "next/image";

interface FigProp {
  className: string;
  src: string | StaticImport;
  alt: string;
}

export default function Fig({ className, src, alt }: FigProp) {
  return (
    <figure {...{ className: cn("relative", className) }}>
      <Image {...{ src, alt }} fill />
    </figure>
  );
}
