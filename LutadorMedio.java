public class LutadorMedio extends lutador {

    public LutadorMedio(String nome) {
        super(nome, 120, 100, 15, 10); 
    }

    @Override
    public void atacar(lutador oponente) {
        int dano = getForca(); // ataque normal
        oponente.receberDano(dano);
        System.out.println(getNome() + " atacou causando " + dano + " de dano!");
    }

    @Override
    public void especial(lutador oponente) {
        if (getEnergia() < 20) {
            System.out.println(getNome() + " não tem energia para o ESPECIAL!");
            return;
        }

        setEnergia(getEnergia() - 20);

        int dano = getForca() * 3;
        oponente.receberDano(dano);

        System.out.println(getNome() + " usou o ESPECIAL MÉDIO causando " + dano + " de dano!");
    }
}
