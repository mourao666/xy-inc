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
	"name": "ModelName",
	"attributes": {
		"attribute1": "attributeType",
		"attribute2": "attributeType",
		"attributeN": "attributeType"
	}
}
```

Os tipos de atributos aceitos são:
* bool
* Boolean
* char
* Character
* Date
* Double
* Float
* int
* Integer
* Long
* String

