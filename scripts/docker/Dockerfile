FROM eclipse-temurin:17-jre-jammy

ENV TZ=Asia/Shanghai

RUN mkdir /web && \
    mkdir /app
COPY entrypoint.sh /entrypoint.sh
COPY backend/*.jar /app/app.jar
COPY frontend.tar.gz /app/frontend.tar.gz

ENTRYPOINT ["/entrypoint.sh"]
CMD ["java", "-jar", "/app/app.jar", "--spring.profiles.active=production"]
