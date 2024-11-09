# Node.js 22 버전을 사용
FROM node:22

# 작업 디렉터리 설정
WORKDIR /app

# pnpm 설치
RUN npm install -g pnpm@9.12.3

# 의존성 설치에 필요한 파일 복사
# COPY package.json pnpm-lock.yaml ./
COPY . .

# 의존성 설치
RUN pnpm install

# Turbo 빌드 실행
RUN pnpm turbo build

# 실행 명령어
CMD ["pnpm", "-F=web", "start"]