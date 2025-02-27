FROM openjdk:21

WORKDIR /usrapp/bin

ENV PORT=6000

COPY /target/classes /usrapp/bin/classes
COPY /target/dependency /usrapp/bin/dependency
COPY /target/classes/static /usrapp/bin/target/classes/static
COPY /target/classes/static /usrapp/bin/target/classes/img

CMD ["java","-cp","./classes:./dependency/*","edu.escuelaing.app.MicroServer"]