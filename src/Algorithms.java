import java.util.Random;

/**
 * @author weiweng
 * @date : 2024/9/25 9:10
 */
public class Algorithms {


  static void aleatorio(int v[], int tam) {
    int i;
    for (i = 0; i < tam; i++) {
      v[i] = i;
    }
    for (i = tam - 1; i > 0; i--) {
// random.nextInt(k) devuelve un entero pseudoaleatorio distribuido
// uniformemente entre 0 (incluido) y el valor especificado k (excluido)
      int j = new Random().nextInt(i + 1);
      int temp = v[i];
      v[i] = v[j];
      v[j] = temp;
    }
  }


  public static void ordena1(int v[], int tam, TraceData data) {
    int i, j, temp;
    i = 1;
    j = 2;
    while (i < tam) {
      data.incrementarComps();
      if (v[i - 1] <= v[i]) {
        i = j;
        j = j + 1;
      } else {
        temp = v[i - 1];
        v[i - 1] = v[i];
        v[i] = temp;
        i = i - 1;
        if (i == 0) {
          i = 1;
        }
        data.incrementarAsigs(3);
      }
    }
  }


  public static void ordena2(int[] v, int tam, TraceData data) {
    int k;
    int n = tam;
    for (k = n / 2; k >= 1; k--) {
      f(v, k, n, data);
    }
    k = n;
    while (k > 1) {
      g(v, 1, k--, data);
      f(v, 1, k, data);
    }
  }

  private static void f(int[] v, int k, int n, TraceData data) {
    while (2 * k <= n) {
      int j = 2 * k;
      if (j < n) {
        data.incrementarComps();
        if (v[j - 1] < v[j]) {
          j++;
        }
      }
      data.incrementarComps();
      if (v[k - 1] >= v[j - 1]) {
        break;
      }
      g(v, k, j, data);
      k = j;
    }
  }

  private static void g(int[] v, int i, int j, TraceData data) {
    int temp = v[i - 1];
    v[i - 1] = v[j - 1];
    v[j - 1] = temp;
    data.incrementarAsigs(3);
  }

  public static void ordena3(int[] v, int tam, TraceData data) {
    int m = h(v, tam, data);
    int[] c = new int[m + 1];
    int[] w = new int[tam];
    for (int i = 0; i < tam; i++) {
      c[v[i]] = c[v[i]] + 1;
      data.incrementarAsigs(1);
    }
    for (int i = 1; i < m + 1; i++) {
      c[i] = c[i] + c[i - 1];
    }
    for (int i = tam - 1; i >= 0; i--) {
      w[c[v[i]] - 1] = v[i];
      c[v[i]] = c[v[i]] - 1;
      data.incrementarAsigs(2);
    }
    for (int i = 0; i < tam; i++) {
      v[i] = w[i];
      data.incrementarAsigs();
    }
  }

  private static int h(int[] v, int tam, TraceData data) {
    int i;
    int m = v[0];
    for (i = 1; i < tam; i++) {
      data.incrementarComps();
      if (v[i] > m) {
        m = v[i];
        data.incrementarAsigs();
      }
    }
    return m;
  }

}
