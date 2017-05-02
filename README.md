# xy-inc
Backend as a Service

## Dependências

* [Maven versão 3.x.x](https://maven.apache.org/)
* Java 8
* [Docker](https://www.docker.com/)

## Instalação

### MongoDB utilizando o Docker

1. Entre no diretório {PROJECT_PATH}/database/
2. Execute o comando:

```
docker build -t xy-inc-mongo .
```

### Compilando a aplicação

1. Entre no diretório {PROJECT_PATH}/api/
2. Execute o comando:

```
mvn clean package
```

## Execução


1. Suba o banco de dados com o comando:

```
sudo docker run --rm -P -p 27666:27017 --name xy-inc-db xy-inc-mongo
```

2. Execute a aplicação com o comando:

```
java -jar {PROJECT_PATH}/api/target/xy-inc-0.1.0.jar
```

### Criando um modelo

Para criar um modelo faça uma requisição POST na url: `http://localhost:8080/model/create` passando como corpo da requisição o JSON:

```
{
	"name": "Foo",
	"attributes": {
		"foo": "int",
		"bar": "String",
		"fooBar": "Boolean"
	}
}
```

Os tipos de atributos aceitos são:
* bool
* Boolean
* char
* Character
* Date (dd/MM/yyyy HH:mm:ss)
* Double
* Float
* int
* Integer
* Long
* String

### CRUD dos registros

Para criar um registro faça uma requisição POST na url: `http://localhost:8080/api/Foo` passando como corpo da requisição o JSON:

```
{
	"foo": 123,
	"bar": "321",
	"fooBar": true
}
```

Para alterar um registro faça uma requisição PUT na url: `http://localhost:8080/api/Foo/7166b98d-9526-4509-bb5f-4db62f2e5342` passando como corpo da requisição o JSON:

```
{
	"foo": 123,
	"bar": "321",
	"fooBar": false
}
```

Para deletar um registro faça uma requisição DELETE na url: `http://localhost:8080/api/Foo/7166b98d-9526-4509-bb5f-4db62f2e5342`

Para buscar um registro faça uma requisição GET na url: `http://localhost:8080/api/Foo/7166b98d-9526-4509-bb5f-4db62f2e5342`

Para buscar todos os registros faça uma requisição GET na url: `http://localhost:8080/api/Foo`

