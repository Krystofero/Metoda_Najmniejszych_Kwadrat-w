package Metoda_Najmniejszych_Kwadratów;

import java.util.Scanner;

public class Metoda_Najmniejszych_Kwadratów {

    public static double[] macierz_gauss(double[][] A, double[] b) {
        int N  = b.length;

        for (int p = 0; p < N; p++) {

            // find pivot row and swap
            int max = p;
            for (int i = p + 1; i < N; i++) {
                if (Math.abs(A[i][p]) > Math.abs(A[max][p])) {
                    max = i;
                }
            }
            double[] temp = A[p]; A[p] = A[max]; A[max] = temp;
            double   t    = b[p]; b[p] = b[max]; b[max] = t;

            // pivot within A and b
            for (int i = p + 1; i < N; i++) {
                double alpha = A[i][p] / A[p][p];
                b[i] -= alpha * b[p];
                for (int j = p; j < N; j++) {
                    A[i][j] -= alpha * A[p][j];
                }
            }
        }
        // back substitution
        double[] x = new double[N];
        for (int i = N - 1; i >= 0; i--) {
            double sum = 0.0;
            for (int j = i + 1; j < N; j++) {
                sum += A[i][j] * x[j];
            }
            x[i] = (b[i] - sum) / A[i][i];
        }
        return x;
    }

    public static double funkcja(double x) {
         return (double) (Math.pow(x,3) + 2);
    //   return (double) (2*Math.pow(x,2) + x + 1);
    }
    public static void main(String[] args) {
        int n; //ilość węzłów
        Scanner sc = new Scanner(System.in);
//        System.out.println("Podaj ilosc wezlow aproksymacji: ");
//        n = Integer.parseInt(sc.nextLine());
//
//        double[] x = new double[n];
//        double[] y = new double[n];
//
//        for (int i = 0; i < n; i++) {
//            System.out.println("Podaj x" + i + ": ");
//            x[i] = Double.parseDouble(sc.nextLine());
//            System.out.println("Podaj y" + i + ": ");
//            y[i] = Double.parseDouble(sc.nextLine());
//        }

        n = 5;
        double[] x = {-1,-0.5,0,0.5,1};
        double[] y = new double[x.length];;
        for(int i = 0; i < x.length; i++) y[i] = funkcja(x[i]);


        double argx; //argument x dla którego wyznaczam rozwiązanie
        int m; //stopień wielomianu
        System.out.println("Podaj m wielomianu: ");
        m = Integer.parseInt(sc.nextLine());
        System.out.println("Podaj x do policzenia: ");
        argx = Double.parseDouble(sc.nextLine()); // x, w którym poszukujemy rozwiązania

        double[] S = new double[2*m+1];
        double[] T = new double[m+1];
        double[] a;
        double wynik=0;

        for (int i = 0; i <= 2*m; i++) {
            for (int j = 0; j <= n-1; j++) {
                S[i]+=Math.pow(x[j],i);
            }
            System.out.println("S"+i+": "+S[i]);
        }

        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n-1; j++) {
                T[i]+=y[j]*Math.pow(x[j],i);
            }
            System.out.println("T"+i+":"+T[i]);
        }

        double [][] gss=new double[m+1][m+1];

        for (int i = 0; i <=m ; i++) {
            for (int j = 0; j <=m; j++) {
                gss[i][j]=S[j+i];
            }
        }

        a=macierz_gauss(gss,T); //Eliminacją Gaussa wyliczam współczynniki a
        for (int i = 0; i <= m; i++) {
            wynik+=a[i]*Math.pow(argx, i);
        }
        System.out.println("Wynik to:"+wynik);
    }
}
