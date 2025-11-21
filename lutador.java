public abstract class lutador {
    protected String nome;
    protected int vida;
    protected int energia;
    protected int forca;
    protected int defesa;
    protected int energiaMax;
    protected int defesaTemp = 0;

    public lutador(String nome, int vida, int energia, int forca, int defesa, int energiaMax) {
        this.nome = nome;
        this.vida = vida;
        this.energia = energia;
        this.forca = forca;
        this.defesa = defesa;
        this.energiaMax = energiaMax;
    }

    public String getNome() { return nome; }
    public int getVida() { return vida; }
    public int getEnergia() { return energia; }
    public int getForca() { return forca; }

    public void setEnergia(int e) {
        energia = Math.min(e, energiaMax);
    }

    public boolean estaVivo() {
        return vida > 0;
    }

    // REGRAS DE NEGÓCIO: HP não pode ficar negativo
    public void receberDano(int dano) {
        dano -= defesaTemp;
        defesaTemp = 0;

        if (dano < 0) dano = 0;

        vida -= dano;

        if (vida < 0) vida = 0;

        System.out.println(nome + " recebeu " + dano + " de dano!");
    }

    // REGRAS DE COMBATE: só ataca se ambos vivos
    public void atacar(lutador oponente) {
        if (!estaVivo()) {
            System.out.println(nome + " não pode atacar, está derrotado!");
            return;
        }
        if (!oponente.estaVivo()) {
            System.out.println("Oponente já está derrotado!");
            return;
        }

        int dano = forca;
        oponente.receberDano(dano);

        System.out.println(nome + " atacou causando " + dano + " de dano!");
    }

    // ESPECIAL precisa validar energia
    public abstract void especial(lutador oponente);

    public void defender() {
        defesaTemp = defesa;
        energia = Math.min(energia + 10, energiaMax);
        System.out.println(nome + " está defendendo (+defesa, +energia).");
    }

    public void mostrarStatus() {
        System.out.println("---- " + nome + " ----");
        System.out.println("Vida: " + vida);
        System.out.println("Energia: " + energia + "/" + energiaMax);
        System.out.println("----------------------");
    }
}
