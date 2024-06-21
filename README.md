POST - INCLUIR PEDIDO
curl --location 'http://localhost:8080/pedido' \
--header 'Content-Type: application/json' \
--data '{
   "clientName": "Gabriela",
   "tipo": "Pessoa fisica",
   "cnpjOuCpf": "17712278000684",
   "dataCompra": "02/05/2024",
   "itens":[{
        "descricao": "calça de shopping",
        "quantidade": 2,
        "valor": 180.0
        },
        {
        "descricao": "bolsaLV",
        "quantidade": 1,
        "valor": 2000.0
        }
   ]
}'
----------------------------------------------------
GET - BUSCAR PEDIDO
curl --location 'http://localhost:8080/pedido/1'
----------------------------------------------------
PUT - ALTERAR PEDIDO
curl --location --request PUT 'http://localhost:8080/pedido/1' \
--header 'Content-Type: application/json' \
--data '{
   "clientName": "tiago",
   "tipo": "Pessoa fisica",
   "cnpjOuCpf": "17712278000684",
   "dataCompra": "02/05/2024",
   "itens":[{
        "descricao": "calça de shopping",
        "quantidade": 2,
        "valor": 150.0
        },
        {
        "descricao": "bolsaLV",
        "quantidade": 1,
        "valor": 300.0
        }
   ]
}'
---------------------------------------------------
DELETE - DELETAR PEDIDO
curl --location --request DELETE 'http://localhost:8080/pedido/2'
