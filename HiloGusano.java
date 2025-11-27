public class HiloGusano extends Thread {
    private char[][] jardin;
    private int filas;
    private int columnas;

    public HiloGusano(char[][] mundo) {
        this.jardin = mundo;
        this.filas = mundo.length;
        this.columnas = mundo[0].length;
    }

    private void limpiarJardin() {
        synchronized (jardin) {
            for (int i = 0; i < filas; i++) {
                for (int j = 0; j < columnas; j++) {
                    jardin[i][j] = '.';
                }
            }
        }
    }

    @Override
    public void run() {
        int totalcasillas = filas * columnas;
        int vida = totalcasillas;
        int fila = 0;
        int col = 0;

        try {
            limpiarJardin();

            while (vida > 0) {

                synchronized (jardin) {
                    jardin[fila][col] = 'W';
                }

                Thread.sleep(1000);
                
                synchronized (jardin) {
                    jardin[fila][col] = '-';
                    
                    col++;
                    if (col >= columnas) {
                        col = 0;
                        fila++;
                        if (fila >= filas) {
                            break; 
                        }
                    }
                }

                vida--;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}