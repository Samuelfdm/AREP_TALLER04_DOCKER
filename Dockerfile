FROM openjdk:21

WORKDIR /usrapp/bin

ENV PORT=6000

# Copiar clases compiladas
COPY /target/classes /usrapp/bin/classes
COPY /target/dependency /usrapp/bin/dependency
# Copiar archivos estaticos directamente a un directorio accesible
COPY src/main/resources/static /usrapp/bin/src/main/resources/static
COPY /target/classes/static /usrapp/bin/static

CMD ["java","-cp","./classes:./dependency/*","edu.escuelaing.app.MicroServer"]