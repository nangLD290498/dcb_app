# dcb_app
Prerequisites: config springboot apps on java 8, create postgre database named "dcb"
Step 1: run 2 app dcb_app and operator_app
Step 2: call postman API: 

curl --location --request POST 'http://localhost:8080/api/transaction/charge' \
--header 'Content-Type: application/json' \
--data-raw '{
    "chargingRequest": {
        "customerInfo": {
            "mobileNo": "6281990449203"
        },
        "transactionInfo": {
            "transactionId": "ff0ced5b-eb31-4eea-8105-d4ebe9557c88",
            "item": "SPOTIFY_G",
            "itemDescription": "SPOTIFY_G",
            "balanceType": "AirBalance",
            "amount": "1000",
            "currency": "IDR"
        }
    }
}'
