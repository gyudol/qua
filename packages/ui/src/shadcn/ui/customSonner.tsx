"use client";

import { Toaster as Sonner } from "sonner";

type ToasterProps = React.ComponentProps<typeof Sonner>;

const CustomToaster = ({ ...props }: ToasterProps) => {
  return <Sonner {...props} />;
};

export { CustomToaster };
