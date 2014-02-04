1. for creating executable jar with dependecies run below command :
mvn clean compile assembly:single

2. run executable jar
java -jar <name of jar> <arguments if any>
e.g. java -jar calendar-event-batch.jar <directory from where we need to read XML/PDF>
