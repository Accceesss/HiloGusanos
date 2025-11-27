public class MonitorJardin extends Thread {
    private char[][] jardin;

    public MonitorJardin(char[][] jardin) {
        this.jardin = jardin;
    }

    @Override
    public void run() {
        int filas = jardin.length;
        int columnas = jardin[0].length;

        try {
            while (true) {

                synchronized (jardin) {
                    System.out.println("================================================");
                    System.out.println("--- JARDIN (Monitor) ---");
                    for (int i = 0; i < filas; i++) {
                        for (int j = 0; j < columnas; j++) {
                            System.out.print("| " + jardin[i][j] + " ");
                        }
                        System.out.println("|");
                    }
                }

                Thread.sleep(1000); 
            }
        } catch (InterruptedException e) {
            System.out.println("Monitor interrumpido.");
        }
    }
}