import javax.print.attribute.HashDocAttributeSet;
import java.util.Arrays;
import java.util.Objects;

public class Polynomial {
    private int[] coeficientes;
    private String stringCoeficientes;
    public Polynomial(float[] cfs) {
        this.coeficientes = quitarDecimales(cfs);
        this.stringCoeficientes = pintarPolinomio(quitarDecimales(cfs));

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
        this.stringCoeficientes = s;
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
        String[] arr1 = this.stringCoeficientes.split(" ");
        String[] arr2 = p.stringCoeficientes.split(" ");
        int grado = this.coeficientes.length;
        String signo = "+";

        if (p.coeficientes.length > grado) grado = p.coeficientes.length;
        float[] suma = new float[grado];

        for (int i = 0; i < arr1.length; i++) {
            if (Objects.equals(arr1[i], "+")){
                signo = "+";
            } else if (Objects.equals(arr1[i], "-")) {
                signo = "-";
            } else if (signo == "+") {
                suma[(grado-1) - calcularGrado(arr1[i])] += saberNumero(arr1[i]);
            } else if (signo == "-") {
                suma[(grado-1) - calcularGrado(arr1[i])] += saberNumero(arr1[i]) * -1;
            }
        }

        signo = "+";

        for (int i = 0; i < arr2.length; i++) {
            if (Objects.equals(arr2[i], "+")) {
                signo = "+";
            } else if (Objects.equals(arr2[i], "-")) {
                signo = "-";
            } else if (signo == "+") {
                suma[(grado - 1) - calcularGrado(arr2[i])] += saberNumero(arr2[i]);
            } else if (signo == "-") {
                suma[(grado - 1) - calcularGrado(arr2[i])] += saberNumero(arr2[i]) * -1;
            }
        }


        return new Polynomial(suma);
    }


    public Polynomial mult(Polynomial p2) {
        return null;
    }

    public Polynomial[] div(Polynomial p2) {
        return null;
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

        if (polinomioString.toString().isEmpty()){
            polinomioString = new StringBuilder("0");
        }

        return polinomioString.toString();
    }
}
