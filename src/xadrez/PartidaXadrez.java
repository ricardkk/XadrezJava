package xadrez;

import tabuleiro.Peca;
import tabuleiro.Posicao;
import tabuleiro.Tabuleiro;
import xadrez.pecas.Rei;
import xadrez.pecas.Torre;

import java.util.ArrayList;
import java.util.List;


public class PartidaXadrez {
    private int turno;
    private Cor jogadorAtual;
    private Tabuleiro tabuleiro;
    private boolean cheque; //false por padrão

    private List<Peca> pecasNoTabuleiro = new ArrayList<>();
    private List<Peca> pecasCapturadas = new ArrayList<>();


    public PartidaXadrez() {
        tabuleiro = new Tabuleiro(8, 8);
        turno = 1;
        jogadorAtual = Cor.BRANCO;
        setupInicial();
    }

    public int getTurno() {
        return turno;
    }

    public Cor getJogadorAtual() {
        return jogadorAtual;
    }

    public boolean getCheque() {
        return cheque;
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

    public boolean[][] movimentosPossiveis(PosicaoXadrez origemPosicao) {
        Posicao posicao = origemPosicao.paraPosicao();
        validaPosicaoOrigem(posicao);
        return tabuleiro.peca(posicao).movimentosPossiveis();
    }

    public PecaXadrez peformaMovimentoXadrez(PosicaoXadrez origemPosicao, PosicaoXadrez destinoPosicao){
        Posicao origem = origemPosicao.paraPosicao();
        Posicao destino = destinoPosicao.paraPosicao();
        validaPosicaoOrigem(origem);
        validaPosicaoDestino(origem, destino);
        Peca pecaCapturada = fazMovimento(origem, destino);

        if (testeCheque(jogadorAtual)) {
            desfazMovimento(origem, destino, pecaCapturada);
            throw new ExceptionXadrez("Você não pode se colocar em cheque");
        }

        cheque = (testeCheque(oponente(jogadorAtual))) ? true : false;

        proximoTurno();
        return (PecaXadrez)pecaCapturada;
    }

    private Peca fazMovimento(Posicao origem, Posicao destino) {
        Peca p = tabuleiro.removePeca(origem);
        Peca pecaCapturada = tabuleiro.removePeca(destino);
        tabuleiro.posicionaPeca(p, destino);

        if (pecaCapturada != null) {
            pecasNoTabuleiro.remove(pecaCapturada);
            pecasCapturadas.add(pecaCapturada);
        }

        return pecaCapturada;
    }

    private void desfazMovimento(Posicao origem, Posicao destino, Peca pecaCapturada) {
        Peca p = tabuleiro.removePeca(destino);
        tabuleiro.posicionaPeca(p, origem);

        if (pecaCapturada != null) {
            tabuleiro.posicionaPeca(pecaCapturada, destino);
            pecasCapturadas.remove(pecaCapturada);
            pecasNoTabuleiro.remove(pecaCapturada);
        }
    }

    private void validaPosicaoOrigem(Posicao posicao) {
        if (!tabuleiro.existePeca(posicao)) {
            throw new ExceptionXadrez("Não há peça na posição de origem");
        }
        if (jogadorAtual != ((PecaXadrez)tabuleiro.peca(posicao)).getCor()){
            throw new ExceptionXadrez("A peça escolhida não é de sua cor");
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

    private void proximoTurno() {
        turno++;
        jogadorAtual = (jogadorAtual == Cor.BRANCO) ? Cor.PRETO : Cor.BRANCO;
    }

    private Cor oponente(Cor cor) {
        if (cor == Cor.BRANCO) {
            return Cor.PRETO;
        }
        else {
            return Cor.BRANCO;
        }
    }

    private PecaXadrez rei(Cor cor) {
        List<Peca> lista = pecasNoTabuleiro.stream().filter(x -> ((PecaXadrez)x).getCor() == cor).toList();
        for (Peca p : lista) {
            if (p instanceof Rei) {
                return (PecaXadrez) p;
            }
        }
        throw new IllegalStateException("Não há rei da cor " + cor + " no tabuleiro");
    }

    private boolean testeCheque(Cor cor) {
        Posicao reiPosicao = rei(cor).getPosicaoXadrez().paraPosicao();
        List<Peca> pecasOponentes = pecasNoTabuleiro.stream().filter(x -> ((PecaXadrez)x).getCor() == oponente(cor)).toList();
        for (Peca p : pecasOponentes) {
            boolean[][] mat = p.movimentosPossiveis();
            if (mat[reiPosicao.getLinha()][reiPosicao.getColuna()]) {
                return true;
            }
        }
        return false;
    }

    private void posicionaNovaPeca(char coluna, int linha, PecaXadrez peca) {
        tabuleiro.posicionaPeca(peca, new PosicaoXadrez(coluna, linha).paraPosicao());
        pecasNoTabuleiro.add(peca);
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
