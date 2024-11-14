FROM node:22

WORKDIR /app

RUN npm install -g pnpm@9.12.3

COPY . .

RUN pnpm install

RUN pnpm turbo build

CMD pnpm -F=web start