package app;

import xadrez.ExceptionXadrez;
import xadrez.PartidaXadrez;
import xadrez.PecaXadrez;
import xadrez.PosicaoXadrez;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        PartidaXadrez partidaXadrez = new PartidaXadrez();
        List<PecaXadrez> capturadas = new ArrayList<>();

        while (true) {
            try {
                Interface.limpaTela();
                Interface.printPartida(partidaXadrez, capturadas);
                System.out.println();
                System.out.print("Origem: ");
                PosicaoXadrez origem = Interface.inputPosicaoXadrez(sc);

                boolean[][] movimentosPossiveis = partidaXadrez.movimentosPossiveis(origem);
                Interface.printTabuleiro(partidaXadrez.getPecas(), movimentosPossiveis);


                System.out.println();
                System.out.print("Destino: ");
                PosicaoXadrez destino = Interface.inputPosicaoXadrez(sc);

                PecaXadrez pecaCapturada = partidaXadrez.peformaMovimentoXadrez(origem, destino);
                if (pecaCapturada != null) {
                    capturadas.add(pecaCapturada);
                }
            }
            catch (ExceptionXadrez e) {
                System.out.println(e.getMessage());
                sc.nextLine();
            }
            catch (InputMismatchException e) {
                System.out.println(e.getMessage());
                sc.nextLine();
            }
        }
    }
}
