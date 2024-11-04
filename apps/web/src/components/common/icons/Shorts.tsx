import type { IconProps } from "./@type";

export default function Shorts({
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
      <path
        d="M2 17.4463V7.94629C2 6.70365 3.00736 5.69629 4.25 5.69629H12.75C13.9926 5.69629 15 6.70365 15 7.94629V10.6963L19.5238 6.81873C20.4968 5.98473 22 6.67609 22 7.95762V17.7815C22 19.0016 20.6209 19.7113 19.6281 19.0021L15 15.6963V17.4463C15 18.6889 13.9926 19.6963 12.75 19.6963H4.25C3.00736 19.6963 2 18.6889 2 17.4463Z"
        stroke={stroke}
        strokeWidth="1.5"
        strokeLinecap="round"
        strokeLinejoin="round"
      />
    </svg>
  );
}
