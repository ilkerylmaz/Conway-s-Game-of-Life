package gameoflife;
public class LifeGame {
    final int habitatSatir = 19;
    final int habitatSutun = 19;
    int hucreHabitat[][];
    int hucreHabitatTmp[][];
    int[] pulsarSatir;
    int[] pSatir;
    int pulsarSutun[];
    public LifeGame() {
        pSatir = new int[] { 3, 8, 10, 15 };
        pulsarSatir = new int[] { 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0 };
        pulsarSutun = new int[] { 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0 };
        hucreHabitat = new int[habitatSatir][habitatSutun];
        hucreHabitatTmp = new int[habitatSatir][habitatSutun];
        int c = 0;
        for (int i = 0; i < habitatSatir; i++) {
            for (int y = 0; y < habitatSutun; y++) {
                hucreHabitatTmp[i][y] = c;
                hucreHabitat[i][y] = c;
            }
        }
        for (int satir = 0; satir < pSatir.length; satir++) {
            for (int sutun = 0; sutun < habitatSutun; sutun++) {
                hucreHabitat[pSatir[satir]][sutun] = pulsarSatir[sutun];
            }
        }
        for (int sutun = 0; sutun < pSatir.length; sutun++) {
            for (int satir = 0; satir < habitatSutun; satir++) {
                hucreHabitat[satir][pSatir[sutun]] = pulsarSatir[satir];
            }}}
    public void drawHabitat() {
        for (int i = 0; i < habitatSatir; i++) {
            for (int y = 0; y < habitatSutun; y++) {
                if (hucreHabitat[i][y] == 0) {
                    System.out.print("-");
                } else {
                    System.out.print("#");
                }
            }
            System.out.println();
        }}
    public int komsuCanliSayisi(int satir, int sutun) {
        int canliKomsuSayisi = 0;
        for (int i = -1; i <= 1; i++) {
            for (int y = -1; y <= 1; y++) {
                int yeniSatir = satir + i;
                int yeniSutun = sutun + y;
                if (yeniSatir >= 0 && yeniSatir < habitatSatir && yeniSutun >= 0 && yeniSutun < habitatSutun && !(i == 0 && y == 0)) {
                    canliKomsuSayisi += hucreHabitat[yeniSatir][yeniSutun];
                }}}
        return canliKomsuSayisi;
    }
    public void newHabitatRule() {
        for (int i = 1; i < habitatSatir - 1; i++) {
            for (int y = 1; y < habitatSutun - 1; y++) {
                int canliKomsuSayisi = komsuCanliSayisi(i, y);
                if (hucreHabitat[i][y] == 1) {
                    if (canliKomsuSayisi < 2 || canliKomsuSayisi > 3) {
                        hucreHabitatTmp[i][y] = 0;
                    } else {
                        hucreHabitatTmp[i][y] = 1;
                    }
                } else {
                    if (canliKomsuSayisi == 3) {
                        hucreHabitatTmp[i][y] = 1;
                    }}}}
        copyHabitat();
    }
    public void copyHabitat() {
        for (int i = 0; i < habitatSatir; i++) {
            System.arraycopy(hucreHabitatTmp[i], 0, hucreHabitat[i], 0, habitatSutun);
        }
    }
    public static void main(String[] args) throws Exception {
        LifeGame lg = new LifeGame();
        for (int i = 0; i < 20; i++) {
            lg.drawHabitat();
            lg.newHabitatRule();
            System.out.println();
            Thread.sleep(1500);
        }}}
