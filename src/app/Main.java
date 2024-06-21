package app;

import xadrez.ExceptionXadrez;
import xadrez.PartidaXadrez;
import xadrez.PecaXadrez;
import xadrez.PosicaoXadrez;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        PartidaXadrez partidaXadrez = new PartidaXadrez();

        while (true) {
            try {
                Interface.limpaTela();
                Interface.printTabuleiro(partidaXadrez.getPecas());
                System.out.println();
                System.out.print("Origem: ");
                PosicaoXadrez origem = Interface.inputPosicaoXadrez(sc);

                System.out.println();
                System.out.print("Destino: ");
                PosicaoXadrez destino = Interface.inputPosicaoXadrez(sc);

                PecaXadrez pecaCapturada = partidaXadrez.peformaMovimentoXadrez(origem, destino);
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
