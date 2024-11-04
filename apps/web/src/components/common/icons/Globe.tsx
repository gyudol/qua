import type { IconProps } from "./@type";

export default function Globe({
  width = 24,
  height = 25,
  stroke = "#47D0BF",
}: IconProps) {
  return (
    <svg
      width={width}
      height={height}
      viewBox="0 0 24 25"
      fill="none"
      xmlns="http://www.w3.org/2000/svg"
    >
      <g opacity="0.9">
        <path
          d="M12 22.8481C17.5228 22.8481 22 18.371 22 12.8481C22 7.3253 17.5228 2.84814 12 2.84814C6.47715 2.84814 2 7.3253 2 12.8481C2 18.371 6.47715 22.8481 12 22.8481Z"
          stroke={stroke}
          strokeWidth="1.6"
          strokeLinecap="round"
          strokeLinejoin="round"
        />
        <path
          d="M7.99998 3.84814H8.99998C7.04998 9.68814 7.04998 16.0081 8.99998 21.8481H7.99998"
          stroke={stroke}
          strokeWidth="1.6"
          strokeLinecap="round"
          strokeLinejoin="round"
        />
        <path
          d="M15 3.84814C16.95 9.68814 16.95 16.0081 15 21.8481"
          stroke={stroke}
          strokeWidth="1.6"
          strokeLinecap="round"
          strokeLinejoin="round"
        />
        <path
          d="M3 16.8481V15.8481C8.84 17.7981 15.16 17.7981 21 15.8481V16.8481"
          stroke={stroke}
          strokeWidth="1.6"
          strokeLinecap="round"
          strokeLinejoin="round"
        />
        <path
          d="M3 9.84824C8.84 7.89824 15.16 7.89824 21 9.84824"
          stroke={stroke}
          strokeWidth="1.6"
          strokeLinecap="round"
          strokeLinejoin="round"
        />
      </g>
    </svg>
  );
}
