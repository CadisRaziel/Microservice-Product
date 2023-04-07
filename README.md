### Arquitetura projeto utilizando Java(Spring) - RabbitMQ


>Idealizando a aplicação

# Product-ms

## Endpoints
- BaseUrl: /products
- POST: create()
- GET: getAll()
- GET/{id}: getById()
- PUT/{id}: update()
- DELETE//{id}: delete()

## Model
``` 
{
"id": 1,
"name": "Iphone 13 pro max",
"description": "Celular de ultima geração"
"price": 699.99,
"isAvailable": true (para brinca com a mensageria, um listener pra conversa entre dois microservice)
}
```

## Business Rules
- Só é possivel a criação de produtos com preço positivo;
- Não é possivel pesquisar produtos que não estão disponiveis;
- Não é possivel inserir descrição com menos de 50 caracteres

## Dependencias utilizadas
- Spring Boot
- Spring Web
- Spring Jpa
- Spring Validation
- H2
- Lombok
- Model Mapper