class Colar{
    Celula1 primero;
    Celula1 ultimo;

    // m quantidade de elemntos na fila
    // n quantidade de elementos na pilha
    Colar(int m, int n){
        System.out.println(m);
        primero = new Celula1(-1);
        ultimo = primero;

        // Construção da linha
        for(int i = 0; i < m; i++){

            // Construção da linha / inserindo no fim
            ultimo.prox = new Celula1(1);
            ultimo = ultimo.prox;
            // Se for a ultima celula do tamanho da lista ele liga o prox dela ao inicio verdadeiro da lista (lembrando que é primeiro.prox porcausa da café com leite)
            if(i == m - 1){
                ultimo.prox = primero.prox;
            }

            // Inicio da construção de pilha
            ultimo.topo = new Celula2(1);
            // Elemento por elemento da pilha considerando que um elemento ja foi pelo topo estar declarado já
            Celula2 atualPilha = ultimo.topo;
            for(int k = 1; k < n; k++, atualPilha = atualPilha.prox){
                atualPilha.prox = new Celula2(k + 1);
            }
            // Fim da construção da pilha
            
        }

    }

    public void mostrar(){
        for(Celula1 i = primero.prox; i != ultimo; i = i.prox){
            System.out.print(i + " ");
        }
        System.out.print("\n");
        for(Celula1 i = primero.prox; i != ultimo; i = i.prox){
            System.out.print(i.prox + " ");
        }
    }
    
}

class Principal{
    public static void main(String[] args) {
        Colar teste = new Colar(3, 5);
        teste.mostrar();

    }
}

class Celula1 {
    Celula1 prox;
    int elemento;
    Celula2 topo;

    public Celula1(int elemento){
        this.elemento = elemento;
        this.prox = null;
        this.topo = null;
    }
}

class Celula2 {
    Celula2 prox;
    int elemento;

    public Celula2(int elemento){
        this.elemento = elemento;
        this.prox = null;
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