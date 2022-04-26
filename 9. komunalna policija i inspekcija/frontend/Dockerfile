FROM node:13.12.0-alpine

WORKDIR /app

COPY package.json ./
RUN npm install

COPY . ./

EXPOSE 9001

CMD ["npm", "start"]