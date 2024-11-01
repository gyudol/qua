import type { IconProps } from "./type";

export default function Post({
  width = 48,
  height = 49,
  fill = "#47D0BF",
}: IconProps) {
  return (
    <svg
      width={width}
      height={height}
      viewBox="0 0 48 49"
      fill="none"
      xmlns="http://www.w3.org/2000/svg"
    >
      <rect y="0.696289" width={width} height={height} rx={width} fill={fill} />
      <path
        d="M23.9999 34.6963C23.2493 34.6963 22.6411 34.0881 22.6411 33.3374V16.0551C22.6411 15.3045 23.2493 14.6963 23.9999 14.6963C24.7505 14.6963 25.3588 15.3045 25.3588 16.0551V33.3374C25.3588 34.0881 24.7505 34.6963 23.9999 34.6963Z"
        fill="white"
      />
      <path
        d="M32.6411 26.0551H15.3588C14.6082 26.0551 14 25.4469 14 24.6962C14 23.9456 14.6082 23.3374 15.3588 23.3374H32.6411C33.3917 23.3374 34 23.9456 34 24.6962C34 25.4469 33.3917 26.0551 32.6411 26.0551Z"
        fill="white"
      />
    </svg>
  );
}
