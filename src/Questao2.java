public class Questao2 {
    MatrixCell start;
    int height, width; // height para linhas e width para colunas

    Questao2() {
        this.start = new MatrixCell(); // Brain
        this.height = this.width = 0;
    }

    boolean isEmpty() {
        return height == 0 && width == 0;
    }

    void fazerBuraco(int l, int c) throws Exception {
        if (isEmpty())
            throw new Exception("A matriz já se encontra vazia!");

        if (c < 0 || c > width || l < 0 || l > height)
            throw new Exception("The values must be in between 0 and height or width");

        MatrixCell aux = start; // percorrer a matriz até os itens desejados

        for (int counter = 0; counter < c; aux = aux.right, counter++)
            ; // atualiza aux pela direita até a coluna desejada

        for (int index = 0; index < l; aux = aux.bottom, index++)
            ; // atualiza aux por baixo até a linha desejada

        aux.left.right = aux.top.bottom = aux.right.left = aux.bottom.top = null;
        aux.top = aux.bottom = aux.left = aux.right = null;
    }
}