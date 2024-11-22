"use client";

import {
  QueryClient,
  QueryClientProvider as ReactQueryClientProvider,
} from "@tanstack/react-query";

interface QueryClientProviderProp {
  children?: React.ReactNode;
}

export default function QueryClientProvider({
  children,
}: QueryClientProviderProp) {
  const queryClient = new QueryClient();

  return (
    <ReactQueryClientProvider client={queryClient}>
      {children}
    </ReactQueryClientProvider>
  );
}
