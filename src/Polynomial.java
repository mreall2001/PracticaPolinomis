import java.util.Objects;

public class Polynomial {
    private int[] coeficientes;
    public Polynomial(float[] cfs) {
        this.coeficientes = quitarDecimales(cfs);
    }

    private int[] quitarDecimales(float[] cfs) {
        int[] noDecimales = new int[cfs.length];

        for (int i = 0; i < cfs.length; i++) {
            noDecimales[i] = (int) Math.floor(cfs[i]);
        }
        return noDecimales;
    }


    public Polynomial() {
        this.coeficientes = new int[]{0};

    }

    public Polynomial(String s) {
        this.coeficientes = casteoString(s);
    }

    private int[] casteoString(String s) {
        String[] arrMonomios = s.split(" ");
        int gradoMaximo = saberGradoMaximo(arrMonomios);
        int[] arrFinal = new int[gradoMaximo+1];
        String signo = "+";

        for (int i = 0; i < arrMonomios.length; i++) {
            if (Objects.equals(arrMonomios[i], "+")){
                signo = "+";
            } else if (Objects.equals(arrMonomios[i], "-")) {
                signo = "-";
            } else if (signo == "+") {
                arrFinal[gradoMaximo - calcularGrado(arrMonomios[i])] += saberNumero(arrMonomios[i]);
            } else if (signo == "-") {
                arrFinal[gradoMaximo - calcularGrado(arrMonomios[i])] += saberNumero(arrMonomios[i]) * -1;
            }
        }
        return arrFinal;

    }

    private int saberNumero(String arrMonomio) {
        String resultado = "";
        int numFinal;
        for (int i = 0; i < arrMonomio.length(); i++) {
            if (arrMonomio.charAt(i) == 'x'){
                break;
            }else {
                resultado += arrMonomio.charAt(i);
            }
        }

        if (resultado == ""){
            numFinal = 1;
        } else if (resultado.equals("-")) {
            numFinal = -1;
        } else {
            numFinal = Integer.parseInt(resultado);
        }
        return numFinal;
    }

    private int calcularGrado(String arrMonomio) {
        int grado = 0;
        String suma = "";

        for (int i = 0; i < arrMonomio.length(); i++) {
            if (arrMonomio.charAt(i) == '^'){
                for (int j = i+1; j < arrMonomio.length(); j++) {
                    suma += arrMonomio.charAt(j);
                }

                if (grado < Integer.parseInt(suma)){
                    grado = Integer.parseInt(suma);
                    suma = "";
                }else {
                    suma = "";
                }

            } else if (arrMonomio.charAt(i) == 'x') {
                if (grado <= 1){
                    grado++;
                }
            }
        }
        return grado;
    }

    private int saberGradoMaximo(String[] arrMonomios) {
        int grado = 0;
        String suma = "";

        for (int i = 0; i < arrMonomios.length; i++) {
            for (int j = 0; j < arrMonomios[i].length(); j++) {
                if (arrMonomios[i].charAt(j) == '^'){
                    for (int k = j+1; k < arrMonomios[i].length(); k++) {
                        suma += arrMonomios[i].charAt(k);
                    }

                    if (grado < Integer.parseInt(suma)){
                        grado = Integer.parseInt(suma);
                        suma = "";
                    }else {
                        suma = "";
                    }

                } else if (arrMonomios[i].charAt(j) == 'x') {
                    if (grado < 1){
                        grado = 1;
                    }
                }
            }
        }
        return grado;
    }

    public float[] roots() {
        return null;
    }

    public Polynomial add(Polynomial p) {
        int[] operador1 = this.coeficientes;
        int[] operador2 = p.coeficientes;
        int grado = this.coeficientes.length-1;
        int gradoOperador1 = this.coeficientes.length-1;
        int gradoOperador2 = p.coeficientes.length-1;

        if (p.coeficientes.length > grado) grado = p.coeficientes.length;
        float[] resultado = new float[grado+1];

        for (int i = 0; i < operador1.length; i++) {
            resultado[grado - (gradoOperador1-i)] += operador1[i];
        }

        for (int i = 0; i < operador2.length; i++) {
            resultado[grado - (gradoOperador2-i)] += operador2[i];
        }

        return new Polynomial(resultado);
    }


    public Polynomial mult(Polynomial p2) {
        int gradoMaximo = this.coeficientes.length + p2.coeficientes.length - 2;
        float[] resultado = new float[gradoMaximo + 1];
        int[] multiplo1 = this.coeficientes;
        int[] multiplo2 = p2.coeficientes;
        int gradoMultiplo1 = multiplo1.length-1;
        int gradoMultiplo2 = multiplo2.length-1;

        for (int i = 0; i < multiplo1.length; i++) {
            for (int j = 0; j < multiplo2.length; j++) {
                resultado[gradoMaximo - ((gradoMultiplo1-i) + (gradoMultiplo2-j))] += multiplo1[i] * multiplo2[j];
            }
        }

        return new Polynomial(resultado);
    }

    public Polynomial[] div(Polynomial p2) {
        Polynomial[] resultado = new Polynomial[2];

        int[] div1 = this.coeficientes;
        int[] div2 = p2.coeficientes;

        int gradoFinal = div1.length - div2.length;
        float[] coeficiente = new float[gradoFinal+1];

        while (div1.length >= div2.length){
            int saberGradoCoeficiente = (coeficiente.length-1) - (div1.length - div2.length);
            int[] monomioCoeficiente = new int[coeficiente.length];
            coeficiente[saberGradoCoeficiente] = (float) div1[0] / div2[0];
            monomioCoeficiente[saberGradoCoeficiente] = div1[0] / div2[0];
            int[] monoSumarYrestar = multiplicacionPolinomios(div2, new Polynomial(pintarPolinomio(monomioCoeficiente)));
            div1 = sumarYrestar(div1, cambiarSigno(monoSumarYrestar));
            monomioCoeficiente[saberGradoCoeficiente] = 0;
        }

        resultado[0] = new Polynomial(pintarPolinomio(quitarDecimales(coeficiente)));
        resultado[1] = new Polynomial(pintarPolinomio(div1));
        return resultado;
    }

    private int[] cambiarSigno(int[] monoSumarYrestar) {
        for (int i = 0; i < monoSumarYrestar.length; i++) {
            monoSumarYrestar[i] = monoSumarYrestar[i] * -1;
        }

        return monoSumarYrestar;
    }

    private int[] sumarYrestar(int[] div1, int[] sumaRestaOperador) {
        String coeficiente = "";
        for (int i = 0; i < sumaRestaOperador.length; i++) {
            if (div1[i] + sumaRestaOperador[i] != 0 || i != 0){
                if (div1[i] + sumaRestaOperador[i] == 0 && coeficiente.isEmpty()){
                    continue;
                }
                coeficiente += String.valueOf(div1[i] + sumaRestaOperador[i]);
                coeficiente += " ";
            }
        }

        if (coeficiente.isEmpty()) coeficiente = "0";

        String[] resultado = coeficiente.split(" ");
        int[] resultadoFinal = new int[resultado.length];

        for (int i = 0; i < resultadoFinal.length; i++) {
            resultadoFinal[i] = Integer.parseInt(resultado[i]);
        }

        return resultadoFinal;
    }

    private int[] multiplicacionPolinomios(int[] operador, Polynomial operante) {
        int gradoMaximo = operador.length + operante.coeficientes.length - 2;
        int[] resultado = new int[gradoMaximo + 1];
        int gradoMultiplo1 = operador.length-1;
        int gradoMultiplo2 = operante.coeficientes.length-1;

        for (int i = 0; i < operador.length; i++) {
            for (int j = 0; j < operante.coeficientes.length; j++) {
                resultado[gradoMaximo - ((gradoMultiplo1-i) + (gradoMultiplo2-j))] += operador[i] * operante.coeficientes[j];
            }
        }

        return resultado;
    }


    @Override
    public boolean equals(Object o) {
        return this.toString().equals(o.toString());

    }


    @Override
    public String toString() {
        System.out.println(pintarPolinomio(coeficientes));
        return pintarPolinomio(coeficientes);
    }

    public static String pintarPolinomio(int[] coeficientes) {
        StringBuilder polinomioString = new StringBuilder();

        for (int i = 0; i < coeficientes.length; i++) {
            int coeficiente = coeficientes[i];

            if (coeficiente != 0) {
                if (!polinomioString.isEmpty()) {
                    polinomioString.append(coeficiente > 0 ? " + " : " - ");
                } else if (polinomioString.isEmpty() && coeficiente < 0) {
                    polinomioString.append("-");
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

        if (polinomioString.toString().isEmpty()){
            polinomioString = new StringBuilder("0");
        }

        return polinomioString.toString();
    }
}
