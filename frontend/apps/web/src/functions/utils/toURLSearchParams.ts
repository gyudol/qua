export function toURLSearchParams(query: Record<string, unknown>) {
  return new URLSearchParams(
    Object.entries(query).map(([key, value]) => [key, String(value)]),
  ).toString();
}
