#Define la imagen base desde la cual se construirá el contenedor. Aquí usamos Alpine Linux versión 3.19.
#Alpine es una distribución ligera (unos 5 MB), ideal para contenedores, y la versión 3.19 es reciente
#(a marzo de 2025), con soporte para paquetes actualizados como OpenJDK.
#Es perfecta para Java por su tamaño y estabilidad.
FROM alpine:3.19

#Ejecuta el comando apk update en el contenedor durante la construcción para actualizar la lista de paquetes disponibles en los repositorios de Alpine.
#Necesitamos la lista más reciente de paquetes para instalar y actualizar componentes como Java. El && concatena comandos en la misma línea para ejecutarlos secuencialmente.
RUN apk update && \
    apk upgrade --no-cache && \
    apk add --no-cache openjdk17

#Establece el directorio de trabajo dentro del contenedor en /app. Todos los comandos posteriores (como COPY o CMD) se ejecutan desde este directorio.
WORKDIR /app

#Copia el archivo JAR de tu aplicación Spring Boot (generado por Maven en target/) desde tu máquina local al directorio /app/ dentro del contenedor, renombrándolo a app.jar.
COPY target/msvc-patterns-sales-0.0.1-SNAPSHOT.jar /app/app.jar

#Indica que el contenedor expondrá el puerto 8080 en tiempo de ejecución.
EXPOSE 8080

#Define el comando predeterminado que se ejecutará cuando el contenedor inicie. Aquí, ejecuta java -jar app.jar.
CMD ["java", "-jar", "app.jar"]