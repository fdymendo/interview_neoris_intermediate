FROM amazoncorretto:11 as builder 
WORKDIR application 
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} application.jar 
RUN java -Djarmode=layertools -jar application.jar extract 

FROM amazoncorretto:11 
WORKDIR application
EXPOSE 8080
COPY --from=builder application/dependencies/ ./ 
COPY --from=builder application/spring-boot-loader/ ./ 
COPY --from=builder application/snapshot-dependencies/ ./ 
COPY --from=builder application/application/ ./ 
ENTRYPOINT ["java", "org.springframework.boot.loader.JarLauncher"]