# Artefactos Antiguos (ms_ancient_artifacts)

El microservicio fue nombrado ms_ancient_artifacts, debido a que el sabio anciano Elowen, requiere rastrear y
asegurar artefactos antiguos que otorgan habilidades mágicas a sus portadores antes de que caigan en manos equivocadas,
dentro de este contexto de ARTEFACTOS ANTIGUOS, se podrán analizar deferentes pistas ya sean manuscritos, imágenes,
hechizos, encantos (con sus próximas evoluciones) con el fin de poder encontrar dichos artefactos.

## Antes de Iniciar

El microservicio esta realizado [Clean Architecture](https://medium.com/bancolombia-tech/clean-architecture-aislando-los-detalles-4f9530f35d7a) con el fin de tener una fácil comprensión del código,
facilitar el reúso de ciertos componentes, separar responsabilidades, proteger la lógica de negocio,  entre otras
ventajas que ofrece esta arquitectura de software.


## Algoritmo Búsqueda de Pistas

### Criterio de Identificación
Un manuscrito contiene una pista sobre la ubicación de un artefacto si se encuentran secuencias de cuatro
letras consecutivas en cualquier dirección (horizontal, vertical o diagonal)

```````
String[] manuscript = {"RTHGQW", "XRLORE", "NARURR", "REVRAL", “EGSILE", "BRINDS"};
```````

Se implementó un algoritmo que recorra la matriz sin importar su tamaño, iniciando en la posición (0,0) 
y validando las 4 siguientes letras (hacia su derecha, abajo, diagonal derecha, diagonal izquierda)

```````
[R][T][H][G][Q][W]
[X][R][L][O][R][E]
[N][A][R][U][R][R]
[R][E][V][R][A][L]
[E][G][S][I][L][E]
[B][R][I][N][D][S]
```````

## Endpoints Implementados

Este microservicio está desplegado dentro EC2 de AWS, con el fin de que el sabio Elowen y sus ayudantes, puedan 
acceder desde cualquier lugar del mundo mediaval fantástico.

### Analisis de Pistas 
[POST → /clue/] El endpoint se encarga de recibir en formato JSON el manuscrito, En caso de que se encuentre una pista 
sobre el artefacto, se retorna un HTTP 200-OK; en caso contrario, un 403-Forbidden.

#### Puede hacer uso del siguiente CURL, desde cualquier lugar del mundo fantástico
curl --location 'http://18.116.85.211/clue/' \
--header 'Content-Type: application/json' \
--data '{"manuscript": ["RTHGQW","áR%%W%","áARWRR","áEWRAL","áGSILE","AAAADS"]}'

#### Clonando el Repositorio
1. Podría clonar este repositorio
2. Abrir en un IDE como IntelliJ el repositorio descargado
3. Ejecutar el archivo MainApplication.java para ejecutar el microservicio
4. Usar el siguiente CURL en su postman para lanzar peticiones a su ambiente local

curl --location 'localhost:8080/clue/' \
--header 'Content-Type: application/json' \
--data '{"manuscript": ["RTHGQW","áR%%W%","áARWRR","áEWRAL","áGSILE","AAAADS"]}'

### Ejemplo pista encontrada

![img.png](img.png)

### Ejemplo pista NO encontrada
![img_1.png](img_1.png)


### Estadisticas de Pistas
[GET → /stats] El endpoint se encarga de mostrar el conteo de los manuscritos que contienen pistas y el conteo de 
los manuscritos que no contienen pistas


#### Puede hacer uso del siguiente CURL, desde cualquier lugar del mundo fantastico
curl --location 'http://18.116.85.211/stats'

#### Clonando el Repositorio
1. Podría clonar este repositorio
2. Abrir en un IDE como IntelliJ el repositorio descargado
3. Ejecutar el archivo MainApplication.java para ejecutar el microservicio
4. Usar el siguiente  CURL en su postman para lanzar peticiones a su ambiente local
   
curl --location 'localhost:8080/stats'


### Ejemplo consulta
![img_2.png](img_2.png)

# Pruebas de Performance

Al inicio del proyecto se habia planeado utilizar una base de datos PostgreSQL, la ejecución dió
como resultado un poco mas de 2 millones de muestras en 5 minutos, pero con una tasa de error del 20%, los errores 
eran relacionados con la base de datos, tenia muchos clientes.

Por esta razón se decidió implementar una base de datos en memoria como H2, la unica contra es que estos datos
al bajar el microservicio se pierden y utilizan la memoria del microservicio que se esté utilizando, como se puede ver en la siguiente imagen donde Java ya tenia ocupado 4GB de memoria con los datos de las pruebas de performance.

![img_3.png](img_3.png)

Aun así se obtuvieron buenos resultados y con una tasa de error del 0%, superando 6 millones de muestras en 4 minutos y
mas de 200 mil transacciones por segundo, ejecuando esto desde el computador en local. esto para el entpoint de almacenar y buscar la pista POST::/Clue/

![img_5.png](img_5.png)

Para el endpoint GET::/Stats, se decidió que se consultara en la base de datos por medio de un conteo a los datos que se encontraron pistas
y a los que no se encontraron pistas, antes se habia definido con PostgreSQL manejar un proceso almacenado para que actualizara el registro de las estadisticas
esto llevó a que la base de datos se saturara un poco, y tambien para no afectar o darle mas carga al endpoint de Clues y en un ambiente real se necesita que Clues tenga mas performance que el endpoint de estadisticas,
el resultado unamuestra de mas de 6 millones 500 mil, 0% de error, teniendo encuenta que cada consulta estaba analizando un poco mas de 6 millones de registros / 4GB de datos

![img_4.png](img_4.png)

Debo recordar que esto es en un ambiente local, muy probablemente en un ambiente de EKS, con balanceador de carga, y una configuración de subir recursoss horizontalemente y verticalmente se podrian tener muy buenos resultados 
aún así usando una base de datos postgres si es que el sabio requiere almacenar estos datos por mucho mas tiempo.

A futuro se podría mejorar el performance usando una caché centralizada como Redis, para que retenga la información por determinado tiempo y en tiempos de descanso del servidor o en determinado tiempo enviar a guardar por bloques a la base de datos como Batch Processor.



