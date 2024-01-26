public class ejemplo {
    public static void main(String[] args) {
        int[] coe = {-6, 0, 0, 20, -8};
        String monomio = pintarMonomio(coe);
        System.out.println("Monomio: " + monomio);
    }

    public static String pintarMonomio(int[] coeficientes) {
        StringBuilder polinomioString = new StringBuilder();

        for (int i = 0; i < coeficientes.length; i++) {
            int coeficiente = coeficientes[i];

            if (coeficiente != 0) {
                if (!polinomioString.isEmpty()) {
                    polinomioString.append(coeficiente > 0 ? " + " : " - ");
                }

                if (Math.abs(coeficiente) != 1 || i == coeficientes.length - 1) {
                    polinomioString.append(Math.abs(coeficiente));
                }

                if (i < coeficientes.length - 1) {
                    polinomioString.append("x");

                    if (i < coeficientes.length - 2) {
                        polinomioString.append("^").append(coeficientes.length - 1 - i);
                    }
                }
            }
        }

        if (coeficientes[0] < 0){
            polinomioString = new StringBuilder("-" + polinomioString);
        }

        return polinomioString.toString();
    }

}
