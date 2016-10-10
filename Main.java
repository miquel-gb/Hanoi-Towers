/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg20161010_hanoitowers;

import java.util.Scanner;

/**
 *
 * @author PC_15
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean showStepStatus;
        char c;
        System.out.println("Introduce el número de discos de la torre inicial:");
        HanoiTowers towers = new HanoiTowers(sc.nextInt());
        do {
            System.out.println("¿Desea ver el estado de las torres a cada "
                    + "iteracion?");
            c = sc.next().charAt(0);
        } while( c != 's' && c != 'n' && c != 'S' && c != 'N');
        showStepStatus = (c == 's' || c == 'S');
        towers.fillTowers();
        System.out.println("Estado inicial de las torres:");
        towers.print();
        towers.solve(showStepStatus);
        System.out.println("\nEstado final de las torres:");
        towers.print();
    }
    
}
