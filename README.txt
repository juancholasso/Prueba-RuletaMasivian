URL GitHub: https://github.com/juancholasso/ruleta_masivian
URL Heroku: https://roulettemasivian.herokuapp.com/

Descripción
Como persistencia de datos usé Redis, para construir y correr el contenedor en docker
basta con ejecutar el script run_docker.sh. En el interior del código existe una carpeta llamada
collections, en el se encuentra la colección de las peticiones al servidor con postman.
Redis tambien se encuentra alojado como un servicio de heroku.

Las URL de la aplicación son las siguientes

-https://roulettemasivian.herokuapp.com/
Muestra el JavaDoc de la aplicación

-https://roulettemasivian.herokuapp.com/api/roulette/create
Crea una nueva ruleta, no necesita parámetros. Inicializa el estado de la ruleta como cerrado

-https://roulettemasivian.herokuapp.com/api/roulette/{idRoulette}/open
Cambia el estado a abierto de la ruleta con idRoulette

-https://roulettemasivian.herokuapp.com/api/roulette/{idRoulette}/close
Cambia el estado a cerrado de la ruleta con idRoulette

-https://roulettemasivian.herokuapp.com/api/roulette/list
Lista las ruletas

-https://roulettemasivian.herokuapp.com/api/roulette/{idRoulette}/bet
Crea una apuesta sobre la ruleta con idRoulette, como header va el idclient, y como body 
existen 2 opciones, un JSON con amount y color, si es que la apuesta va por color,
o amount y number si la apuesta va por número.

-https://roulettemasivian.herokuapp.com/api/client/create
Crea un nuevo cliente, por parámetro en JSON va el id del cliente y money (la cantidad de dinero que tiene
para apostar)

-https://roulettemasivian.herokuapp.com/api/client/{idclient}/addmoney
Agrega dinero a un Cliente con idclient
