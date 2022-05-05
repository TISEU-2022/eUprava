FROM node:16.13.2 as build-stage
WORKDIR /app
COPY package.json package-lock.json /app/
RUN npm install
COPY ./ /app/
RUN npm run build

FROM nginx:1.15
COPY --from=build-stage /app/dist/ /usr/share/nginx/html
COPY /nginx.conf /etc/nginx/conf.d/default.conf