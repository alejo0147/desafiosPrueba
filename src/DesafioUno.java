import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DesafioUno {

    //  SE CREA MAIN QUE LLAMA EL MÉTODO PARA CORRER LA PRUEBA Y LA PRUEBA UNITARIA
    public static void main(String[] args) {
        //  LLAMADO AL MÉTODO PARA HACER LA PRUEBA
        DesafioUno_1();
        //  LLAMADO AL MÉTODO QUE REALIZA LAS PRUEBAS UNITARIAS
        ejecutarPruebas();

    }

    //  MÉTODO QUE EJECUTA LA PRUEBA UNO SOLICITADA
    public static void DesafioUno_1() {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Ingresa la cadena de texto generada en md5 para determinar el valor de S: ");
        //  VARIABLE QUE TENDRÁ EL VALOR DEL TEXTO INGRESADO
        String textoAlfaNum = scanner.nextLine();
        //  VARIABLE TIPO CHAR QUE TOMARÁ EL VALOR DEL PRIMER CARACTER NUMÉRICO ENCONTRADO
        char primerNum = buscarNum(textoAlfaNum);
        //  LA VARIABLE S TOMARÁ EL VALOR DEL PRIMER CARACTER NUMÉRICO QUE SE HAYA ENCONTRADO
        int S = (Character.getNumericValue(primerNum));
        //  VALIDAMOS SI EL VALOR NUMÉRICO ES DIFERENTE A CERO
        if (S != 0) {
            System.out.println("El primer número de 1-9 encontrado en el texto es: " + S);

            System.out.println("Introduce la cantidad de números que contendrá el array o presiona Enter para usar datos por defecto:");
            String cantNumArray = scanner.nextLine();

            //  LISTA QUE CONTENDRÁ LOS NÚMEROS DEL ARRAY
            List<Integer> numArr = new ArrayList<>();
            if (cantNumArray.isEmpty()) {
                //  NÚMEROS POR DEFECTO SI NO SE ALIMENTA EL ARRAY
                numArr = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 20, 30, 40, 50, 60, 70, 80, 90, 100);
            } else {
                int num = 0;
                boolean validInput = false;

                //  VALIDAR QUE SEA UN NÚMERO
                while (!validInput) {
                    try {
                        num = Integer.parseInt(cantNumArray);
                        validInput = true;
                    } catch (NumberFormatException e) {
                        System.out.println("Entrada no válida, introduce un número:");
                        cantNumArray = scanner.nextLine();
                    }
                }

                for (int i = 0; i < num; i++) {
                    System.out.println("Introduce los números del array uno por uno y da enter");
                    if (scanner.hasNextInt()) {
                        int numero = scanner.nextInt();
                        if (numero > 100 || numero <= -1) {
                            System.out.println("Todos los números deben ser máximo 100 y mínimo 0. Inténtalo de nuevo.");
                            validInput = false;
                            numArr.clear();
                            scanner.nextLine();
                            break;
                        } else {
                            numArr.add(numero);
                        }
                    } else {
                        System.out.println("Entrada no válida. Inténtalo de nuevo.");
                        validInput = false;
                        numArr.clear();
                        scanner.nextLine();
                        break;
                    }
                }
                if (!validInput) {
                    System.out.println("Deben ser números y deben ser mayores de 0 y menores a 100, debes intentarlo de nuevo.");
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
        //  ESTE FOR ITERA EL TEXTO CARACTER POR CARACTER CON toCharArray
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

    // MÉTODO QUE FILTRA LOS DÍGITOS SEGÚN S
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

    //  MÉTODO DE PRUEBA UNITARIA QUE LLAMA CADA PRUEBA
    public static void ejecutarPruebas() {
        System.out.println("-----------------------------------------------------------------------------------------");
        System.out.println("-----------------------------------------------------------------------------------------");
        System.out.println("----------------------------------PRUEBAS UNITARIAS--------------------------------------");
        probarBuscarNum();
        probarDepurarNums();
        probarFiltrarNumero();
    }

    //  PROBAR LA BUSQUEDA DEL NÚMERO DEL TEXTO
    public static void probarBuscarNum() {
        System.out.println("Prueba buscarNum:");

        String texto1 = "abc123";
        char resultado1 = buscarNum(texto1);
        char esperado1 = '1';
        assert resultado1 == esperado1 : "Fallo en prueba 1";

        String texto2 = "abcdef";
        char resultado2 = buscarNum(texto2);
        char esperado2 = '0';
        assert resultado2 == esperado2 : "Fallo en prueba 2";

        System.out.println("Todas las pruebas de buscarNum pasaron.");
    }

    //  PRUEBA AL MÉTODO QUE QUE TOMA S Y QUITA LOS DIGITOS IGUALES A O MAYORES EN S
    public static void probarDepurarNums() {
        System.out.println("Prueba depurarNums:");

        List<Integer> numeros = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 20, 30, 40, 50, 60, 70, 80, 90, 100);
        int S = 5;
        List<Integer> resultado = depurarNums(numeros, S);
        List<Integer> esperado = List.of(4, 3, 2, 1, 0, 4, 3, 2, 1, 0, 4, 3, 2, 1, 0);
        assert resultado.equals(esperado) : "Fallo en prueba depurarNums";

        System.out.println("Todas las pruebas de depurarNums pasaron.");
    }

    //  PRUEBA PARA AGREGAR A LA LISTA LOS DIGITOS QUE SEAN MENORES A S
    public static void probarFiltrarNumero() {
        System.out.println("Prueba filtrarNumero:");

        int numero = 12345;
        int S = 3;
        int resultado = filtrarNumero(numero, S);
        int esperado = 12;
        assert resultado == esperado : "Fallo en prueba filtrarNumero";

        System.out.println("Todas las pruebas de filtrarNumero pasaron.");
    }

}
