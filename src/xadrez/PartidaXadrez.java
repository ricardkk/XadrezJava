package xadrez;

import tabuleiro.Posicao;
import tabuleiro.Tabuleiro;
import xadrez.pecas.Rei;
import xadrez.pecas.Torre;


public class PartidaXadrez {
    private Tabuleiro tabuleiro;

    public PartidaXadrez() {
        tabuleiro = new Tabuleiro(8, 8);
        setupInicial();
    }

    public PecaXadrez[][] getPecas() {
        PecaXadrez[][] mat = new PecaXadrez[tabuleiro.getLinhas()][tabuleiro.getColunas()];
        for (int i = 0; i < tabuleiro.getLinhas(); i++) {
            for (int j = 0; j < tabuleiro.getColunas(); j++) {
                mat[i][j] = (PecaXadrez) tabuleiro.peca(i, j);
            }
        }
        return mat;
    }

    private void posicionaNovaPeca(char coluna, int linha, PecaXadrez peca) {
        tabuleiro.posicionaPeca(peca, new PosicaoXadrez(coluna, linha).paraPosicao());
    }

    private void setupInicial() {
        posicionaNovaPeca('a', 8, new Torre(tabuleiro, Cor.PRETO));
        posicionaNovaPeca('h', 8, new Torre(tabuleiro, Cor.PRETO));
        posicionaNovaPeca('d', 8, new Rei(tabuleiro, Cor.PRETO));

        posicionaNovaPeca('a', 1, new Torre(tabuleiro, Cor.BRANCO));
        posicionaNovaPeca('h', 1, new Torre(tabuleiro, Cor.BRANCO));
        posicionaNovaPeca('d', 1, new Rei(tabuleiro, Cor.BRANCO));

    }
}
