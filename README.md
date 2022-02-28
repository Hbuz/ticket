# ticket

## Getting Started


### Main technologies used
 - JDK 11
 - Maven
 - Spring Boot
 - H2


### Installing
* Clone the repository
```
git clone git@github.com:Hbuz/ticket.git
```

* Go to root project
```
cd ticket
```

* Change folder mode
```
chmod +x mvnw
```

* Creating an executable JAR
Execute this command from the root project:
```
./mvnw package
```

### Run the application
Execute this command from the root project:
```
java -jar target/*.jar
```

### Endpoints implemented

- Create a ticket:

```
  POST - https://localhost:8084/tickets
```
  `
  body = {
            "validity_date": "2022-02-25T23:59",
            "userId": 1
         }
`
```
  response = {
    "id": 1,
    "token": "duh7sdDS_BBCz5BIOKJMDbzquBgV3xeU",
    "validity_date": "2022-02-25T23:59:00",
    "user_id": 1,
    "created_on": "2022-02-28T22:44:49.521088"
  }
```

- Get ticket by ID:

```
  GET - https://localhost:8084/tickets/{id}
```
```
response = {
    "id": 2,
    "token": "duh7sdDS_BBCz5BIOKJMDbzquBgV3xeU",
    "validity_date": "2022-02-25T23:59:00",
    "user_id": 1,
    "created_on": "2022-02-28T22:44:49.521088"
}
```


- Get list of tickets

```
  GET - https://localhost:8084/tickets
```
```
response = [
    {
        "id": 1,
        "token": "CMEf9AeSrvk9qKZwEKZmSOrf7KwdUJBz",
        "validity_date": "2022-02-28T23:43:04.732885",
        "user_id": 1,
        "created_on": "2022-02-28T23:43:04.732885"
    },
    {
        "id": 2,
        "token": "duh7sdDS_BBCz5BIOKJMDbzquBgV3xeU",
        "validity_date": "2022-02-25T23:59:00",
        "user_id": 1,
        "created_on": "2022-02-28T22:44:49.521088"
    }
]
```

#### Note
All services can be call with SSL, and I also added an already generated certificate in the classpath by convenience.
So, there's no need to generate it


### Postman
A postman collection has been added to the root project. It can be imported and used in Postman to test the application: 

```Ticket API.postman_collection.json```
