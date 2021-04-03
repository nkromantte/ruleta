package Casino;

import  java.io.*;
import  java.lang.String;
import  java.util.Scanner;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

class Game{

    void iniciar() throws IOException{
        int[] j = new int[4];
        boolean muertos = false;
        //vidas de los jugadores al llegar a 0 se considera muerto.

        j[0] = 1;
        j[1] = 1;
        j[2] = 1;
        j[3] = 1;


        //Constructores
        jugador player = new jugador();
        lista_ruleta l = new lista_ruleta();
        lista_apuesta apuesta = new lista_apuesta();
        menu_apuesta  menu = new menu_apuesta();
        //DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        //System.out.println(dtf.format(LocalDateTime.now()));
        //Inicio el metodo añadir jugadores
        player.add();
        // init ruleta, lista de apuestas y turno
        l.set();
        apuesta.set();
        //comienza el juego
        while (!muertos){
            if (j[0]+j[1]+j[2]+j[3] >1){
            //Mostrar menu de apuesta para cada jugador
             for (int i = 1 ;i<=4;i++)  {
                int acu1 =0;
                int acu2 =0;
                int acu3 =0;
                int acu4 =0;
                int acu5 =0;
                int acu6 =0;
                boolean result = false;
                menu.renderOpciones();
                while(!result) {
                    System.out.println("Jugador "+ i );
                    System.out.println("Escoge una opción");
                    int br = menu.keying.nextInt();
                    switch (br) {
                        case 1:
                            if (acu1<1){
                                acu1++;
                                apuesta.add(i,1);
                            }
                            menu.renderOpciones();
                            break;
                        case 2:
                            if (acu2<1){
                                acu2++;
                                apuesta.add(i,2);
                            }

                            menu.renderOpciones();
                            break;
                        case 3:
                            if (acu3<1){
                                acu3++;
                                apuesta.add(i,3);
                            }

                            menu.renderOpciones();
                            break;
                        case 4:
                            if (acu4<1){
                                acu4++;
                                apuesta.add(i,4);
                            }
                            menu.renderOpciones();
                            break;
                        case 5:
                            if (acu5<1){
                                acu5++;
                                apuesta.add(i,5);
                            }

                            menu.renderOpciones();
                            break;
                        case 6:
                            if (acu6<1){
                                acu6++;
                                apuesta.add(i,6);
                            }
                            menu.renderOpciones();
                            break;
                        case 7:
                            result = true;
                            break;
                        case 8:
                            if(i == 1){
                                j[0]=0;
                            }
                            if(i == 2){
                                j[1]=0;
                            }
                            if(i == 3){
                                j[2]=0;
                            }
                            if(i == 4){
                                j[3]=0;
                            }
                            result = true;
                            menu.renderOpciones();
                            break;
                        default: System.out.println("Opción invalida");
                    }
                }
             }

        //muestro la ruleta
        //l.show();
        //Recorre los nodos en sentido horario hasta que se cumpla la condicion de detenerse
         String gana = l.random();
        //Inicio una lista de apuestas para cada jugador

        //Muestra las apuestas de todos los usuarios
        apuesta.show();
        apuesta.log();

        //asigno ganadores
        apuesta.ganadores(gana);
        //

            }else{
                muertos = true;
                System.out.println("El unico sobreviviente es");
                if (j[0] == 1){
                    System.out.println("Jugador uno");
                }
                if (j[1] == 1){
                    System.out.println("Jugador dos");
                }
                if (j[2] == 1){
                    System.out.println("Jugador tres");
                }
                if (j[3] == 1){
                    System.out.println("Jugador cuatro");
                }
            }
        }
    }
}

class menu_apuesta{
    Scanner keying;
    boolean result;
    BufferedReader br;

    menu_apuesta(){

        this.keying = new Scanner(System.in);
        this.br = new BufferedReader(new InputStreamReader(System.in));
        this.result = false;
    }


    void rendertablero() {
        System.out.println("");
        System.out.println("Tablero de apuestas");
        System.out.println("");
        System.out.println("3 6 9 12  |  15 18 21 24  |   27 30 33 36");
        System.out.println("2 5 8 11  |  14 17 20 23  |   26 29 32 35");
        System.out.println("1 4 7 10  |  13 16 19 22  |   25 28 31 34");
        System.out.println("");
    }

    void renderOpciones() {
        this.rendertablero();
        System.out.println("");
        System.out.println("Seleccione tipo de apuesta");
        System.out.println("1) En un solo número");
        System.out.println("2) Varios grupos de números");
        System.out.println("3) los colores rojo o negro");
        System.out.println("4) Si el número es par o impar");
        System.out.println("5) Los numeros altos (19-36)");
        System.out.println("6) Los Numeros bajos  (1-18)");
        System.out.println("7) Terminar apuesta");
        System.out.println("8) Salir de la mesa");
        System.out.println("");
    }
}


class nodo_jugador extends  jugador{
    //Formato de apuesta
    // fichas apostadas|Numero
    // fichas apostadas divididas en |Numeros divididos "/"
    // fichas apostadas|apuesta_color ( Rojo o Negro
    // fichas apostadas|apuesta_impar ( 1 = impar ; 0 = par)
    // fichas apostadas|apuesta_altos 1 o 0 o 1
    // fichas apostadas|apuesta_bajos 0 o 1 o 1

    String apuesta_unica;
    String apuesta_grupal;
    String apuesta_color;
    String apuesta_impar;
    String apuesta_bajos;
    String apuesta_altos;
    nodo_jugador next;

    nodo_jugador(int id, String Nombre, String Apellido, int saldo){
        this.id  = id;
        this.Nombre = Nombre;
        this.Apellido = Apellido;
        this.saldo = saldo;
        //Aprovecho iniciar todas las apuestas en cero
        this.apuesta_unica = "0/0";
        this.apuesta_grupal = "0/0";
        this.apuesta_color = "0/0" ;
        this.apuesta_impar  = "0/0";
        this.apuesta_bajos  = "0/0";
        this.apuesta_altos  = "0/0";

    }

    nodo_jugador(int id, String Nombre, String Apellido, int saldo, nodo_jugador next){
        this.id  = id;
        this.Nombre = Nombre;
        this.Apellido = Apellido;
        this.saldo = saldo;
        //Aprovecho iniciar todas las apuestas en cero
        this.apuesta_unica = "0/0";
        this.apuesta_grupal = "0/0";
        this.apuesta_color = "0/0" ;
        this.apuesta_impar  = "0/0";
        this.apuesta_bajos  = "0/0";
        this.apuesta_altos  = "0/0";

        this.next = next;
    }
}

class lista_apuesta{
    nodo_jugador L, aux , aux2;

    Scanner keying;
    boolean result;
    BufferedReader br;

    boolean isEmpty() {
        if (L == null) {
            return true;
        }else{
            return false;
        }
    }
    /*
       1) En un solo número
       2) Varios grupos de números
       3) los colores rojo o negro
       4) Si el número es par o impar
       5) Los numeros altos (19-36)
       6) Los Numeros bajos  (1-18)
     */
    lista_apuesta(){
        this.keying = new Scanner(System.in);
        this.br = new BufferedReader(new InputStreamReader(System.in));
        this.result = false;
    }

    void set() throws IOException{
        String datos;
        file F = new file();

        for (int i = 1 ; i <=4;i++){
            datos = F.leer("Jugador"+i+".txt");
            String[] A = new String[F.count-1];
            A = datos.split("\n");
            add(A[0]);
        }
    }

    void add( String linea) {
        String[] datos;
        datos = linea.split("/");

        int id =Integer.parseInt(datos[0]);
        String Nombre = datos[1];
        String Apellido = datos[2];
        int saldo = Integer.parseInt(datos[3]);


        if (this.isEmpty()) {
            this.L = new nodo_jugador(id, Nombre, Apellido, saldo );
            this.L.next = this.L;
        } else {
            this.aux = this.L;
            while (this.aux.next != this.L)
                this.aux = this.aux.next;
            this.aux.next = new nodo_jugador(id, Nombre, Apellido, saldo , L);
        }
    }

    void show(){
        aux = L;
        for (int i = 1;i <= 4;i++ ){
            //Datos del jugador
            System.out.println(aux.id);
            System.out.println(aux.Nombre);
            System.out.println(aux.Apellido);
            System.out.println(aux.saldo);
            //DAtos de apuesta
            System.out.println(aux.apuesta_unica);
            System.out.println(aux.apuesta_grupal);
            System.out.println(aux.apuesta_impar);
            System.out.println(aux.apuesta_bajos);
            System.out.println(aux.apuesta_altos);
            aux = aux.next;
        }
    }

    void log() throws IOException{
        String datos ="";
        file log = new file();
        aux = L;
        for (int i = 1;i <= 4;i++ ){
            datos="";
            //Datos del jugador
            datos += "Jugador :" +aux.id;
            datos += "\n";
            datos += "Nombre :"+aux.Nombre;
            datos += "\n";
            datos += "Apellido :"+aux.Apellido;
            datos += "\n";
            datos += "Saldo a favor : "+ aux.saldo*5 +"USD";
            datos += "\n";
            datos += "Datos de la apuesta";
            datos += "\n";
            String[] A;
            A = aux.apuesta_unica.split("/");
            datos += "Cantidad Apostado :" + (Integer.parseInt(A[0]) * 5);
            datos += "\n";
            datos += "Numero Apostado :" + (Integer.parseInt(A[1]) * 5);
            datos += "\n";

            String[]  G;
            G = aux.apuesta_grupal.split("/");
            String[] ApuestaG ;
            ApuestaG = G[0].split("-");
            int acum=0;
            for(int z= 0;z< ApuestaG.length;z++)
                acum+= Integer.parseInt(ApuestaG[z]);


            datos += "Cantidad Apostado :" + acum * 5;
            datos += "\n";
            datos += "Numeros Apostado :" + G[1] ;
            datos += "\n";

            String[] B;
            B = aux.apuesta_impar.split("/");
            datos += "Cantidad Apostado :" + (Integer.parseInt(B[0]) * 5);
            datos += "\n";
            if ((Integer.parseInt(B[1]) ==1))
                datos += "Apuesta a impar : Si";
            if ((Integer.parseInt(B[1]) ==0))
                datos += "Apuesta a impar : No";
            datos += "\n";
            String[] C;
            C = aux.apuesta_color.split("/");
            datos += "Cantidad Apostado :" + (Integer.parseInt(C[0]) * 5);
            datos += "\n";
            if ((Integer.parseInt(C[1]) ==0))
                datos += "Apuesta a color: No";
            if ((Integer.parseInt(C[1]) ==1))
                datos += "Apuesta a : Rojo";
            if ((Integer.parseInt(C[1]) ==2))
                datos += "Apuesta a : Negro";
            datos += "\n";
            String[] D;
            D = aux.apuesta_altos.split("/");
            datos += "Cantidad Apostado :" + (Integer.parseInt(D[0]) * 5);
            datos += "\n";
            if ((Integer.parseInt(D[1]) ==1))
                datos += "Apuesta a Altos : Si";
            if ((Integer.parseInt(D[1]) ==0))
                datos += "Apuesta a Altos : No";
            datos += "\n";

            String[] E;
            E = aux.apuesta_impar.split("/");
            datos += "Cantidad Apostado :" + (Integer.parseInt(E[0]) * 5);
            datos += "\n";
            if ((Integer.parseInt(E[1]) ==1))
                datos += "Apuesta a Bajos : Si";
            if ((Integer.parseInt(E[1]) ==2))
                datos += "Apuesta a Bajos : No";
            datos += "\n";
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
            datos += dtf.format(LocalDateTime.now());
            datos += "\n";
            String codi = "log.out";
            log.crear(codi,datos,true);
            aux = aux.next;
        }

    }


    void win() throws IOException{
        String datos ="";
        file log = new file();
        aux = L;
        for (int i = 1;i <= 4;i++ ){
            datos="";
            //Datos del jugador
            datos += "Jugador :" +aux.id;
            datos += "\n";
            datos += "Nombre :"+aux.Nombre;
            datos += "\n";
            datos += "Apellido :"+aux.Apellido;
            datos += "\n";
            datos += "Saldo a favor : "+ aux.saldo*5 +"USD";
            datos += "\n";
            datos += "Datos de la apuesta";
            datos += "\n";
            String[] A;
            A = aux.apuesta_unica.split("/");
            datos += "Cantidad Apostado :" + (Integer.parseInt(A[0]) * 5);
            datos += "\n";
            datos += "Numero Apostado :" + (Integer.parseInt(A[1]));
            datos += "\n";


            String[] G;
            G = aux.apuesta_grupal.split("/");
            String[] ApuestaG;
            ApuestaG = G[0].split("-");
            int acum=0;
            for(int z= 0;z< ApuestaG.length;z++)
                acum+= Integer.parseInt(ApuestaG[z]);


            datos += "Cantidad Apostado :" + acum * 5;
            datos += "\n";
            datos += "Numeros Apostado :" + G[1] ;
            datos += "\n";





            String[] B;
            B = aux.apuesta_impar.split("/");
            datos += "Cantidad Apostado :" + (Integer.parseInt(B[0]) * 5);
            datos += "\n";
            if ((Integer.parseInt(B[1]) ==1))
                datos += "Apuesta a impar : Si";
            if ((Integer.parseInt(B[1]) ==0))
                datos += "Apuesta a impar : No";
            datos += "\n";
            String[] C;
            C = aux.apuesta_color.split("/");
            datos += "Cantidad Apostado :" + (Integer.parseInt(C[0]) * 5);
            datos += "\n";
            if ((Integer.parseInt(C[1]) ==0))
                datos += "Apuesta a color: No";
            if ((Integer.parseInt(C[1]) ==1))
                datos += "Apuesta a : Rojo";
            if ((Integer.parseInt(C[1]) ==2))
                datos += "Apuesta a : Negro";
            datos += "\n";
            String[] D;
            D = aux.apuesta_altos.split("/");
            datos += "Cantidad Apostado :" + (Integer.parseInt(D[0]) * 5);
            datos += "\n";
            if ((Integer.parseInt(D[1]) ==1))
                datos += "Apuesta a Altos : Si";
            if ((Integer.parseInt(D[1]) ==0))
                datos += "Apuesta a Altos : No";
            datos += "\n";

            String[] E;
            E = aux.apuesta_impar.split("/");
            datos += "Cantidad Apostado :" + (Integer.parseInt(E[0]) * 5);
            datos += "\n";
            if ((Integer.parseInt(E[1]) ==1))
                datos += "Apuesta a Bajos : Si";
            if ((Integer.parseInt(E[1]) ==2))
                datos += "Apuesta a Bajos : No";
            datos += "\n";
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
            datos += dtf.format(LocalDateTime.now());
            datos += "\n";
            String codi = "ganadores.out";
            log.crear(codi,datos,true);
            aux = aux.next;
        }

    }

    void ganadores(String ruleta) throws IOException{
        aux = L;
        String[] R;
        R = ruleta.split("/");
        int numero = Integer.parseInt(R[0]);
        String color = R[1];
        int impar = Integer.parseInt(R[2]);

        for (int i = 1; i<=4 ; i++ ){
            int blanco = 0;
            int va = 0;
            int vbi = 0;
            int vbp= 0;
            int vcr = 0;
            int vcn =0;
            int vd =0;
            int ve =  0;
            int vg = 0;




            String[] G;
            G = aux.apuesta_grupal.split("/");

            String[] ApuestaG;
            ApuestaG = G[0].split("-");
            String[] NumerosG;
            NumerosG = G[1].split("-");
            int Grueso =0;
            Grueso = NumerosG.length;
            int acum = 0;
            Double multiplicador = Math.ceil(36/Grueso);
            for (int x=0;i<Grueso-1;x++){
                if (numero == Integer.parseInt(NumerosG[x])){
                    vg++;
                    for(int j=0;j < Grueso-1;j++)
                        acum +=Integer.parseInt(ApuestaG[j]);
                    aux.saldo += acum * multiplicador ;
                    break;
                }
            }


            String[] A;
            A = aux.apuesta_unica.split("/");
            if (numero == Integer.parseInt(A[1])){
                aux.saldo +=  (Integer.parseInt(A[0])*36);
                va ++;
            }

            String[] B;
            B = aux.apuesta_impar.split("/");
            //impar
            if (impar == 1){
                if(Integer.parseInt(B[1]) == 1){
                    aux.saldo = aux.saldo  + Integer.parseInt(B[0]);
                    vbi ++;
                }
            }
            //para pares
            if (impar == 0){
                if(Integer.parseInt(B[1]) == 2){
                    aux.saldo = aux.saldo + Integer.parseInt(B[0]);
                    vbp ++;
                }
            }
            String[] C;
            C = aux.apuesta_color.split("/");
            if (color == "rojo"){
                if(Integer.parseInt(C[1] )== 1){
                    aux.saldo = aux.saldo + Integer.parseInt(C[0]);
                    vcr++;
                }
            }
            if (color == "negro"){
                if(Integer.parseInt(C[1]) == 2){
                    aux.saldo = aux.saldo + Integer.parseInt(C[0]);
                    vcn++;
                }
            }
            String[] D;
            D = aux.apuesta_altos.split("/");
            if (numero > 18){
                if(Integer.parseInt(D[1]) == 1){
                    aux.saldo = aux.saldo + Integer.parseInt(D[0]);
                    vd++;
                }
            }

            String[] E;
            E = aux.apuesta_bajos.split("/");
            if ((numero > 0) && (numero<=18)){
                if(Integer.parseInt(E[1]) == 1){
                    aux.saldo = aux.saldo + Integer.parseInt(E[0]);
                    ve++;
                }
            }

            if (numero == 0){
                blanco++;
                aux.saldo = Integer.parseInt(A[0])+
                            Integer.parseInt(A[0])+
                            Integer.parseInt(B[0])+
                            Integer.parseInt(C[0])+
                            Integer.parseInt(D[0])+
                            Integer.parseInt(E[0])+
                            acum;
            }


            if ( (va+vbi+vbp+vcr+vcn+vd+ve+blanco+vg) > 1 ){
                this.win();
                System.out.println("Lista de premios");
                System.out.println("Jugador : " + i );
                if(blanco >0){
                    System.out.println("Premio de consuelo recuperas lo apostado");
                }
                if(va>0){
                    System.out.println("Premio unico");
                }
                if(vg>0){
                    System.out.println("Premio grupal");
                }
                if(vbi>0){
                    System.out.println("Premio por imPar");
                }
                if(vbp>0){
                    System.out.println("Premio por Par");
                }
                if(vcr>0){
                    System.out.println("Premio premio por Rojo");
                }
                if(vcn>0){
                    System.out.println("Premio por Negro");
                }if(vd>0){
                    System.out.println("Premio por Altos");
                }
                if(ve>0){
                    System.out.println("Premio por Bajos");
                }
            }else{
                System.out.println("Jugador" +i+ " gracias por tu dinero");
            }
        }
    }

    // id = jugador
    // opc tipo de apuesta
    // apuesta datos de la apuesta
    void add(int id, int opc){
        int apuesta = 0;
        int numero = 0;
        boolean ok = true;
        aux = L;
        while( aux.next != L){
            aux2 = aux;
            aux = aux.next;
            if (aux2.id  == id){
               if(aux2.saldo == 0){
                    break;
                }else{}
                if (opc == 1){
                    //añade apuesta unica
                            System.out.println("Jugador: " + aux2.id);
                            System.out.println("Saldo disponible: " + aux2.saldo);
                    do{
                        try{
                            System.out.println("Ingrese cantidad de fichas apostar");
                            apuesta = this.keying.nextInt();

                            if (apuesta > aux2.saldo){
                                System.out.println("Saldo insuficiente");
                            }else{
                                aux2.saldo = aux2.saldo - apuesta;
                                ok = false;
                            }
                        }catch (Exception e){
                            System.out.println("Numero invalido se esperaba un integer ¬¬");
                            break;
                        }
                    }while(ok);
                    ok = true;
                    do{
                        try{
                            System.out.println("Ingrese el numero del 1 al 36");
                            numero = this.keying.nextInt();

                            if ((numero > 36) || (numero <=0) ){
                                System.out.println("Numero invalido");
                            }else{
                                aux2.apuesta_unica = apuesta  + "/" + numero ;
                                ok = false;
                            }
                        }catch ( Exception e){
                            System.out.println("Numero invalido se esperaba un integer ¬¬");
                            break;
                        }
                    }while(ok);

                }
                if (opc == 2){
                    String apueston ="";
                    String numeron ="";
                    boolean seguir = true;
                    do{
                    //añade apuesta unica
                    System.out.println("Jugador: " + aux2.id);
                    System.out.println("Saldo disponible: " + aux2.saldo);
                    ok = true;
                    do{
                        try{
                            if(aux2.saldo==0){
                                break;
                            }
                            System.out.println("Ingrese cantidad de fichas apostar");
                            apuesta = this.keying.nextInt();

                            if (apuesta > aux2.saldo){
                                System.out.println("Saldo insuficiente");
                            }else{
                                aux2.saldo = aux2.saldo - apuesta;
                                apueston += apuesta + "-";
                                ok = false;
                            }
                        }catch (Exception e){
                            System.out.println("Numero invalido se esperaba un integer ¬¬");
                            break;
                        }
                    }while(ok);
                    ok = true;
                    do{
                        try{
                            if(aux2.saldo==0){
                                break;
                            }
                            System.out.println("Ingrese el numero del 1 al 36");
                            numero = this.keying.nextInt();

                            if ((numero > 36) || (numero <=0) ){
                                System.out.println("Numero invalido");
                            }else{
                                numeron += numero +"-";
                                ok = false;
                            }
                        }catch ( Exception e){
                            System.out.println("Numero invalido se esperaba un integer ¬¬");
                            break;
                        }
                    }while(ok);
                    System.out.println("Terminar grupo  apretar 1");
                    numero = this.keying.nextInt();
                    if (numero== 1){
                        System.out.println("Apuestas completadas");

                        //remuevo las - sobrantes de cada lado xD o sino va petar mas adelante
                        String str = apueston;
                        str = str.substring(0, str.length()-1);
                        String str2 = numeron;
                        str2 = str2.substring(0, str2.length()-1);
                        aux2.apuesta_grupal = str + "/"+ str2;
                        seguir = false;
                    }

                    }while(seguir);
                }

                if (opc == 3){

                        //añade apuesta unica
                        System.out.println("Jugador: " + aux2.id);
                        System.out.println("Saldo disponible: " + aux2.saldo);
                        do{
                            try{
                                System.out.println("Ingrese cantidad de fichas apostar");
                                apuesta = this.keying.nextInt();

                                if (apuesta > aux2.saldo){
                                    System.out.println("Saldo insuficiente");
                                }else{
                                    aux2.saldo = aux2.saldo - apuesta;

                                    ok = false;
                                }
                            }catch (Exception e){
                                System.out.println("Numero invalido se esperaba un integer ¬¬");
                                break;
                            }
                        }while(ok);
                        ok = true;
                        do{
                            try{
                                System.out.println("Apuesta Rojo = 1 al Negro = 2");
                                numero = this.keying.nextInt();

                                if ((numero > 2) || (numero <=0) ){
                                    System.out.println("Color invalido");
                                }else{
                                    aux2.apuesta_color = apuesta  + "/" + numero ;
                                    ok = false;
                                }
                            }catch ( Exception e){
                                System.out.println("Numero invalido se esperaba un integer ¬¬");
                                break;
                            }
                        }while(ok);
                }

                if (opc == 4){
                    //añade apuesta unica
                    System.out.println("Jugador: " + aux2.id);
                    System.out.println("Saldo disponible: " + aux2.saldo);
                    do{
                        try{
                            System.out.println("Ingrese cantidad de fichas apostar");
                            apuesta = this.keying.nextInt();

                            if (apuesta > aux2.saldo){
                                System.out.println("Saldo insuficiente");
                            }else{
                                aux2.saldo = aux2.saldo - apuesta;
                                ok = false;
                            }
                        }catch (Exception e){
                            System.out.println("Numero invalido se esperaba un integer ¬¬");
                            break;
                        }
                    }while(ok);
                    ok = true;
                    do{
                        try{
                            System.out.println("Apuesta Impar = 1 al Par = 2");
                            numero = this.keying.nextInt();

                            if ((numero > 2) || (numero <=0) ){
                                System.out.println("Opcion invalida");
                            }else{
                                aux2.apuesta_impar = apuesta  + "/" + numero ;
                                ok = false;
                            }
                        }catch ( Exception e){
                            System.out.println("Numero invalido se esperaba un integer ¬¬");
                            break;
                        }
                    }while(ok);
                }

                if (opc == 5){
                    //añade apuesta unica
                    System.out.println("Jugador: " + aux2.id);
                    System.out.println("Saldo disponible: " + aux2.saldo);
                    do{
                        try{
                            System.out.println("Ingrese cantidad de fichas apostar");
                            apuesta = this.keying.nextInt();

                            if (apuesta > aux2.saldo){
                                System.out.println("Saldo insuficiente");
                            }else{
                                aux2.saldo = aux2.saldo - apuesta;
                                ok = false;
                            }
                        }catch (Exception e){
                            System.out.println("Numero invalido se esperaba un integer ¬¬");
                            break;
                        }
                    }while(ok);
                    ok = true;
                    do{
                        try{
                            System.out.println("Apuesta High = 1");
                            numero = this.keying.nextInt();

                            if ((numero > 1) || (numero <=0) ){
                                System.out.println("Opcion invalida");
                            }else{
                                aux2.apuesta_altos = apuesta  + "/" + numero ;
                                ok = false;
                            }
                        }catch ( Exception e){
                            System.out.println("Numero invalido se esperaba un integer ¬¬");
                            break;
                        }
                    }while(ok);
                }
                if (opc == 6){
                    //añade apuesta unica
                    System.out.println("Jugador: " + aux2.id);
                    System.out.println("Saldo disponible: " + aux2.saldo);
                    do{
                        try{
                            System.out.println("Ingrese cantidad de fichas apostar");
                            apuesta = this.keying.nextInt();

                            if (apuesta > aux2.saldo){
                                System.out.println("Saldo insuficiente");
                            }else{
                                aux2.saldo = aux2.saldo - apuesta;
                                ok = false;
                            }
                        }catch (Exception e){
                            System.out.println("Numero invalido se esperaba un integer ¬¬");
                            break;
                        }
                    }while(ok);
                    ok = true;
                    do{
                        try{
                            System.out.println("Apuesta Low = 1 ");
                            numero = this.keying.nextInt();

                            if ((numero > 1) || (numero <=0) ){
                                System.out.println("Opcion invalida");
                            }else{
                                aux2.apuesta_bajos = apuesta  + "/" + numero ;
                                ok = false;
                            }
                        }catch ( Exception e){
                            System.out.println("Numero invalido se esperaba un integer ¬¬");
                            break;
                        }
                    }while(ok);
                }
            }
        }
    }
}


class   lista_ruleta{
    nodo_ruleta L, aux , aux2;

    boolean isEmpty() {
        if (L == null) {
            return true;
        }else{
            return false;
        }
    }

    void set() throws IOException{
        String datos;
        file F = new file();
        datos = F.leer("ruleta.txt");
        String[] A = new String[F.count-1];
        A = datos.split("\n");

        for ( int i =0; i < F.count ; i++){
            add(A[i]);
        }
    }

    void add( String linea) {
        String[] datos;
        datos = linea.split("/");

        int numero = Integer.parseInt(datos[0]);
        String color = datos[1];
        int impar = Integer.parseInt(datos[2]);

        if (this.isEmpty()) {
            this.L = new nodo_ruleta(numero, color,impar);
            this.L.next = this.L;
        } else {
            this.aux = this.L;
            while (this.aux.next != this.L)
                this.aux = this.aux.next;
            this.aux.next = new nodo_ruleta(numero, color,impar, L);
        }
    }

    String random(){
        boolean salir = false;
        String salida ="";
        int dado = 0;
        aux = L;
        while( aux.next != null && !salir ){
            dado = (int) Math.floor(Math.random() * 37 + 1);
            aux2 = aux;
            aux= aux.next;
            if (dado == 4) {
                System.out.println("La ruleta se detuvo en:");
                System.out.println("Numero :" +aux2.numero + " Color :" +aux2.color);
                System.out.println("");
                System.out.println("Revisando las apuestas");
                System.out.println("");
                salida += aux2.numero + "/" + aux2.color + "/" + aux2.impar;
                salir = true;
            }
        }
            return salida;
    }

    void show(){
        aux = L;
        while( aux.next != L ){
            aux2 = aux;
            aux= aux.next;
            System.out.println(aux2.numero);
            System.out.println(aux2.color);
            System.out.println(aux2.impar);
        }
    }

        lista_ruleta(){
        };
}

class nodo_ruleta extends ruleta{
    nodo_ruleta next;

    nodo_ruleta(int numero, String color, int impar){
        this.numero = numero;
        this.color = color;
        this.impar = impar;
    }

    nodo_ruleta(int numero, String color, int impar, nodo_ruleta next){
        this.numero = numero;
        this.color = color;
        this.impar = impar;
        this.next = next;
    }
}

class ruleta{
    int numero;
    String color;
    int impar;
}

class jugador{
    int id;
    String Nombre;
    String Apellido;
    int saldo;


    public jugador(){
        this.id = 0;
        this.saldo = 0;
        this.Nombre= "";
        this.Apellido = "";
    }

    void add() throws IOException{

        for (int i = 1; i<=4;i++) {
            Scanner keying = new Scanner(System.in);

            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Jugador " + i );
            this.id = i;
            System.out.println("");
            System.out.print("Escriba su nombre: ");
            this.Nombre = br.readLine();
            System.out.print("Escriba su apellido: ");
            this.Apellido = br.readLine();
            System.out.println("Recuerde cada ficha equivale a 5 USD");
            System.out.print("fichas a colocar: ");
            this.saldo = keying.nextInt();
            System.out.println("Saldo a favor: " + (this.saldo*5) );

            String nombrearchi = "Jugador" + i + ".txt";
            String datos;
            datos = this.id+"/"+this.Nombre+"/"+this.Apellido+"/"+this.saldo;
            this.save(datos,nombrearchi,false);
            System.out.println("");
        }

    }

    void save(String datos, String archivito, boolean b) throws IOException{
        file f =  new file();
        f.crear(archivito, datos,b);
    }

    void show(int i) throws IOException{
        String datos;
        file F = new file();
        if (i == 1){
            datos = F.leer("lectores.in");
        }else{
            datos = F.leer("lectores.out");
        }

    }
}

class file{
    PrintWriter pf;
    FileReader fr;
    int count;

    file(){}

    void crear( String nombreArchivo, String Datos , boolean b) throws IOException{
        pf = new PrintWriter( new FileWriter(nombreArchivo,b));
        pf.println(Datos);
        pf.close();
        System.out.println("Jugador añadidod");
    }

    String leer (String nombreArchivo) throws IOException{
        this.count = 0;
        String datos = "";
        fr = new FileReader(nombreArchivo);
        BufferedReader  br = new BufferedReader(fr);
        String linea;

        while ( (linea= br.readLine())!= null){
            datos += linea + "\n";
            this.count++;
        }
        return  datos;
    }

    String leerlento (String nombreArchivo) throws IOException{
        this.count = 0;
        String datos = "";
        fr = new FileReader(nombreArchivo);
        BufferedReader  br = new BufferedReader(fr);
        String linea;

        while ( (linea= br.readLine())!= null){
            try{
                Thread.sleep(90);
                System.out.println(linea);
            }
            catch(InterruptedException ex){
                Thread.currentThread().interrupt();
            }
        }
        return  "\n";
    }




    String actualizar( String datos){
        return "Termino de leer el carajito";
    }


}




class menu{
    Scanner keying;
    boolean result;
    BufferedReader br;

    menu(){
        this.keying = new Scanner(System.in);
        this.br = new BufferedReader(new InputStreamReader(System.in));
        this.result = false;
    }

     void renderMenu() {
        System.out.println("");
        System.out.println("");
        System.out.println("Bienvenidos al casido Bender");
        System.out.println("   1) Empezar la partida");
        System.out.println("   2) Instrucciones de la ruleta");
        System.out.println("   3) log de apuestas");
        System.out.println("   4) Los Ganadores");
        System.out.println("   5) Salir del Sistema");
        System.out.println("");
    }

     void mostrarMenu() throws IOException{
        file m = new file();
        Game juego = new Game();
        while(!this.result) {
            System.out.println("Escoge una opción");
            int br = this.keying.nextInt();
            switch (br) {
                case 1:
                    juego.iniciar();
                    this.renderMenu();
                    break;
                case 2:
                     System.out.println( m.leer("readme.txt") );
                    this.br.read();
                    this.renderMenu();
                    break;
                case 3:
                    System.out.println( m.leer("log.out") );
                    this.br.read();
                    this.renderMenu();
                    break;
                case 4:
                    System.out.println( m.leer("ganadores.out") );
                    this.br.read();
                    this.renderMenu();
                    break;

                case 5: System.out.println("Hasta luego");
                    this.result = true;
                    break;
                default: System.out.println("Opción invalida");
            }
        }
    }
}



public class Main {

    public static void main(String[] args) throws IOException {
        menu m = new menu();
        file bender = new file();
        bender.leerlento("bender.txt");
        m.renderMenu();
        m.mostrarMenu();


    }
}
