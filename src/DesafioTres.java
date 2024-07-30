import java.util.Arrays;
import java.util.Scanner;

public class DesafioTres {
    public static void main(String[] args) {
        //  PARA SOLICITAR LA ENTRADA POR CONSOLA DE LA CANTIDAD DE NUM DEM ARRAY Y SUS VALORES
        Scanner scanner = new Scanner(System.in);

        System.out.println("Introduce la cantidad de monedas para crear el array o presiona Enter para usar datos por defecto");
        String input = scanner.nextLine();

        //  VARIABLE DE TIPO ARRAY QUE CONTENDRÁ LOS VALORES DE LAS MONEDAS
        int[] coins;

        // VALIDA SI EL DATO INSERTADO POR CONSOLA ES VACÍO
        if (input.isEmpty()) {
            // DATOS POR DEFECTO POR SI LA ARRAY NO ES ALIMENTADA
            coins = new int[]{1, 2, 4, 9};
            System.out.println("No se ingresaron monedas. Usando datos por defecto:");
        } else {
            //  VARIABLE QUE TOMARÁ EL VALOR INGRESADO POR CONSOLA PARA SABER EL TAMAÑO DEL ARRAY
            int n = 0;
            //  VARIABLE CENTINELA QUE SIRVE PARA SALIR DEL WHILE
            boolean validInput = false;

            //  MIENTRAS LA VARIABLE CENTINELA SEA NEGATIVA SE INGRESARÁ AL WHILE
            while (!validInput) {
                try {
                    //  VALIDA SI EL DATO INGRESADO ES NUMÉRICO PARA ASIGNARLO A LA VARIABLE
                    n = Integer.parseInt(input);
                    //
                    validInput = true;
                } catch (NumberFormatException e) {
                    System.out.println("Entrada no válida, introduce un número:");
                    input = scanner.nextLine();
                }
            }

            //  SE ASIGNA EL TAMAÑO DEL ARRAY A LA LIST coins
            coins = new int[n];

            //  ITERAR Y ALIMENTAR EL ARRAY
            for (int i = 0; i < n; i++) {
                //  VARIABLE CENTINELA QUE SIRVE PARA SALIR DEL WHILE
                boolean validCoinInput = false;

                //  MIENTRAS LA VARIABLE CENTINELA SEA NEGATIVA SE INGRESARÁ AL WHILE
                while (!validCoinInput) {
                    System.out.println("Introduce el valor de la moneda que contendrá el array y da enter");
                    try {
                        //  VALIDA SI EL DATO INGRESADO ES NUMÉRICO PARA ASIGNARLO A LA VARIABLE
                        int numero = Integer.parseInt(scanner.nextLine());

                        //  VALIDAR QUE LOS VALORES INGRESADOS POR CONSOLA SEAN POSITIVOS
                        if (numero < 0) {
                            System.out.println("Los valores de las monedas deben ser positivos");
                        } else {
                            //  AL PUESTO DEL ARRAY SE LE DA EL VALOR INGRESADO
                            coins[i] = numero;
                            validCoinInput = true;
                        }
                    } catch (NumberFormatException e) {
                        //  SI LOS NÚMEROS NO SON POSITIVOS DAR MENSAJE
                        System.out.println("Entrada no válida. Inténtalo de nuevo.");
                    }
                }
            }
        }

        // ORDENAR EL ARRAY CON EL MÉTODO PROPIO DE JAVA
        Arrays.sort(coins);

        // MOSTRAR EL ARRAY DE VALORES DE MONEDAS ORGANIZADO
        System.out.println("Monedas utilizadas:");
        for (int coin : coins) {
            System.out.print(coin + " ");
        }
        System.out.println(); // SALTO DE LÍNEA

        //  LLAMADO AL MÉTODO QUE OBTENDRÁ EL VALOR MÍNIMO DE CAMBIO QUE NO SE PUEDE DAR
        int minChange = findMinChange(coins);
        System.out.println("La cantidad mínima de cambio que no se puede dar es: " + minChange);

        //  CIERRE DE SCANNER
        scanner.close();

        // EJECUTAR LAS PRUEBAS UNITARIAS
        ejecutarPruebas();
    }


    //  MÉTODO QUE OBTENDRÁ EL VALOR MÍNIMO DE CAMBIO QUE NO SE PUEDE DAR DE LAS MONEDAS QUE HAY
    public static int findMinChange(int[] coins) {
        // ORDENAR LAS MONEDAS POR SU VALOR
        Arrays.sort(coins);

        //  VARIABLE DE SEGUIMIENTO DEL CAMBIO MÁXIMO CON LAS MONEDAS QUE HAY
        int currentChange = 0;

        //  ITERAR CADA MONEDA
        for (int coin : coins) {
            // SI LA MONEDA ES MAYOR A LA VARIABLE DE SEGUIMIENTO + 1, NO SE PODRÍA DAR EL CAMBIO
            if (coin > currentChange + 1) {
                return  currentChange + 1;
            }
            //  SUMAR EL VALOR DE LA MONEDA AL CAMBIO EXISTENTE
            currentChange += coin;
        }

        //  RETORNA EL CAMBIO MÍNIMO QUE NO SE PUEDE DAR
        return currentChange + 1;
    }

    //  MÉTODO QUE EJECUTA LAS PRUEBAS UNITARIAS
    public static void ejecutarPruebas() {
        System.out.println("-----------------------------------------------------------------------------------------");
        System.out.println("-----------------------------------------------------------------------------------------");
        System.out.println("----------------------------------PRUEBAS UNITARIAS--------------------------------------");
        probarFindMinChange();
    }

    // PROBAR LA ENTREGA DEL CAMBIO MÍNIMO QUE NO SE PUEDE DAR
    public static void probarFindMinChange() {
        System.out.println("Prueba findMinChange:");

        int[] monedas1 = {1, 2, 4, 9};
        int resultado1 = findMinChange(monedas1);
        int esperado1 = 8;
        assert resultado1 == esperado1 : "Fallo en prueba 1";

        int[] monedas2 = {1, 1, 1, 1};
        int resultado2 = findMinChange(monedas2);
        int esperado2 = 5;
        assert resultado2 == esperado2 : "Fallo en prueba 2";

        int[] monedas3 = {1, 2, 5, 10, 20, 50};
        int resultado3 = findMinChange(monedas3);
        int esperado3 = 4;
        assert resultado3 == esperado3 : "Fallo en prueba 3";

        System.out.println("Todas las pruebas de los métodos pasaron.");
    }

}

