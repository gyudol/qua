import type { IconProps } from "./@type";

export default function LeftChevron({
  width = 24,
  height = 24,
  fill = "white",
}: IconProps) {
  return (
    <svg
      width={width}
      height={height}
      viewBox="0 0 24 24"
      fill="none"
      xmlns="http://www.w3.org/2000/svg"
    >
      <path
        fillRule="evenodd"
        clipRule="evenodd"
        d="M11.5207 2.46246C12.1598 3.07908 12.1598 4.07881 11.5207 4.69543L3.95053 12L11.5207 19.3046C12.1598 19.9212 12.1598 20.9209 11.5207 21.5375C10.8817 22.1542 9.84559 22.1542 9.20655 21.5375L0.47928 13.1165C-0.159759 12.4999 -0.159759 11.5001 0.47928 10.8835L9.20655 2.46246C9.84559 1.84585 10.8817 1.84585 11.5207 2.46246Z"
        fill={fill}
      />
    </svg>
  );
}
