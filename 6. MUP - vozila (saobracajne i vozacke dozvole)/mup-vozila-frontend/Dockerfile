FROM node:alpine AS build-phase
WORKDIR /app
COPY . .
RUN npm ci && npm run build

FROM nginx:alpine
COPY nginx.config /etc/nginx/conf.d/default.conf
COPY --from=build-phase /app/dist/mup-vozila-frontend /usr/share/nginx/html
