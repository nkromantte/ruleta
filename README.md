Migle Aoyu
Hernesto Beltrone
Marco Orozco
Esteban Garcia

Ruleta de casino

El archivo en zip compilado en Netbeans IDE 8.2
El codigo suelto compilado en Intellij IDEA Comunnity Edittion

Es una lista sinple enlazadas o en palabras criollas una lista circular

La ruleta es un lista circular, que se carga con el archivo ruleta.txt Importante: No perder este archivo

Los jugadores al registrarse crean un archivo con su id Nombre Apellido y Saldo 

La ruleta acepta hasta 4 jugador como maximo, y cada uno tendra su Jugador.txt correspondiente

Las apuestas es una lista circular donde se cargan los datos de los jugadores que se cargaran del archivo jugadorX.txt
id                      
nombre
apellido
saldo

Y los datos de apuestas se guardaran cada vez que el jugador en su ronda haga una apuesta en su respectivo formato
1En un solo número                  "Cantidad apostad" / "Numero apostado"        -   "10/26"                                         
2Varios grupos de números           "Cantidad apostad1 - Cantidad apostadn " / "Numero apostado1 - Numero apostadon"      "10-10-5-4-1-10/32-34-32-5-25-24"                                             
3los colores rojo o negro           "Cantidad apostad" / "color apostado"           "10/0" "10/1" "10/2"       No = 0   rojo = 1 negro = 2              
4Si el número es par o impar        "Cantidad apostad" / "impar o par apostado"           "10/0" "10/1" "10/2"       No = 0   impar = 1 par = 2                                               
5Los numeros altos (19-36)          "Cantidad apostad" / "impar o par apostado"           "10/0" "10/1"     Alto = 1           No= 0                             
6Los Numeros bajos  (1-18)          "Cantidad apostad" / "impar o par apostado"           "10/0" "10/1"     bajos = 1          No= 0  


Cada nodo de la lista circular de los jugadores mantendría este orden de datos

"1","Bender","Contreras","100","10/26","10-10-5-4-1-10/32-34-32-5-25-24","10/2","10/1","10/1","10/0"

Si un jugador decide retirarse de la mesa y del juego. lo puede hacer en cada turno que le toque.

Todas las apuestas de cada jugador, se guardan en un archivo log.out junto a la hora de la apuesta  dia mes año hora minutos segundos

La ruleta en cada ronda mostrara un valor distinto, un puntero va recorriendo en forma horaria cada nodo y solo se detiene cuando se cumple una condicion de 0.05%

Se envian el analisis de resultados de las apuestas Y solo los jugadores que ganaron en esa ronda se guardaran en un archivo "ganadores.out"

El juego termina cuando todos se salen de la mesa o queda al menos uno jugando.

Advertencia!! en el menu principal se pueden ver los resultados de los ganadores, y los logs. No borrar los archivos o no les mostrara nada.

Readme.txt                                  

La ruleta es un juego de casino que lleva el nombre de la palabra francesa roulette.

Instrucciones de la Ruleta

      En el juego los jugadores pueden optar por hacer apuestas:                      

       1) En un solo número                   multiplicador x35
       2) Varios grupos de números            multiplicador x36/N          
       3) los colores rojo o negro            multipilicador  x1
       4) Si el número es par o impar         multipilicador  x1     
       5) Los numeros altos (19-36)           multipilicador  x1                                            
       6) Los Numeros bajos  (1-18)           multipilicador  x1              
       Premio consuelo la ruleta cae en Cero  multipilicador  x1
      
- Cada jugador tendra su turno para apostar 

- La apuesta minima es de 1 ficha ( 5 USD ).

- Despues que cada jugador haga su apuestas
   Se cerrara la mesa y la ruleta dejara de recibir apuestas comenzara a girar    
   
   
   

- El juego acaba cuando todos los jugadores se retiran de la mesa

PD: Si un jugador queda sin saldo a favor, sera asesinado de una forma muy horrible

Disfruten el juego sino ¡Besen mi brillante trasero metalico!
