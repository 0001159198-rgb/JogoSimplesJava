public class LutadorLeve extends lutador {

    public LutadorLeve(String nome) {
        super(nome, 90, 100, 12, 8, 100);
    }

    @Override
    public void especial(lutador oponente) {
        int custo = 20;

        if (energia < custo) {
            System.out.println(nome + " não tem energia para o especial!");
            return;
        }

        energia -= custo;

        int dano = forca * 3;
        oponente.receberDano(dano);

        System.out.println(nome + " usou ESPECIAL LEVE causando " + dano + " de dano!");
    }
}
