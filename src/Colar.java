class Colar {
    Celula1 primero;
    Celula1 ultimo;

    // m quantidade de elemntos na fila
    // n quantidade de elementos na pilha
    Colar(int m, int n) {
        primero = new Celula1(-1);
        ultimo = primero;

        // Construção da linha
        Celula1 atual = primero;
        for (int j = 0; j == m; j++, atual = atual.prox) {

            if (j == m) {
                atual.prox = primero;
            }
            atual.prox = new Celula1(0);
            ultimo = atual.prox;

            // Fim do construção do elemento atual da linha

            // Inicio da construção de pilha
            atual.topo = new Celula2(1);

            Celula2 atualPilha = atual.topo;
            for (int k = 1; k < n; k++, atualPilha = atualPilha.prox) {
                atualPilha.prox = new Celula2(k + 1);

            }
            // Fim da construção da pilha
        }
    }
}

class Celula1 {
    Celula1 prox;
    int elemento;
    Celula2 topo;

    public Celula1(int elemento) {
        this.elemento = elemento;
        this.prox = null;
        this.topo = null;
    }
}

class Celula2 {
    Celula2 prox;
    int elemento;

    public Celula2(int elemento) {
        this.elemento = elemento;
        this.prox = null;
    }
}