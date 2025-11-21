public class LutadorMedio extends lutador {

    public LutadorMedio(String nome) {
        super(nome, 120, 100, 16, 10, 100);
    }

    @Override
    public void especial(lutador oponente) {
        int custo = 25;

        if (energia < custo) {
            System.out.println(nome + " não tem energia para o especial!");
            return;
        }

        energia -= custo;

        int dano = forca * 3;
        oponente.receberDano(dano);

        System.out.println(nome + " usou ESPECIAL MÉDIO causando " + dano + " de dano!");
    }
}
