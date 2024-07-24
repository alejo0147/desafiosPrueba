import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DesafioDos {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Ingresa la cadena de texto generada en md5 y así determinar el valor de S: ");
        //  VARIABLE QUE TENDRÁ EL VALOR DEL TEXTO INGRESADO
        String textoAlfaNum = scanner.nextLine();
        //  VARIABLE TIPO CHAR QUE TOMAR´PA EL VALOR DEL PRIMER CARACTER NUMÉRICO ENCONTRADO
        char primerNum = buscarNum(textoAlfaNum);
        //  SI EL CARACTER FUE NUMÉRICO SE CONVERTIRÁ A NÚMERO Y SE ASIGNARÁ A LA VARIABLE S
        int S = (Character.getNumericValue(primerNum));
        //  VALIDAMOS SI HUBO CARACTER NUMÉRICO Y SI NO ES ASÍ SOLICITAR QUE SE VUELVA A SOLICITAR OTRO Y EJECUTAR DE NUEVO
        if (S != 0) {
            System.out.println("El primer número de 1-9 encontrado en el texto es: " + S);

            System.out.println("Introduce la cantidad de números que contendrá el array o presiona Enter para usar datos por defecto:");
            String cantNumArray = scanner.nextLine();

            //  LISTA QUE CONTENDRÁ LOS NÚMEROS DEL ARRAY
            List<Integer> numArr;
            if (cantNumArray.isEmpty()) {
                //  NÚMEROS DEL ARRAY POR DEFECTO SI NO SE INGRESAN NÚMEROS PARA ALIMENTAR EL ARRAY
                numArr = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 20, 30, 40, 50, 60, 70, 80, 90, 100);
            } else {
                numArr = new ArrayList<>();
                int num = Integer.parseInt(cantNumArray);
                for (int i = 0; i < num; i++) {
                    System.out.println("Introduce los números del array uno por uno y da enter");
                    int numAr = scanner.nextInt();
                    numArr.add(numAr);
                }
            }

            numArr = manualSort(numArr);
            System.out.println("Los números a elevar en orden ascendente " + numArr);


            //  LISTA DE ENTEROS QUE SERÁ DEPURADA PARA SACAR LOS NÚMEROS >= S
            List<Integer> listFinal = numsProcesados(numArr, S);

            System.out.println("Resultado:");
            System.out.println(listFinal);

            scanner.close();

        } else {
            System.out.println("Se debe volver a pedir un texto porque no tuvo ningún número");
        }

    }

    //  MÉTODO PARA ENCONTRAR EL PRIMER NÚMERO DE LA CADENA
    public static char buscarNum(String texto) {
        //  ITERAR EL TEXTO CARACTER POR CARACTER CON toCharArray
        for (char numChar : texto.toCharArray()) {
            if (numChar >= '1' && numChar <= '9') {
                //  RETORNA EL PRIMER CARACTER NUMÉRICO ENCONTRADO
                return numChar;
            }
        }
        //  SI NO HAY UN NÚMERO EN EL TEXTO
        return '0';
    }

    public static List<Integer> numsProcesados(List<Integer> numeros, int S) {
        List<Integer> numElevados = new ArrayList<>();
        //  VARIABLE QUE VALDRÁ LO MISMO QUE S PARA LUEGO CONCATENARLA
        int newS = S;

        //  PASAR S A STRING
        String numStr = Integer.toString(S);
        //  PASAR EL NUEVO NÚMERO QUE VALE IGUA QUE S A STRING
        String numNewStr = Integer.toString(newS);
        // CONCATENAR LOS DOS NÚMEROS QUE ESTÁN EN STRING
        String concatenarNumsStr = numStr + numNewStr;

        //  PASAR LOS NÚMEROS CONCATENADOS A NUMÉRICO A ESTA VARIABLE
        int maxRange = Integer.parseInt(concatenarNumsStr);

        System.out.println("Los números elevados mayores o iguales a: " + maxRange + " no serán mostrados");

        // ITERAR LOS NÚMEROS Y ELEVARLOS AL CUADRADO
        for (int numero : numeros) {
            int elevado = numero * numero;
            //  SI EL NÚMERO ELEVADO ES MENOR A LA VARIABLE QUE TIENE CONCATENADOS LOS NÚMEROS, AGREGARLO
            if (elevado <= maxRange) {
                numElevados.add(elevado);
            }
        }

        //  LLAMADO A LA FUNCIÓN QUE LOS ORDENARÁ
        numElevados = manualSort(numElevados);

        return numElevados;
    }

    //  ORDENAR EL ARRAY DE NÚMEROS ELEVADOS SIN USAR LA FUNCIÓN DE ORDENACIÓN DE JAVA
    public static List<Integer> manualSort(List<Integer> listNums) {
        // ORDENAR LOS VALORES DE MENOR A MAYOR
        int num = listNums.size();
        for (int i = 0; i < num - 1; i++) {
            for (int j = 0; j < num - i - 1; j++) {
                if (listNums.get(j) > listNums.get(j + 1)) {
                    // INTERCAMBIAR list[j] y list[j+1]
                    int temp = listNums.get(j);
                    listNums.set(j, listNums.get(j + 1));
                    listNums.set(j + 1, temp);
                }
            }
        }
        return listNums;
    }
}

