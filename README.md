#### Incluir Pedido (POST)

```bash
curl --location 'http://localhost:8080/pedido' \
--header 'Content-Type: application/json' \
--data '{
   "clientName": "Gabriela",
   "tipo": "Pessoa fisica",
   "cnpjOuCpf": "17712278000684",
   "dataCompra": "02/05/2024",
   "itens": [
       {
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

#### Buscar Pedido (GET)
curl --location 'http://localhost:8080/pedido/1'

#### Alterar Pedido (PUT)
curl --location --request PUT 'http://localhost:8080/pedido/1' \
--header 'Content-Type: application/json' \
--data '{
   "clientName": "Tiago",
   "tipo": "Pessoa fisica",
   "cnpjOuCpf": "17712278000684",
   "dataCompra": "02/05/2024",
   "itens": [
       {
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

#### Deletar Pedido (DELETE)
curl --location --request DELETE 'http://localhost:8080/pedido/2'


Neste formato:
- Cada operação CRUD é descrita em uma seção separada com um título descritivo.
- Os exemplos de requisições cURL estão formatados como blocos de código para melhor legibilidade.
- Cada exemplo inclui uma breve descrição do que a operação realiza.
- O texto está organizado de forma clara e concisa para facilitar o entendimento e uso da API.
