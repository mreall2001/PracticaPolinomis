public class ejemplo {
    public static void main(String[] args) {
        int[] coe = {3, 5, -7, -6, 0, -6};
        String monomio = paintMonomio(coe);
        System.out.println("Monomio: " + monomio);
    }

    public static String paintMonomio(int[] coeficientes) {
        StringBuilder resultado = new StringBuilder();

        for (int i = 0; i < coeficientes.length; i++) {
            int coeficiente = coeficientes[i];

            if (coeficiente != 0) {
                if (i == 0){
                    resultado.append(Math.abs(coeficiente));
                    if (coeficientes.length - 1 - i > 0) {
                        resultado.append("x");

                        if (coeficientes.length - 1 - i > 1) {
                            resultado.append("^").append(coeficientes.length - 1 - i);
                        }
                    }
                }else {
                    if (Math.abs(coeficiente) != 1 || (coeficientes.length - 1 - i) == 0) {
                        resultado.append((coeficiente > 0) ? " + " : " - ");
                        resultado.append(Math.abs(coeficiente));
                    }

                    if (coeficientes.length - 1 - i > 0) {
                        resultado.append("x");

                        if (coeficientes.length - 1 - i > 1) {
                            resultado.append("^").append(coeficientes.length - 1 - i);
                        }
                    }
                }

            }
        }

        return resultado.toString();
    }

}
