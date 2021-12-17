public class Main {
    public static void main(String[] args) {

        int xmin = 253;// 20;//
        int xmax = 280;// 30;//
        int ymin = -73;// -10;//
        int ymax = -46;// -5;//

        int posyMax = 0;
        int count = 0;
        for (int i = 0; i <= xmax; i++) {
            for (int j = -ymin; j >= ymin; j--) {

                int posx = 0;
                int posy = 0;

                int vx = i;
                int vy = j;
                int posTemp = 0;

                while (posy >= ymin) {
                    posx = posx + vx;
                    posy = posy + vy;
                    boolean posEstDansCarre = posx >= xmin && posx <= xmax && posy >= ymin && posy <= ymax;
                    if (vy == 0) {
                        posTemp = posy;
                    }
                    if (posEstDansCarre) {
                        if (posTemp >= posyMax) {
                            posyMax = posTemp;
                        }
                        count++;
                        break;
                    }

                    if (vx < 0) {
                        vx = vx + 1;
                    }
                    if (vx > 0) {
                        vx = vx - 1;
                    }
                    vy = vy - 1;
                }
            }
        }
        System.out.println("posymax = " + posyMax);

        System.out.println(count);

    }
}
