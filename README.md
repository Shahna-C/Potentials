# POTENTIALS

>Potentials is a REST Api  designed  as a learning resource for neurodiagnostic clinicians. Clinicians [users] can create lists of various procedures and attach a list of the nerves that they monitor with each procedure. The nerves provide images of where to stimulate, and innervations of each nerve. 


## Technologies
* MySQL - Version 8.0.20
* Java - Version 1.8.0_251
* Eclipse - version 4.15.0
* Apache Maven - Version 3.6.3


## REST API
HTTP Method : CRUD Action : Request
3. POST : Create a nerve: /nerves
1. POST : Create a user: /users
4. POST : Create a url for nerve images: /nerves/{id}/nervePicture
2. POST : Create a procedure: /users/{id}/procedures/{procedureId}
2. GET : Read a list users: / users
3. GET : Read a list of nerves: /nerves
3. GET : Read a list of procedures: /users/{id}/procedures
4. PUT : Update user information: /users/{id}/
4. PUT : Update and existing procedure : /users/{id}/procedures/{procedureId}
5. DELETE : Delete user: /users/{id}
5. DELETE : Delete nerve: /nerves/{id}


## INSTALLATION

1. Install Eclipse, IntelliJ or IDE of your choice for Java and DSL Developers.
    ```
    https://www.eclipse.org/downloads/packages/
    https://www.jetbrains.com/idea/download/
    ```
2. Install MySql and add MySQL path to environment variable
    ```
   https://dev.mysql.com/doc/mysql-windows-excerpt/5.7/en/
   https://dev.mysql.com/doc/mysql-osx-excerpt/5.7/en/
    ```
3. Install Postman
	```
    https://www.postman.com/downloads/
    ```
4. Clone this repository.
    ```
    git clone https://github.com/Shahna-C/Potentials.git

## DEMO
https://github.com/Shahna-C/Potentials/blob/master/Demo.gif




