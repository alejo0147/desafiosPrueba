import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DesafioUno {

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
            List<Integer> numArr = new ArrayList<>();
            if (cantNumArray.isEmpty()) {
                //  NÚMEROS DEL ARRAY POR DEFECTO SI NO SE INGRESAN NÚMEROS PARA ALIMENTAR EL ARRAY
                numArr = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 20, 30, 40, 50, 60, 70, 80, 90, 100);
            } else {
                int num = Integer.parseInt(cantNumArray);
                boolean validInput = true;

                System.out.println("Introduce los números del array uno por uno y da enter");
                for (int i = 0; i < num; i++) {
                    try {
                        int numero = scanner.nextInt();
                        if (numero > 100 || numero == -1) {
                            System.out.println("Todos los números deben ser máximo 100 y mínimo 0. Inténtalo de nuevo.");
                            validInput = false;
                            numArr.clear();
                            scanner.nextLine();
                            break;
                        } else {
                            numArr.add(numero);
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Entrada no válida. Inténtalo de nuevo.");
                        validInput = false;
                        numArr.clear();
                        scanner.nextLine();
                        break;
                    }
                }
                if (!validInput) {
                    System.out.println("Los números ingresados deben ser mayores de -1 y menos a 100, debes intentarlo de nuevo.");
                    return;
                }
            }


            //  LISTA DE ENTEROS QUE SERÁ DEPURADA PARA SACAR LOS NÚMEROS >= S
            List<Integer> listFinal = depurarNums(numArr, S);

            System.out.println("Lista sin el número : " + S + " o mayor a " + S);
            System.out.println(listFinal);

            scanner.close();
        } else {
            System.out.println("Se debe volver a pedir un texto porque no tuvo ningún número");
        }

    }

    //  MÉTODO PARA ENCONTRAR EL PRIMER NÚMERO DE LA CADENA
    public static char buscarNum(String texto) {
        //  ESTE FOR RECORRE EL TEXTO POR CARACTER CON toCharArray
        for (char numChar : texto.toCharArray()) {
            if (numChar >= '1' && numChar <= '9') {
                //  RETORNA EL PRIMER CARACTER NUMÉRICO ENCONTRADO
                return numChar;
            }
        }
        //  SI NO HAY UN NÚMERO EN EL TEXTO
        return '0';
    }

    // MÉTODO PARA DEPURAR LA LISTA DE NÚMEROS SEGÚN EL NÚMERO S
    public static List<Integer> depurarNums(List<Integer> numeros, int S) {
        //  LISTA QUE TENDRA LOS NÚMEROS YA SIN EL DIGITO (S)
        List<Integer> listaProcesada = new ArrayList<>();

        // ITERAR LA LISTA numeros Y ELIMINAR LOS NÚMEROS >= S DE ELLA
        for (int numero : numeros) {
            //  VARIABLE QUE TOMARÁ CADA
            int numFiltrado = filtrarNumero(numero, S);
            //  VALIDA SI CADA NÚMERO QUE LLEGA ES >= 0 Y AGREGARLO A listaProcesada
            if (numFiltrado != -1) listaProcesada.add(numFiltrado);
        }

        // ITERAR listaProcesada PARA INVERTIR LA LISTA
        for (int i = 0, j = listaProcesada.size() - 1; i < j; i++, j--) {
            int temp = listaProcesada.get(i);
            listaProcesada.set(i, listaProcesada.get(j));
            listaProcesada.set(j, temp);
        }

        //  RETORNO DE LA LISTA CON LOS NÚMEROS PROCESADOS Y QUE NO TENDRÁN EL VALOR DE S NI SUPERIORES A S
        return listaProcesada;
    }

    // MÉTODO QUE FILTRAR LOS DÍGITOS SEGÚN S
    public static int filtrarNumero(int numero, int S) {
        //  CONVERTIR LOS NÚMEROS A UNA CADENA DE TEXTO
        String numStr = String.valueOf(numero);

        StringBuilder filteredStr = new StringBuilder();

        //  ITERAR LA CADENA DE TEXTO CARACTE POR CARACTER
        for (char c : numStr.toCharArray()) {
            //  LA VARIABLE digit TOMARÁ EL VALOR DE CADA CARACTER
            int digit = Character.getNumericValue(c);
            //  SE VALIDA SI EL CARACTER ES MENOR DE S Y SI ES ASÍ SE AÑADE A filteredStr
            if (digit < S) {
                filteredStr.append(digit);
            }
        }

        //  SE VALIDA SI LA CANTIDAD DE CARACTERES ES 0
        if (filteredStr.length() == 0) {
            //  SE RETORNA -1 PARA INDICAR QUE SE DEBE COLOCAR EL 0
            return -1;
        }

        //  SE RETORNARA LA LISTA PARSEADA A ENTEROS
        return Integer.parseInt(filteredStr.toString());
    }

}
