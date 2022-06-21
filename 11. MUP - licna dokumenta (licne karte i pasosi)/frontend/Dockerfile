FROM node:16.15.0
WORKDIR /app
COPY package.json /app
RUN npm install
COPY . /app
EXPOSE 11000
CMD npm start
