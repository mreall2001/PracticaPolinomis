public class ejemplo {
    public static void main(String[] args) {
        float numeroFloat = 3.2f;

        // Utilizando Math.floor para redondear hacia abajo
        int numeroEntero1 = (int) Math.floor(numeroFloat);

        // Utilizando Math.ceil para redondear hacia arriba
        int numeroEntero2 = (int) Math.ceil(numeroFloat);

        System.out.println("Número original: " + numeroFloat);
        System.out.println("Número entero (Math.floor): " + numeroEntero1);
        System.out.println("Número entero (Math.ceil): " + numeroEntero2);
    }
}
