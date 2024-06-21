package tabuleiro;

public class Tabuleiro {
    private Integer linhas;
    private Integer colunas;
    private Peca[][] pecas;

    public Tabuleiro(Integer linhas, Integer colunas) {
        if (linhas < 1 || colunas < 1) {
            throw new ExceptionTabuleiro("Erro na criação de tabuleiro: linha e coluna precisam ser no mínimo 1.");
        }
        this.linhas = linhas;
        this.colunas = colunas;
        pecas = new Peca[linhas][colunas];
    }

    public Integer getLinhas() {
        return linhas;
    }

    public Integer getColunas() {
        return colunas;
    }

    public Peca peca(int linha, int coluna){
        if (!existePosicao(linha, coluna)) {
            throw new ExceptionTabuleiro("Posição não está no tabuleiro");
        }
        return pecas[linha][coluna];
    }

    public Peca peca(Posicao posicao){
        if (!existePosicao(posicao)) {
            throw new ExceptionTabuleiro("Posição não está no tabuleiro");
        }
        return pecas[posicao.getLinha()][posicao.getColuna()];
    }

    public void posicionaPeca(Peca peca, Posicao posicao){
        if (existePeca(posicao)) {
            throw new ExceptionTabuleiro("Já existe uma peça na posição " + posicao);
        }
        pecas[posicao.getLinha()][posicao.getColuna()] = peca;
        peca.posicao = posicao;
    }

    public Peca removePeca(Posicao posicao) {
        if (!existePosicao(posicao)) {
            throw new ExceptionTabuleiro("Posição não está no tabuleiro");
        }
        if (peca(posicao) == null){
            return null;
        }
        Peca aux = peca(posicao);
        aux.posicao = null;
        pecas[posicao.getLinha()][posicao.getColuna()] = null;
        return aux;
    }


    private boolean existePosicao (int linha, int coluna) {
        return linha >= 0 && linha < linhas && coluna >= 0 && coluna < colunas;
    }

    public boolean existePosicao (Posicao posicao) {
        return existePosicao(posicao.getLinha(), posicao.getColuna());
    }

    public boolean existePeca (Posicao posicao) {
        if (!existePosicao(posicao)) {
            throw new ExceptionTabuleiro("Posição não está no tabuleiro");
        }
        return peca(posicao) != null;
    }

}
