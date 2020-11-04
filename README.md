# Test Search Service

## Dependencias

* Git
* Java 11

## Tasks
Opciones para ejecutar la aplicación

```
$ ./gradlew bootRun
```
```
$ make run
```

Opciones para ejecutar las pruebas
```
$ ./gradlew unitTest componentTest
```
```
$ make test
```

Opciones para ejecutar las pruebas de mutación
```
$ ./gradlew pitest
```
```
$ make pitest
```

Opciones para ejecutar las pruebas de component
```
$ ./gradlew componentTest
```
```
$ make component-test
```

Opciones para ejecutar las pruebas de integración

El tunnel de INT debe estar conectado
```
$ make integration-test
```

Ejecutar sonar local
```
$ ./gradlew unitTest jacocoTestReport sonarqube -Dsonar.analysis.mode="preview"
```

```
$ make sonar-preview
```

## Endpoints standares


