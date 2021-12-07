![GamesOrderService](https://user-images.githubusercontent.com/15968335/145801080-e4a311c1-9f05-4fa9-bbbe-8f2bea88b073.png)
### Games Order Service
Using the Games Order Service one can check a catalog of games available at an online shop for order. 
Catalog service can be used to also add new games to MongoDB.
Catalog information is stored in MongoDB nosql database
The description and ratings are fetched from an external api with retry mechanism.

After the user views the catalog, she can proceed to order one or games from the Catalog.
Order Service takes an skuCode from client. Checks with Inventory Service if stock is available and then proceeds to create the Order and send a message to message queue.
Notification Service acts a consumer of the messages. It sends out ( currently just logs) an email to user on a successful order.
Order Service connects with Inventory Service over open feign client utilizing Circuit breaker pattern.

Order and Inventory information are stored in postgres sql database.
Configuration for the microservices are fetched from git repository using Spring Cloud Config.

Observability is enforced using Sleuth and Zipkin distributed logging and tracing.

### Order Of Starting Microservices
1. games-discovery-server:8761- acts as a service registry for microservices
2. games-config-server:9296 - helps to get config values from external source such as github
3. games-notification-service:9001 - is a consumer for message queue and sends email
4. games-catalog-service:<random-port> - games can be listed and added. backed by mongodb and makes call to external api to get ratings. can be enhanced by caching.
5. games-inventory-service:<random-port> - keeps details about games availability for purchase. backed by h2. can be moved to postgres.
6. games-order-service:<random-port> - used for ordering games using sku code. checks with inventory service if there is stock. places order. saves order in database.
7. games-api-gateway:9000 - routes api requests from client/frontend to microservices(catalog, order)

### API Endpoints

* Base URL
  /v1/api
  
* Games Catalog Service
  * GET: /catalog (lists all games)
  
  * POST: /catalog (save game)
  ```json
  {
  "name":"GAME5",
  "price": "50,00€",
  "gameId": "tt2382320"
  }
  ```
  * DELETE: /catalog (delete game)
  ```json
  {
    "id": "56893903"
  }
  ```
  
* Order Service
  * POST: /order (post order)
      ```json
      {
        "orderLineItemsList":[
          {
            "skuCode":"GAME1",
            "price":50,
            "quantity":2
          }
        ]
      }
      ```

### Postgres in docker
psql -h localhost -p 5432 -U root -d orderservice
psql -h localhost -p 5432 -U root -d inventoryservice
dt
select * from inventory;
