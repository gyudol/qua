import type { IconProps } from "./type";

export default function Search({
  width = 24,
  height = 25,
  stroke = "#9DB2CE",
}: IconProps) {
  return (
    <svg
      width={width}
      height={height}
      viewBox="0 0 24 25"
      fill="none"
      xmlns="http://www.w3.org/2000/svg"
    >
      <circle
        cx="10.8889"
        cy="11.5852"
        r="8.88889"
        stroke={stroke}
        strokeWidth="1.5"
      />
      <path
        d="M17.5555 18.252L22 22.6964"
        stroke={stroke}
        strokeWidth="1.5"
        strokeLinecap="round"
        strokeLinejoin="round"
      />
    </svg>
  );
}
