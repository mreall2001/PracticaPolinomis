import javax.print.attribute.HashDocAttributeSet;
import java.util.Arrays;
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
                arrFinal[gradoMaximo - calcularGrado(arrMonomios[i])] = saberNumero(arrMonomios[i]);
            } else if (signo == "-") {
                arrFinal[gradoMaximo - calcularGrado(arrMonomios[i])] = saberNumero(arrMonomios[i]) * -1;
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
        }else {
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
       return null;
    }


    public Polynomial mult(Polynomial p2) {
        return null;
    }

    public Polynomial[] div(Polynomial p2) {
        return null;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Polynomial that = (Polynomial) o;
        return Arrays.equals(coeficientes, that.coeficientes);
    }

    @Override
    public String toString() {
        System.out.println(pintarPolinomio(coeficientes));

        return pintarPolinomio(coeficientes);
    }

    public static String pintarPolinomio(int[] coe){
        int grado = coe.length -1;
        String resultado = "";
        String signo = "";

        for (int i = 0; i < coe.length; i++) {

            if (grado > 0 && coe[i+1] < 0){
                signo = " - ";
            } else {
                signo = " + ";
            }

            if (grado > 0 && coe[i] == 0){
                grado--;
            } else if (grado == 0) {
                resultado += pintarMonomio(grado, coe[i]);
            } else {
                resultado += pintarMonomio(grado, coe[i]) + signo;
                grado--;
            }
        }
        System.out.println(Arrays.toString(coe));

        return resultado;
    }

    private static String pintarMonomio(int grado, int coe) {
        String monomio = "";

        if (grado == 0 && coe < 0){
            coe = coe * -1;
        }

        if (grado == 0){
            monomio += String.valueOf(coe);
        } else if (grado == 1 && coe == 1) {
            monomio += "x";
        } else if (grado == 1 && coe > 1) {
            monomio += coe + "x";
        } else if (grado > 1 && coe == 0) {
            monomio += "x" + "^" + grado;
        } else if (grado > 1) {
            monomio += coe + "x" + "^" + grado;
        }

        return monomio;
    }
}
