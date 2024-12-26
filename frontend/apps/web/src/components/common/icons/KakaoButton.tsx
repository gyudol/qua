import type { IconProps } from "./@type";

function KakaoButton({ width = 20, height = 24 }: IconProps) {
  return (
    <div>
      <svg
        width={width}
        height={height}
        viewBox="0 0 20 24"
        fill="none"
        xmlns="http://www.w3.org/2000/svg"
      >
        <g clipPath="url(#clip0_104_8770)">
          <path
            fillRule="evenodd"
            clipRule="evenodd"
            d="M10 1.5C4.47682 1.5 -6.10352e-05 5.3912 -6.10352e-05 10.1904C-6.10352e-05 13.175 1.73151 15.8062 4.36832 17.3712L3.25887 21.9306C3.16085 22.3335 3.57041 22.6546 3.88491 22.4212L8.74813 18.8103C9.15854 18.8548 9.57562 18.8808 10 18.8808C15.5227 18.8808 19.9999 14.9898 19.9999 10.1904C19.9999 5.3912 15.5227 1.5 10 1.5Z"
            fill="black"
          />
        </g>
        <defs>
          <clipPath id="clip0_104_8770">
            <rect
              width="20"
              height="22.5"
              fill="white"
              transform="translate(-6.10352e-05 0.75)"
            />
          </clipPath>
        </defs>
      </svg>
    </div>
  );
}

export default KakaoButton;
