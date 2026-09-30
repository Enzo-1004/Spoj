package ArmariosDoMario;

import java.util.Scanner;

public class ArmariosDoMario {
    public static void main(String[] args) {
        Scanner leia = new Scanner(System.in);
        int n;
        do{
            System.out.print("\nOlá Mário, quantos armários o clinte precisa usar?: ");
            n = leia.nextInt();
        } while (n < 1 || n > 100000);

        int quantDisponivel;
        do{
            System.out.print("Certo. E quantos armários você tem disponível?: ");
            quantDisponivel = leia.nextInt();
        } while (quantDisponivel < n || quantDisponivel > 100000);

        int[] l = new int[quantDisponivel];
        for (int i = 0; i < l.length; i++) {
            System.out.print("Digite um número de armário vazio: ");
            l[i] = leia.nextInt();
        }
        
        boolean trocou;
        for (int i = 0; i < l.length - 1; i++) { // Bubble sort
            trocou = false;
            for (int j = 0; j < l.length - i - 1; j++) {
                if (l[j] < l[j + 1]) {
                    int temp = l[j];
                    l[j] = l[j + 1];
                    l[j + 1] = temp;
                    trocou = true;
                }
            }
            if (!trocou)
                break;
        }

        int melhorNumDeInteracoes = n;
        int dica = 1;
        for (int i = 0; i <= l.length - n+1; i++) {
            int numDeInteracoes = n;

            for (int j = 0; j < n-1; j++) {
                if (l[i] + j == l[i + j]) {
                    numDeInteracoes--;
                }
            }

            if (numDeInteracoes < melhorNumDeInteracoes) {
                melhorNumDeInteracoes = numDeInteracoes;
                dica = l[i];
            }
            if (melhorNumDeInteracoes == 0)
                break;
        }
        
        System.out.println("\nProblema resolvido Mário:");
        System.out.println("Número de interações necessárias: " + melhorNumDeInteracoes);
        System.out.printf("Dica: no final você ficará com o intervalo de %d até %d", dica, dica+n-1);
        leia.close();

    }
}
