public class LutadorPesado extends lutador {

    public LutadorPesado(String nome) {
        super(nome, 140, 100, 20, 15, 100);
    }

    @Override
    public void especial(lutador oponente) {
        int custo = 30;

        if (energia < custo) {
            System.out.println(nome + " não tem energia para o especial!");
            return;
        }

        energia -= custo;

        int dano = forca * 4;
        oponente.receberDano(dano);

        System.out.println(nome + " usou ESPECIAL PESADO causando " + dano + " de dano!");
    }
}
