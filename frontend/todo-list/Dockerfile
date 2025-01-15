# Use Node.js image
FROM node:20

# Set the working directory inside the container
WORKDIR /app

# Install dependencies
COPY package.json package-lock.json ./
RUN npm install

# Copy application files
COPY . .

# Install TypeScript globally for transpiling
RUN npm install -g typescript

# Expose the application port
EXPOSE 8080

# Start the application with nodemon
CMD ["nodemon"]