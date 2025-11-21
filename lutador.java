import java.util.Random;
import java.util.Scanner;

// =================== CLASSE LUTADOR ===================
public class lutador {
    private String nome;
    private int vida;
    private int energia;
    private int forca;
    private int energiaMax;
    private int defesa;
    private int defesaTemporaria = 0;

    public lutador(String nome, int vida, int energia, int forca, int energiaMax, int defesa) {
        this.nome = nome;
        this.vida = vida;
        this.energia = energia;
        this.forca = forca;
        this.energiaMax = energiaMax;
        this.defesa = defesa;
    }

    // Getters e setters
    public String getNome() { return nome; }
    public int getVida() { return vida; }
    public int getEnergia() { return energia; }
    public int getEnergiaMax() { return energiaMax; }
    public int getForca() { return forca; }
    public int getDefesa() { return defesa; }
    public int getDefesaTemporaria() { return defesaTemporaria; }

    public void setVida(int vida) { this.vida = vida; }
    public void setEnergia(int energia) { this.energia = energia; }

    // ----------------- TOMAR DANO -----------------
    public void tomarDano(int dano) {

        if (defesaTemporaria > 0) {
            dano -= defesaTemporaria;
            if (dano < 0) dano = 0;

            System.out.println(nome + " reduziu o dano em " + defesaTemporaria);
            defesaTemporaria = 0;
        }

        vida -= dano;
        if (vida < 0) vida = 0;
    }

    // ----------------- ATAQUE -----------------
    public void atacar(lutador oponente) {
        oponente.tomarDano(this.forca);
        System.out.println(this.nome + " atacou " + oponente.getNome() + " causando " + this.forca + " de dano!");
    }

    // ----------------- ESPECIAL -----------------
    public void especial(lutador oponente) {
        int custo = 20;
        int danoEspecial = this.forca + (this.energia / 2);

        if (this.energia < custo) {
            System.out.println(this.nome + " não tem energia suficiente para o ESPECIAL!");
            return;
        }

        this.energia -= custo;
        oponente.tomarDano(danoEspecial);

        System.out.println(this.nome + " usou o ESPECIAL causando " + danoEspecial + " de dano em " + oponente.getNome());
    }

    // ----------------- DEFENDER -----------------
    public void defender() {
        Random r = new Random();
        int sorte = r.nextInt(2); // 0 ou 1

        if (sorte == 0) {
            int energiaGanha = 5;
            this.energia += energiaGanha;

            if (this.energia > this.energiaMax)
                this.energia = this.energiaMax;

            System.out.println(this.nome + " se defendeu e recuperou " + energiaGanha + " de energia!");
        } else {
            defesaTemporaria = this.forca / 3;
            System.out.println(this.nome + " ergueu a guarda e reduzirá " + defesaTemporaria + " de dano no próximo golpe!");
        }
    }

    public void mostrarStatus() {
        System.out.println("\n===== STATUS DE " + nome + " =====");
        System.out.println("Vida: " + vida);
        System.out.println("Energia: " + energia + "/" + energiaMax);
        System.out.println("==============================\n");
    }

    public boolean isVivo() { return vida > 0; }
}



// =================== CLASSE MAIN ===================
class batalha {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Random r = new Random();

        // Criar os lutadores
        lutador jogador = new lutador("Jogador", 100, 50, 20, 50, 10);
        lutador inimigo = new lutador("Inimigo", 100, 50, 18, 50, 8);

        System.out.println("=== INÍCIO DA BATALHA ===");

        // -------- LOOP DA LUTA --------
        while (jogador.isVivo() && inimigo.isVivo()) {

            jogador.mostrarStatus();
            inimigo.mostrarStatus();

            // Turno do jogador
            System.out.println("Escolha sua ação:");
            System.out.println("1 - Ataque normal");
            System.out.println("2 - Golpe especial");
            System.out.println("3 - Defender");
            System.out.print("Opção: ");

            int acao = sc.nextInt();

            switch (acao) {
                case 1:
                    jogador.atacar(inimigo);
                    break;
                case 2:
                    jogador.especial(inimigo);
                    break;
                case 3:
                    jogador.defender();
                    break;
                default:
                    System.out.println("Opção inválida! Perdeu o turno!");
            }

            // Verifica se o inimigo morreu
            if (!inimigo.isVivo()) break;

            // Turno do inimigo (aleatório)
            int acaoInimigo = r.nextInt(3) + 1;

            if (acaoInimigo == 1) {
                inimigo.atacar(jogador);
            } else if (acaoInimigo == 2) {
                inimigo.especial(jogador);
            } else {
                inimigo.defender();
            }

            // Verifica se o jogador morreu
            if (!jogador.isVivo()) break;

            System.out.println("\n---------------------------------\n");
        }

        // -------- FIM DA BATALHA --------
        if (jogador.isVivo())
            System.out.println("\n🎉 O jogador venceu a batalha!");
        else
            System.out.println("\n💀 O inimigo venceu!");

        sc.close();
    }
}
