import type { IconProps } from "./@type";

function NaverButton({ width = 25, height = 25 }: IconProps) {
  return (
    <div>
      <svg
        width={width}
        height={height}
        viewBox="0 0 17 16"
        fill="none"
        xmlns="http://www.w3.org/2000/svg"
      >
        <g clipPath="url(#clip0_71_45)">
          <g filter="url(#filter0_d_71_45)">
            <path
              d="M11.3491 8.56267L5.41687 0H0.5V16H5.65088V7.436L11.5831 16H16.5V0H11.3491V8.56267Z"
              fill="#03C75A"
            />
          </g>
        </g>
        <defs>
          <filter
            id="filter0_d_71_45"
            x="-3.5"
            y="0"
            width="24"
            height="24"
            filterUnits="userSpaceOnUse"
            colorInterpolationFilters="sRGB"
          >
            <feFlood floodOpacity="0" result="BackgroundImageFix" />
            <feColorMatrix
              in="SourceAlpha"
              type="matrix"
              values="0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 127 0"
              result="hardAlpha"
            />
            <feOffset dy="4" />
            <feGaussianBlur stdDeviation="2" />
            <feComposite in2="hardAlpha" operator="out" />
            <feColorMatrix
              type="matrix"
              values="0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0.25 0"
            />
            <feBlend
              mode="normal"
              in2="BackgroundImageFix"
              result="effect1_dropShadow_71_45"
            />
            <feBlend
              mode="normal"
              in="SourceGraphic"
              in2="effect1_dropShadow_71_45"
              result="shape"
            />
          </filter>
          <clipPath id="clip0_71_45">
            <rect
              width="16"
              height="16"
              fill="white"
              transform="translate(0.5)"
            />
          </clipPath>
        </defs>
      </svg>
    </div>
  );
}

export default NaverButton;
