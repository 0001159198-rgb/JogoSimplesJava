import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Random rnd = new Random();

        System.out.println("===== CRIAÇÃO DO JOGADOR =====");
        System.out.print("Digite o nome do seu lutador: ");
        String nomeJogador = sc.nextLine();

        int tipo = 0;

        // Entrada protegida
        do {
            try {
                System.out.print("Escolha o tipo (1-Leve, 2-Médio, 3-Pesado): ");
                tipo = sc.nextInt();

                if (tipo < 1 || tipo > 3) {
                    System.out.println("Opção inválida!");
                    tipo = 0;
                }

            } catch (InputMismatchException e) {
                System.out.println("Por favor, digite apenas números!");
                sc.nextLine(); 
            }
        } while (tipo == 0);

        lutador jogador;

        switch (tipo) {
            case 1: jogador = new LutadorLeve(nomeJogador); break;
            case 2: jogador = new LutadorMedio(nomeJogador); break;
            case 3: jogador = new LutadorPesado(nomeJogador); break;
            default: jogador = new LutadorMedio(nomeJogador);
        }

        lutador oponente = new LutadorPesado("Oponente");

        System.out.println("\n===== STATUS INICIAL =====");
        jogador.mostrarStatus();
        oponente.mostrarStatus();

        // LOOP DE BATALHA
        while (jogador.estaVivo() && oponente.estaVivo()) {

            int acao = 0;

            // Proteção da ação do jogador
            do {
                try {
                    System.out.println("\n===== SUA VEZ =====");
                    System.out.println("1 - Ataque Normal");
                    System.out.println("2 - Golpe Especial");
                    System.out.println("3 - Defesa");
                    System.out.print("Escolha: ");

                    acao = sc.nextInt();

                    if (acao < 1 || acao > 3) {
                        System.out.println("Opção inválida!");
                        acao = 0;
                    }

                } catch (InputMismatchException e) {
                    System.out.println("Digite apenas números!");
                    sc.nextLine();
                }

            } while (acao == 0);

            // EXECUTAR AÇÃO DO JOGADOR
            switch (acao) {
                case 1: jogador.atacar(oponente); break;
                case 2: jogador.especial(oponente); break;
                case 3: jogador.defender(); break;
            }

            if (!oponente.estaVivo()) break;

            // TURNO DO OPONENTE
            System.out.println("\n===== TURNO DO OPONENTE =====");

            int acaoOp = rnd.nextInt(3) + 1;

            if (acaoOp == 1) oponente.atacar(jogador);
            else if (acaoOp == 2) oponente.especial(jogador);
            else oponente.defender();

            if (!jogador.estaVivo()) break;

            System.out.println("\n===== STATUS ATUAL =====");
            jogador.mostrarStatus();
            oponente.mostrarStatus();
        }

        System.out.println("\n===== FIM DA BATALHA =====");

        if (jogador.estaVivo()) {
            System.out.println(jogador.nome + " venceu!");
        } else {
            System.out.println(oponente.nome + " venceu!");
        }
    }
}
