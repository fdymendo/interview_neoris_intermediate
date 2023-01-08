# interview_neoris_intermediate

This is a repository created for neoris

## Prueba

El archivo de psotman se encuentra en la raiz con el nombre:

* Neoris.postman_collection.json

Las pruebas de crud estan en los paquetes

* Cliente
* Cuenta
* Transacciones

Y las pruebas de casos de uso en:

* Casos de uso

Nota: Version de export 2.1

## Docker

compilar 

```
mvn clean package
```

crear imagen

```
docker build -t neoris .
```

Ejecutar imagen

```
docker run -it -p 8080:8080 neoris
```