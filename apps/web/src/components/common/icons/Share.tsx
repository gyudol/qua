import type { IconProps } from "./@type";

export default function Share({
  width = 20,
  height = 20,
  fill = "none",
  stroke = "#838383",
}: IconProps) {
  return (
    <svg
      width={width}
      height={height}
      viewBox="0 0 20 20"
      fill={fill}
      xmlns="http://www.w3.org/2000/svg"
    >
      <path
        d="M18.7087 1.29125C20.2747 2.85717 15.1249 19 12.8058 19C11.0892 19 8.87055 13.0971 8.87055 11.1294C8.87055 11.1294 1 8.91076 1 7.19417C1 4.87509 17.1427 -0.27469 18.7087 1.29125Z"
        stroke={stroke}
        strokeWidth="1.7"
        strokeLinejoin="round"
      />
    </svg>
  );
}
