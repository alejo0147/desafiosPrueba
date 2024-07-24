import java.util.Arrays;
import java.util.Scanner;

public class DesafioTres {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Introduce la cantidad de monedas para crear el array o presiona Enter para usar datos por defecto");
        String input = scanner.nextLine();

        int[] coins;
        if (input.isEmpty()) {
            // DATOS POR DEFECTO POR SI LA ARRAY NO ES ALIMENTADA
            coins = new int[] {1, 2, 4, 9};
            System.out.println("No se ingresaron monedas. Usando datos por defecto:");
        } else {
            int n = Integer.parseInt(input);
            coins = new int[n];

            //  ITERAR Y ALIMENTAR EL ARRAY
            for (int i = 0; i < n; i++) {
                System.out.println("Introduce el valor de la moneda que contendrá el array y da enter");
                try {
                    int numero = scanner.nextInt();
                    //  VALIDAR QUE LOS NÚMEROS INGRESADOS SEAN POSITIVOS
                    if (numero < 0){
                        System.out.println("Los valores de las monedas deben ser positivos");
                        scanner.nextLine();
                        return;
                    }
                    coins[i] = numero;
                } catch (NumberFormatException e){
                    //  SI LOS NÚMEROS NO SON POSITIVOS DAR MENSAJE
                    System.out.println("Entrada no válida. Inténtalo de nuevo.");
                    scanner.nextLine();
                    return;
                }
            }
        }

        // ORDENAR EL ARRAY
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
}

