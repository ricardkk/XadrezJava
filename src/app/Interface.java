package app;

import xadrez.Cor;
import xadrez.PartidaXadrez;
import xadrez.PecaXadrez;
import xadrez.PosicaoXadrez;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;


public class Interface {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_YELLOW = "\u001B[38;5;227m";
    public static final String ANSI_WHITE = "\u001B[38;5;231m";
    public static final String ANSI_CYAN_BACKGROUND = "\u001B[48;5;81m";


    public static PosicaoXadrez inputPosicaoXadrez(Scanner sc) {
        try {
            String s = sc.nextLine();
            char coluna = s.charAt(0);
            int linha = Integer.parseInt(s.substring(1));
            return new PosicaoXadrez(coluna, linha);
        }
        catch (RuntimeException e) {
            throw new InputMismatchException("Erro na leitura. Valores válidos são de a1 até h8");
        }
    }

    public static void printPartida(PartidaXadrez partidaXadrez, List<PecaXadrez> capturadas) {
        printTabuleiro(partidaXadrez.getPecas());
        System.out.println();
        printPecasCapturadas(capturadas);
        System.out.println("Turno: " + partidaXadrez.getTurno());
        if (!partidaXadrez.getChequeMate()) {
            System.out.println("Esperando jogador: " + partidaXadrez.getJogadorAtual());
            if (partidaXadrez.getCheque()) {
                System.out.println("CHEQUE!");
            }
        }
        else {
            System.out.println("CHEQUE MATE!");
            System.out.println("O vencedor é: " + partidaXadrez.getJogadorAtual());
        }
    }

    public static void printTabuleiro(PecaXadrez[][] pecas) {
        for (int i = 0; i < pecas.length; i++) {
            System.out.print((8-i + " "));
            for (int j = 0; j < pecas.length; j++) {
                printPeca(pecas[i][j], false);
            }
            System.out.println();
        }
        System.out.println("  a b c d e f g h");
    }

    public static void printTabuleiro(PecaXadrez[][] pecas, boolean[][] movimentosPossiveis) {
        for (int i = 0; i < pecas.length; i++) {
            System.out.print((8-i + " "));
            for (int j = 0; j < pecas.length; j++) {
                printPeca(pecas[i][j], movimentosPossiveis[i][j]);
            }
            System.out.println();
        }
        System.out.println("  a b c d e f g h");
    }

    private static void printPeca(PecaXadrez peca, boolean fundo){
        if (fundo) {
            System.out.print(ANSI_CYAN_BACKGROUND);
        }
        if (peca == null) {
            System.out.print("-" + ANSI_RESET);
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

    private static void printPecasCapturadas(List<PecaXadrez> capturadas) {
        List<PecaXadrez> brancas = capturadas.stream().filter(x -> x.getCor() == Cor.BRANCO).toList();
        List<PecaXadrez> pretas = capturadas.stream().filter(x -> x.getCor() == Cor.PRETO).toList();
        System.out.println("Peças capturadas: ");
        System.out.print("Brancas: ");
        System.out.print(ANSI_WHITE);
        System.out.println(Arrays.toString(brancas.toArray()));
        System.out.print(ANSI_RESET);

        System.out.print("Pretas: ");
        System.out.print(ANSI_YELLOW);
        System.out.println(Arrays.toString(pretas.toArray()));
        System.out.print(ANSI_RESET);

    }
}
