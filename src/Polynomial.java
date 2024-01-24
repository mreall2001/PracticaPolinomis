import java.util.Arrays;

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
