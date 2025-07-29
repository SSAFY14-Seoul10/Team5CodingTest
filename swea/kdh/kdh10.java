package kdh;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class kdh10 {

  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

  static int t, n, ans;
  static boolean[] col = new boolean[11];
  static boolean[] diag = new boolean[21];
  static boolean[] diag1 = new boolean[21];

  public static void dfs(int r)
  {
    if (r == n) {
//      System.out.println("r == n");
      ans++;
      return;
    }

    for (int c = 0; c < n; c++)
    {
      if (col[c]) continue;
      if (diag[n - (r - c)]) continue;
      if (diag1[r + c]) continue;

//      System.out.println(r + " , " + c + " : " + "방문");
      col[c] = true;
      diag[n - (r - c)] = true;
      diag1[r + c] = true;
      dfs(r + 1);
      col[c] = false;
      diag[n - (r - c)] = false;
      diag1[r + c] = false;
    }
  }

  public static void main(String[] args) throws IOException {
    t = Integer.parseInt(br.readLine());

    for (int tc = 1; tc <= t; tc++)
    {
      ans = 0;
      col = new boolean[11];
      diag = new boolean[21];
      diag1 = new boolean[21];
      n = Integer.parseInt(br.readLine());

      dfs(0);

      bw.write("#" + tc + " " + ans + '\n');
      bw.flush();
    }
  }
}
