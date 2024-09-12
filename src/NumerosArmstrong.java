import java.util.ArrayList;
import java.util.List;

public class NumerosArmstrong {

    public static void main(String[] args) {
        //  Lista que tendrá como valores lo que nos traiga el método obtenerNumeros
        List<Integer> numeros = obtenerNumeros();
        //  Listar los números resultantes de la lista
        System.out.println("Números de Armstrong de 3 cifras: " + numeros);
    }

    public static List<Integer> obtenerNumeros() {
        //  Declarar la lista de números
        List<Integer> numeros = new ArrayList<>();

        // Recorremos todos los números de 3 cifras que van de 100 a 999
        for (int i = 100; i <= 999; i++) {
            //  Llamar el método sumarCubosDeDigitos que es sumar cada número al cubo
            int sumaCubos = sumarCubosDeDigitos(i);

            // Si la suma de los cubos de los dígitos es igual al número, lo agregamos a la lista
            if (sumaCubos == i) {
                numeros.add(i);
            }
        }

        return numeros;
    }

    // Método que toma un número y retorna la suma de los cubos de sus dígitos
    public static int sumarCubosDeDigitos(int numero) {
        //  Var que sirve para ir sumando el resultado del cubo
        int suma = 0;
        //  Var que tendrá el valor enviado desde el for
        int temp = numero;

        //  Iteramos sobre cada dígito
        while (temp > 0) {
            //  Obtenemos el último dígito
            int digito = temp % 10;
            //  Sumamos el cubo del dígito
            suma += Math.pow(digito, 3);
            //  Eliminamos el último dígito
            temp /= 10;
        }
        //  retorna el valor de la suma de los cubos al for
        return suma;
    }
}
