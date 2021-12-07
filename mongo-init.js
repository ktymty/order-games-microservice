db = db.getSiblingDB('catalog-service');

db.createUser({
    user: "guest",
    pwd: "guest",
    roles: [{
        role: "readWrite",
        db: "catalog-service"
    }]
});

db.createCollection('game');

db.game.insertMany([{
        id: '1',
        name: 'GAME1',
        price: '20,09€',
        gameId: 'tt1160419'
    },
    {
        id: '2',
        name: 'GAME2',
        price: '50,00€',
        gameId: 'tt2382320'
    },
    {
        id: '3',
        name: 'GAME3',
        price: '49,45€',
        gameId: 'tt10919420'
    },
    {
        id: '4',
        name: 'GAME4',
        price: '49,45€',
        gameId: 'tt6751668'
    }
]);