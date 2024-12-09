/** @type {import('next').NextConfig} */
module.exports = {
  reactStrictMode: true,
  transpilePackages: ["@repo/ui"],
  images: {
    domains: ["media.qua.world", "qua-assets.s3.ap-northeast-2.amazonaws.com"],
  },
};
