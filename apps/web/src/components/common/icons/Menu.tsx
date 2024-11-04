import type { IconProps } from "./@type";

export default function Menu({
  width = 22,
  height = 20,
  fill = "#47D0BF",
}: IconProps) {
  return (
    <svg
      width={width}
      height={height}
      viewBox="0 0 22 20"
      fill="none"
      xmlns="http://www.w3.org/2000/svg"
    >
      <rect y="0.348145" width="18" height="3" rx="1.5" fill={fill} />
      <rect y="8.34814" width="22" height="3" rx="1.5" fill={fill} />
      <rect y="16.3481" width="11" height="3" rx="1.5" fill={fill} />
    </svg>
  );
}
