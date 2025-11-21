public class LutadorPesado extends lutador {

    public LutadorPesado(String nome) {
        super(nome, 140, 100, 20, 15); 
    }

    @Override
    public void atacar(lutador oponente) {
        int dano = getForca() + 5; // pesado bate mais forte
        oponente.receberDano(dano);
        System.out.println(getNome() + " atacou (pesado) causando " + dano + " de dano!");
    }

    @Override
    public void especial(lutador oponente) {
        if (getEnergia() < 30) {
            System.out.println(getNome() + " não tem energia para o especial!");
            return;
        }

        setEnergia(getEnergia() - 30);

        int dano = getForca() * 4;
        oponente.receberDano(dano);

        System.out.println(getNome() + " usou o ESPECIAL PESADO causando " + dano + " de dano!");
    }
}
