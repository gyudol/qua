import type { Config } from 'tailwindcss';

// We want each package to be responsible for its own content.
const config: Omit<Config, 'content'> = {
  theme: {
    extend: {
      colors: {
        primary: '#48d0bf',
        secondary: '#a853ba',
        tertiary: '#e92a67',
      },
      backgroundImage: {
        'glow-conic':
          'conic-gradient(from 180deg at 50% 50%, #2a8af6 0deg, #a853ba 180deg, #e92a67 360deg)',
      },
      dropShadow: {
        primary: '0 0px 15px #0CEDA980',
      },
    },
  },
  plugins: [],
};
export default config;
