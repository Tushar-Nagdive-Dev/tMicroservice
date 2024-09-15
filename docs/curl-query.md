### POST Create Account
`curl -X POST http://localhost:8001/api/create -H "Content-Type: application/json" -d '{"name": "John Cena", "email": "johncena@example.com", "mobileNumber":"6234561728"}'`

create account and customer entry in DB

### PUT Update Account

`curl -X PUT http://localhost:8001/api/update -H "Content-Type: application/json" -d '{"name": "John Cena", "email":"johncena@example.com", "mobileNumber":"6234561728", "accountDto": {"accountNumber":"1804881485","accountType":"Current","branchAddress":"Sch 114, Rajiv Awas Vihar"}}'`

update account and customer entry in DB

### GET fetcg Account

`curl -X GET http://localhost:8001/api/fetch\?mobileNumber\=6234561728`

get account details by mobilenumber


### Encrypt In Spring config
`curl -X POST http://localhost:8000/encrypt -d 'bigBank@cards.com'`
Encrypt 
### Decrypt In Spring Config
`curl -X POST http://localhost:8000/decrypt -d 'f98da8b76ea61e6bf582b87ea6c41f951113c27bd2f5e7b50c7fbd57a7f5a6f9cfae63849f16233adc5c4f66ab7800e8'`
Decrypt
### Refresh Spring cloud config data
`curl -X POST http://localhost:8002/actuator/refresh`
Refresh Data

### BusRefresh
`curl -X POST http://localhost:8000/actuator/busrefresh`
Refresh all instance data
