Authors: Marty Vo, Roshan Pai

This project consists of a database application representing statistics of the 2019 NFL season. The front-end of the project is written in 
Java and utilizes the JDBC connector to connect to the MySQL database server. To access and run our project, you will need the following software:

- IDE capable of running a Java file. We recommend IntelliJ or Eclipse.
IntelliJ: https://www.jetbrains.com/idea/download/
Eclipse: https://www.eclipse.org/downloads/

- MySQL Server and Workbench. This will store our database.
Community Server: https://dev.mysql.com/downloads/mysql/
Workbench: https://dev.mysql.com/downloads/workbench/

- JDBC Connector. This connector will allow the front-end to communicate with the database in the back-end.
Connector/J: https://dev.mysql.com/downloads/connector/j/

After you have the required software installed, use the IDE to open the front-end java file. Move the JDBC connector jar to the same 
project location as the front-end java file. Using MySQL Workbench, run the database dump to create the database server with all objects,
triggers, functions, and procedures. Now go back to the IDE, and run the front-end java file. In the console you should now see a series of 
instructions that will allow you to perform CRUD operations on the database.
