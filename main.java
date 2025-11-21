import java.util.Random;
import java.util.Scanner;

public class main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Random rnd = new Random();

        System.out.println("===== CRIAÇÃO DO JOGADOR =====");
        System.out.print("Digite o nome do seu lutador: ");
        String nomeJogador = sc.nextLine();

        System.out.print("Escolha o tipo (1-Leve, 2-Médio, 3-Pesado): ");
        int tipo = sc.nextInt();

        lutador jogador;

        switch (tipo) {
            case 1: jogador = new LutadorLeve(nomeJogador); break;
            case 2: jogador = new LutadorMedio(nomeJogador); break;
            case 3: jogador = new LutadorPesado(nomeJogador); break;
            default:
                System.out.println("Tipo inválido, escolhendo Médio.");
                jogador = new LutadorMedio(nomeJogador);
        }

        // Oponente fixo
        lutador oponente = new LutadorPesado("Oponente");

        // Mostrar status inicial
        System.out.println("\n===== STATUS INICIAL =====");
        jogador.mostrarStatus();
        oponente.mostrarStatus();

        // LOOP DE BATALHA 
        while (jogador.estaVivo() && oponente.estaVivo()) {

            System.out.println("\n===== SUA VEZ =====");
            System.out.println("Escolha a ação:");
            System.out.println("1 - Ataque Normal");
            System.out.println("2 - Golpe Especial");
            System.out.println("3 - Defesa");
            System.out.print("Escolha: ");
            int acao = sc.nextInt();

            switch (acao) {
                case 1:
                    jogador.atacar(oponente);
                    break;
                case 2:
                    jogador.especial(oponente);
                    break;
                case 3:
                    jogador.defender();
                    break;
                default:
                    System.out.println("Ação inválida! Perdeu o turno.");
            }

            if (!oponente.estaVivo()) break;

            // TURNO DO OPONENTE
            System.out.println("\n===== TURNO DO OPONENTE =====");

            int acaoOp = rnd.nextInt(3) + 1;

            if (acaoOp == 1) {
                oponente.atacar(jogador);
            } else if (acaoOp == 2) {
                oponente.especial(jogador);
            } else {
                oponente.defender();
            }

            if (!jogador.estaVivo()) break;

            System.out.println("\n===== STATUS ATUAL =====");
            jogador.mostrarStatus();
            oponente.mostrarStatus();
        }

        System.out.println("\n===== FIM DA BATALHA =====");

        if (jogador.estaVivo()) {
            System.out.println(jogador.nome + " VENCEU!");
        } else {
            System.out.println(oponente.nome + " venceu!");
        }

        sc.close();
    }
}
