# ticket

Endpoints that can be called:

- Create a ticket:
POST - https://localhost:8084/ticket 
  body = {
            "validity_date": "2022-02-24T20:42:04.191588",
            "userId": 1
         }
         
- Get ticket by ID:
GET - https://localhost:8084/ticket/{id}

- Get list of tickets
GET - https://localhost:8084/ticket


The services can be call with SSL, and I also added an already generated certificate in the classpath by convenience.
So, there's no need to generate it
