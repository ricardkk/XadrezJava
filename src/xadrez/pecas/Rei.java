package xadrez.pecas;

import tabuleiro.Posicao;
import tabuleiro.Tabuleiro;
import xadrez.Cor;
import xadrez.PecaXadrez;

public class Rei extends PecaXadrez {

    public Rei(Tabuleiro tabuleiro, Cor cor) {
        super(tabuleiro, cor);
    }

    @Override
    public String toString() {
        return "K";
    }

    private boolean podeSeMover(Posicao posicao) {
        PecaXadrez p = (PecaXadrez)getTabuleiro().peca(posicao);
        return p == null || p.getCor() != getCor();
    }

    @Override
    public boolean[][] movimentosPossiveis() {
        boolean[][] mat = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];

        Posicao p = new Posicao(0,0);

        // acima
        p.setValores(posicao.getLinha() - 1, posicao.getColuna());
        if (getTabuleiro().existePosicao(p) && podeSeMover(p)) {
            mat[p.getLinha()][p.getColuna()] = true;
        }

        // abaixo
        p.setValores(posicao.getLinha() + 1, posicao.getColuna());
        if (getTabuleiro().existePosicao(p) && podeSeMover(p)) {
            mat[p.getLinha()][p.getColuna()] = true;
        }

        // esquerda
        p.setValores(posicao.getLinha(), posicao.getColuna() - 1 );
        if (getTabuleiro().existePosicao(p) && podeSeMover(p)) {
            mat[p.getLinha()][p.getColuna()] = true;
        }

        // direita
        p.setValores(posicao.getLinha(), posicao.getColuna() + 1 );
        if (getTabuleiro().existePosicao(p) && podeSeMover(p)) {
            mat[p.getLinha()][p.getColuna()] = true;
        }

        // diagonal superior esquerda
        p.setValores(posicao.getLinha() - 1, posicao.getColuna() - 1 );
        if (getTabuleiro().existePosicao(p) && podeSeMover(p)) {
            mat[p.getLinha()][p.getColuna()] = true;
        }

        // diagonal superior direita
        p.setValores(posicao.getLinha() - 1, posicao.getColuna() + 1 );
        if (getTabuleiro().existePosicao(p) && podeSeMover(p)) {
            mat[p.getLinha()][p.getColuna()] = true;
        }

        // diagonal inferior esquerda
        p.setValores(posicao.getLinha() + 1, posicao.getColuna() - 1 );
        if (getTabuleiro().existePosicao(p) && podeSeMover(p)) {
            mat[p.getLinha()][p.getColuna()] = true;
        }

        // diagonal inferior direita
        p.setValores(posicao.getLinha() + 1, posicao.getColuna() + 1 );
        if (getTabuleiro().existePosicao(p) && podeSeMover(p)) {
            mat[p.getLinha()][p.getColuna()] = true;
        }

        return mat;
    }
}
