package app;

import xadrez.Cor;
import xadrez.PecaXadrez;

public class Interface {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_WHITE = "\u001B[37m";

    public static void printTabuleiro(PecaXadrez[][] pecas) {
        for (int i = 0; i < pecas.length; i++) {
            System.out.print((8-i + " "));
            for (int j = 0; j < pecas.length; j++) {
                printPeca(pecas[i][j]);
            }
            System.out.println();
        }
        System.out.println("  a b c d e f g h");
    }

    private static void printPeca(PecaXadrez peca){
        if (peca == null) {
            System.out.print("-");
        }
        else {
            if (peca.getCor() == Cor.BRANCO) {
                System.out.print(ANSI_WHITE + peca + ANSI_RESET);
            }
            else {
                System.out.print(ANSI_YELLOW + peca + ANSI_RESET);
            }
        }
        System.out.print(" ");
    }
}
