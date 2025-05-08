# Gamer Directory API
## Description
A RESTful API built with Spring Boot to manage gamers, the games they play, and their skill levels.
Useful for matchmaking, analytics, or leaderboard integration.

## How To Run
1. Clone the repository:
```bash
git clone https://github.com/broxzx/gaming-api.git
```
2. Navigate to the project directory:
```bash
cd gaming-api
```
3. Run command `docker-compose up -d`
4. Run the application (via IDE or CLI):
```bash
./mvnw spring-boot:run
```

if you need more detailed logging consider setting profile to dev (via IDE or CLI):
```bash
./mvnw spring-boot:run -Dspring-boot.run.profiles=dev
```

- [Description](#description)
- [How To Run](#how-to-run)
- [API Endpoints](#api-endpoints)
    - [Game Management](#game-management)
    - [Gamer Management](#gamer-management)
    - [Gamer Progress Management](#gamer-progress-management)
- [Swagger](#swagger)

## API Endpoints
Base URL `http://localhost:8080/api/v1`

### Game Management
Create a game
`POST /games`

Request:
```json
{
    "name": "game name",
    "released": "2021-01-01"
}
```

Response
```json
{
    "name": "game name",
    "released": "2021-01-01"
}
```


### Gamer Management
#### Create a gamer
`POST /gamers`

Request:
```json
{
    "username": "mock data",
    "email": "mock-data@gmail.com",
    "geography": "europe"
}
```

Response:
```json
{
  "username": "test",
  "email": "test1@gmail.com",
  "geography": "Europe",
  "createdAt": "2025-05-08T08:19:16.596927Z",
  "updatedAt": "2025-05-08T08:19:16.596946Z"
}
```

#### Search for gamer
`GET /gamers`

Available parameters: `level`, `game`, `geography`

Request:
`/api/v1/gamers?level=INVINCIBLE`

Response:
```json
{
    "content": [
        {
            "username": "ninja",
            "game": "Red Dead Redemption 2",
            "email": "ninja@gmail.com",
            "geography": "South America",
            "createdAt": "2025-05-08T07:59:52.775182Z",
            "updatedAt": "2025-05-08T07:59:52.775182Z"
        },...
    ],
    "pageable": {
        "pageNumber": 0,
        "pageSize": 10,
        "sort": {
            "sorted": false,
            "empty": true,
            "unsorted": true
        },
        "offset": 0,
        "paged": true,
        "unpaged": false
    },
    "size": 10,
    "number": 0,
    "first": true,
    "last": true,
    "numberOfElements": 8,
    "sort": {
        "sorted": false,
        "empty": true,
        "unsorted": true
    },
    "empty": false
}
```

#### Search for gamers by level and grouped by game
`GET /levels/{level}`

Request: 
`/api/v1/gamers/levels/NOOB`

Response
```json
{
    "data": {
        "The Witcher 3": [
            {
                "username": "dark_knight",
                "game": "The Witcher 3",
                "email": "dark-knight@gmail.com",
                "geography": "South America",
                "createdAt": "2025-05-08T07:59:52.775182Z",
                "updatedAt": "2025-05-08T07:59:52.775182Z"
            },...
        ],
        "Red Dead Redemption 2": [
            {
                "username": "knight",
                "game": "Red Dead Redemption 2",
                "email": "knight@gmail.com",
                "geography": "Asia",
                "createdAt": "2025-05-08T07:59:52.775182Z",
                "updatedAt": "2025-05-08T07:59:52.775182Z"
            },...
        ],...
    }
}
```

### Gamer Progress Management
#### Link Gamer to Game
`POST /gamers/link/games`

Request:
```json
{
    "gamerId": "580c9896-0bbe-47d1-b559-b40386a918f8",
    "gameId": "933cc925-51d8-4932-bd3d-7f03204e3282",
    "levelType": "NOOB"
}
```

Response:
```json
{
    "gamerId": "580c9896-0bbe-47d1-b559-b40386a918f8",
    "gameId": "933cc925-51d8-4932-bd3d-7f03204e3282",
    "levelType": "NOOB"
}
```

## Swagger
For more detailed inputs/output data for api you can visit `http://localhost:8080/swagger-ui/index.html`