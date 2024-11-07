# Node.js 22 버전을 사용
FROM node:22

# 작업 디렉터리 설정
WORKDIR /app

# pnpm 설치
RUN npm install -g pnpm@8.15.6

# pnpm store 경로 설정
RUN echo "STORE_PATH=$(pnpm store path --silent)" >> /app/.env

# 의존성 설치에 필요한 파일 복사
COPY package.json pnpm-lock.yaml ./

# 의존성 설치
RUN pnpm install

# 앱 소스 코드 복사
COPY . .

# 빌드 환경 변수 설정 (환경 변수는 Docker 빌드 시 전달됨)
ARG APPLICATION_PROD
RUN mkdir -p ./apps/web && \
    echo $APPLICATION_PROD > ./apps/web/.env

# Turbo 빌드 실행
RUN pnpm turbo run build --filter="web" --concurrency=4

# 실행 명령어
CMD ["pnpm", "start"]