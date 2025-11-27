import java.util.Scanner;

public class MainGus {
    public static void main(String[] args) {

        final int FILAS = 12;
        final int COLUMNAS = 16;
        char [][] mapa = new char[FILAS][COLUMNAS];

        for(int r=0;r<FILAS;r++){
            for(int c=0;c<COLUMNAS;c++){
                mapa[r][c]='*';
            }
        }

        Scanner scanner = new Scanner(System.in);

        System.out.println("Ingrese la cantidad de gusanos a colocar:");
        int numGusanos = scanner.nextInt();

        int[] columnasInicio = new int[numGusanos]; //lista columnas

        for (int i = 0; i < numGusanos; i++) {
            System.out.println("Ingrese la columna de inicio (0-" + (COLUMNAS - 1) + ") para el gusano " + (i + 1) + ":");
            columnasInicio[i] = scanner.nextInt();
        }

        scanner.close();

        HiloGusano[] gusanos = new HiloGusano[numGusanos]; //lista gusanos

        for (int i = 0; i < numGusanos; i++) {
            gusanos[i] = new HiloGusano(mapa, columnasInicio[i]);
            gusanos[i].start();
        }

        MonitorMapa monitor = new MonitorMapa(mapa); //dibujar el mapa
        monitor.start();

        try{
            for (int i = 0; i < numGusanos; i++) {
                gusanos[i].join();
            }
        }
        catch(InterruptedException e){
            System.out.println("Interrupción en el Main: " + e.getMessage());
        }

        monitor.terminar();
        System.out.println("\nPrograma finalizado.");
    }
}