/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg20161010_hanoitowers;

import java.util.Stack;

/**
 *
 * @author PC_15
 */
public class HanoiTowers {

    private final int disks; // Cantidad de discos
    private Stack<Integer> t1, t2, t3; // Torres (usadas para visualizacion)

    /**
     * Constructor
     *
     * @param disks - Integer, cantidad de discos
     */
    public HanoiTowers(int disks) {
        this.disks = disks;
    }

    /**
     * Ejecuta las instrucciones correctamente para resolver el juego
     * @param showStepStatus - Boolean, mostrar o no el estado de las torres
     *                          a cada paso
     */
    public void solve(boolean showStepStatus) {
        // Calcula la cantidad de movimientos que se tienen que llevar a cabo
        // para resolver el juego (2^n - 1, n = numero de discos)
        int limit = (int) Math.pow(2, disks) - 1;
        for (int i = 0; i < limit; i++) {
            // Calcula que disco hay que mover
            int disk = diskToMove(i);
            // Calcula la torre de origen del disco a mover
            int src = (movements(i, disk) * direction(disk, disks)) % 3;
            // Calcula la torre de destino del disco a mover
            int dest = (src + direction(disk, disks)) % 3;
            move(disk, src, dest);
            
            // Control usado para identificar los movimientos de cada disco 
            // previos a la iteracion actual
            // System.out.println("Disco " + (disk + 1) + "->" + movements(i, disk));
            
            // Decide si mostrar o no el estado de las torres a cada iteracion
            if (showStepStatus) {
                print();
            }
        }
    }

    /**
     * Inicializa y rellena las torres en la forma inicial
     */
    public void fillTowers() {
        t1 = new Stack<>();
        t2 = new Stack<>();
        t3 = new Stack<>();
        for (int i = disks; i > 0; i--) {
            t1.push(i);
        }
    }

    /**
     * Devuelve el disco que se tendrá que mover en cada iteracion
     *
     * @param i - Integer, numero de la iteracion actual
     * @return - Integer, disco a mover
     */
    private int diskToMove(int i) {
        int C, x = i + 1;
        // Continuamente divide por dos para encontrar un numero impar
        for (C = 0; x % 2 == 0; C++) {
            x /= 2;
        }
        return C;
    }

    /**
     * Calcula cuantas veces se ha movido un disco antes de la iteracion actual
     *
     * @param i - Integer, numero de la iteracion actual
     * @param d - Integer, disco actual
     * @return - Integer, numero de veces que se ha movido el disco
     */
    private int movements(int i, int d) {
        while (d-- != 0) {
            i /= 2;
        }
        return (i + 1) / 2;
    }

    private int direction(int d, int n) {
        return 2 - (n + d) % 2;
    }

    private void move(int disk, int source, int destination) {
        //Mensaje informando del movimiento realizado
        System.out.println("\nMoviendo disco [" + (disk + 1) + "] de la torre ["
                + (source + 1) + "] a la torre [" + (destination + 1) + "].");

        // Calculan de donde a donde ha sido el movimiento entre las torres
        // para el componente visual por consola.
        int m = 0;
        switch (source) {
            case 0:
                m = t1.pop();
                break;
            case 1:
                m = t2.pop();
                break;
            case 2:
                m = t3.pop();
                break;
        }
        switch (destination) {
            case 0:
                t1.push(m);
                break;
            case 1:
                t2.push(m);
                break;
            case 2:
                t3.push(m);
                break;
        }
    }

    /**
     * Imprime por consola las 3 torres con el siguiente formato: Torre1:
     * [n][n-1][n-2]...[1] Torre2: [n][n-1][n-2]...[1] Torre3:
     * [n][n-1][n-2]...[1]
     */
    public void print() {
        System.out.println("Torre 1: " + t1);
        System.out.println("Torre 2: " + t2);
        System.out.println("Torre 3: " + t3);
    }
}
