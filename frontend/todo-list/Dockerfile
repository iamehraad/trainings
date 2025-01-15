FROM node:20

WORKDIR /app

COPY package.json package-lock.json ./
RUN npm install

COPY . .

RUN npm install -g typescript

EXPOSE 8080

CMD ["nodemon"]