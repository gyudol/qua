"use client";

import { useParams } from "next/navigation";

export default function withParams<T>(
  Component: React.FC<T>,
  childKey?: string,
) {
  function ComponentWithParam(props: T) {
    const params = useParams<Record<string, string>>();

    if (childKey) {
      const child = decodeURI(params[childKey]);
      return <Component {...props}>{child}</Component>;
    }
    return <Component {...props} params={params} />;
  }

  return ComponentWithParam;
}
