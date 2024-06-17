package app;

import tabuleiro.Posicao;
import tabuleiro.Tabuleiro;
import xadrez.PartidaXadrez;

public class Main {
    public static void main(String[] args) {

        PartidaXadrez partida = new PartidaXadrez();

        UI.printTabuleiro(partida.getPecas());
    }
}
