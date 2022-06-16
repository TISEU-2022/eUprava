FROM node:16.13.2 as build-stage
WORKDIR /app
COPY package.json package-lock.json /app/
RUN npm install
COPY ./ /app/
RUN npm run build
