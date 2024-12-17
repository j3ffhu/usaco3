import java.util.*;

public class Q2 {
    static boolean[][][] cheese;
    static boolean[][] xySlice, yzSlice, zxSlice;
    static int N;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        int Q = sc.nextInt();
        
        cheese = new boolean[N][N][N];
        xySlice = new boolean[N][N];
        yzSlice = new boolean[N][N];
        zxSlice = new boolean[N][N];

        for (int i = 0; i < Q; i++) {
            int x = sc.nextInt();
            int y = sc.nextInt();
            int z = sc.nextInt();
            
            carve(x, y, z);
            System.out.println(countConfigurations());
        }
        sc.close();
    }

    static void carve(int x, int y, int z) {
        cheese[x][y][z] = true;

        xySlice[x][y] = true;
        for (int k = 0; k < N; k++) {
            if (!cheese[x][y][k]) {
                xySlice[x][y] = false;
                break;
            }
        }

        yzSlice[y][z] = true;
        for (int k = 0; k < N; k++) {
            if (!cheese[k][y][z]) {
                yzSlice[y][z] = false;
                break;
            }
        }

        zxSlice[z][x] = true;
        for (int k = 0; k < N; k++) {
            if (!cheese[x][k][z]) {
                zxSlice[z][x] = false;
                break;
            }
        }
    }

    static int countConfigurations() {
        int count = 0;

        for (int x = 0; x < N; x++) {
            for (int y = 0; y < N; y++) {
                if (xySlice[x][y]) count++;  // Z-axis placement
            }
        }

        for (int y = 0; y < N; y++) {
            for (int z = 0; z < N; z++) {
                if (yzSlice[y][z]) count++;  // X-axis placement
            }
        }

        for (int z = 0; z < N; z++) {
            for (int x = 0; x < N; x++) {
                if (zxSlice[z][x]) count++;  // Y-axis placement
            }
        }

        return count;
    }
}
