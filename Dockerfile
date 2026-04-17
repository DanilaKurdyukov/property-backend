FROM gradle:8.11.1-jdk21 AS build
WORKDIR /app
COPY . .
RUN gradle installDist --no-daemon

FROM eclipse-temurin:21-jre
WORKDIR /app
COPY --from=build /app/build/install/backend /app
ENV PORT=10000
EXPOSE 10000
CMD ["sh", "-c", "./bin/backend"]