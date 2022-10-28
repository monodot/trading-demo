# First we use webpack to build a self-contained test script containing Faker
FROM docker.io/library/node:18.12.0 AS build

WORKDIR /app
COPY package.json package-lock.json webpack.config.js .
COPY src ./src

RUN npm install && npm run pretest

# Copy the test script into a new container based on the k6 image
FROM docker.io/grafana/k6:0.40.0
WORKDIR /app
COPY --from=build /app/dist/* /app/

ENV TRADING_SERVICE_BASE_URL=http://trading-service:8080

LABEL org.opencontainers.image.source https://github.com/monodot/trading-demo

ENTRYPOINT ["k6", "run", "/app/test.trade.js"]
