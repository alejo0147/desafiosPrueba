import java.util.Arrays;
import java.util.Scanner;

public class DesafioTres {
    public static void main(String[] args) {
        //  PARA SOLICITAR LA ENTRADA AL USUARIO DEL TEXTO Y DEMÁS DATOS
        Scanner scanner = new Scanner(System.in);

        System.out.println("Introduce la cantidad de monedas para crear el array o presiona Enter para usar datos por defecto");
        String input = scanner.nextLine();

        //  VARIABLE QUE ES DE TIPO ARRAY
        int[] coins;

        // VALIDA SI EL DATO INSERTADO ES VACÍO
        if (input.isEmpty()) {
            // DATOS POR DEFECTO POR SI LA ARRAY NO ES ALIMENTADA
            coins = new int[] {1, 2, 4, 9};
            System.out.println("No se ingresaron monedas. Usando datos por defecto:");
        } else {
            int n = 0;
            boolean validInput = false;

            while (!validInput) {
                try {
                    n = Integer.parseInt(input);
                    validInput = true;
                } catch (NumberFormatException e) {
                    System.out.println("Entrada no válida, introduce un número:");
                    input = scanner.nextLine();
                }
            }

            coins = new int[n];

            //  ITERAR Y ALIMENTAR EL ARRAY
            for (int i = 0; i < n; i++) {
                boolean validCoinInput = false;

                while (!validCoinInput) {
                    System.out.println("Introduce el valor de la moneda que contendrá el array y da enter");
                    try {
                        int numero = Integer.parseInt(scanner.nextLine());

                        //  VALIDAR QUE LOS NÚMEROS INGRESADOS SEAN POSITIVOS
                        if (numero < 0) {
                            System.out.println("Los valores de las monedas deben ser positivos");
                        } else {
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

        // MOSTRAR EL ARRAY DE MONEDAS
        System.out.println("Monedas utilizadas:");
        for (int coin : coins) {
            System.out.print(coin + " ");
        }
        System.out.println(); // Para un salto de línea

        //  LLAMADO AL MÉTODO QUE OBTENDRÁ EL VALOR MÍNIMO DE CAMBIO QUE NO SE PUEDE DAR
        int minChange = findMinChange(coins);
        System.out.println("La cantidad mínima de cambio que no se puede dar es: " + minChange);

        scanner.close();

        // EJECUTAR LAS PRUEBAS
        ejecutarPruebas();
    }



    //  MÉTODO QUE OBTENDRÁ EL VALOR MÍNIMO DE CAMBIO QUE NO SE PUEDE DAR DE LAS MONEDAS QUE HAY
    public static int findMinChange(int[] coins) {
        // ORDENAR LAS MONEDAS
        Arrays.sort(coins);

        //  VARIABLE DE SEGUIMIENTO DEL CAMBIO MÁXIMO CON LAS MONEDAS QUE HAY
        int currentChange = 0;

        //  ITERAR CADA MONEDA
        for (int coin : coins) {
            // SI LA MONEDA ES MAYOR AL CAMBIO, NO SE PODRÍA DAR EL CAMBIO
            if (coin > currentChange + 1) {
                break;
            }
            //  SUMAR EL VALOR DE LA MONEDA AL CAMBIO EXISTENTE
            currentChange += coin;
        }

        //  RETORNA EL CAMBIO MÍNIMO QUE NO SE PUEDE DAR
        return currentChange + 1;
    }

    //  MÉTODO DE PRUEBA
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

