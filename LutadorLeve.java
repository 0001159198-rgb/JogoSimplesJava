public class LutadorLeve extends lutador {

    public LutadorLeve(String nome) {
        super(nome, 80, 100, 10, 5); 
    }

    @Override
    public void atacar(lutador oponente) {
        int dano = getForca(); // leve bate fraco
        oponente.receberDano(dano);
        System.out.println(getNome() + " atacou causando " + dano + " de dano!");
    }

    @Override
    public void especial(lutador oponente) {
        if (getEnergia() < 15) {
            System.out.println(getNome() + " não tem energia para o ESPECIAL!");
            return;
        }

        setEnergia(getEnergia() - 15);

        int dano = getForca() * 2; // leve tem especial moderado
        oponente.receberDano(dano);

        System.out.println(getNome() + " usou o ESPECIAL LEVE causando " + dano + " de dano!");
    }
}
