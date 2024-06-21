package xadrez;

import tabuleiro.Peca;
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

    public PecaXadrez peformaMovimentoXadrez(PosicaoXadrez origemPosicao, PosicaoXadrez destinoPosicao){
        Posicao origem = origemPosicao.paraPosicao();
        Posicao destino = destinoPosicao.paraPosicao();
        validaPosicaoOrigem(origem);
        validaPosicaoDestino(origem, destino);
        Peca pecaCapturada = fazMovimento(origem, destino);
        return (PecaXadrez)pecaCapturada;
    }

    private Peca fazMovimento(Posicao origem, Posicao destino) {
        Peca p = tabuleiro.removePeca(origem);
        Peca pecaCapturada = tabuleiro.removePeca(destino);
        tabuleiro.posicionaPeca(p, destino);
        return pecaCapturada;
    }

    private void validaPosicaoOrigem(Posicao posicao) {
        if (!tabuleiro.existePeca(posicao)) {
            throw new ExceptionXadrez("Não há peça na posição de origem");
        }
        if (!tabuleiro.peca(posicao).existeMovimentoPossivel()) {
            throw new ExceptionXadrez("Não existem movimentos possíveis para essa peça");
        }
    }

    private void validaPosicaoDestino(Posicao origem, Posicao destino) {
        if (!tabuleiro.peca(origem).movimentoPossivel(destino)) {
            throw new ExceptionXadrez("A peça de origem não pode se mover para a posição de destino");
        }
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
