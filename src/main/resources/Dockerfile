# Imagen base de OpenJDK
FROM openjdk:17-jdk-slim

# Crear un directorio para la aplicación
WORKDIR /app

# Copiar el JAR generado en el directorio de la aplicación
COPY target/ExamenParcial-0.0.1-SNAPSHOT.jar app.jar

# Exponer el puerto en el que corre la aplicación
EXPOSE 8082

# Comando para ejecutar el JAR
ENTRYPOINT ["java", "-jar", "app.jar"]
