package WindWaker;

import java.util.Scanner;

public class WindWaker {
    public static void main(String[] args) {
        Scanner leia = new Scanner(System.in);
        System.out.println("\n~~~~~~~~~~~Olá jogador de Wind Waker~~~~~~~~~~~\n");
        System.out.println("Digite as suas atuais coordenadas(x,y): ");
        int atuaisCoordenadas[] = fazerCoordenadas();
        System.out.print("\nVocê está atrás de quantos colecionáveis/objetivos?: ");
        int n = leia.nextInt();
        int todosObjetivos[][] = new int[n][2];
        System.out.println();
        for (int i = 0; i < n; i++) {
            System.out.printf("Objetivo %d: \n", i + 1);
            int objetivo[] = fazerCoordenadas();
            todosObjetivos[i][0] = objetivo[0];
            todosObjetivos[i][1] = objetivo[1];
        }
        int melhorObjetivo = 0;
        float melhorDistancia = 2000000000;
        for (int i = 0; i < n; i++) {
            System.out.printf("\n-------------Caminho para objetivo %d-------------\n", i + 1);
            int x = atuaisCoordenadas[0];
            int y = atuaisCoordenadas[1];
            float distanciaTotal = 0;

            /* 
            whiles para ver quantas vezes cada comando seria usado
            N = Norte, E = Leste, S = Sul, W = Oeste
            Ne = noroeste
            e assim por diante.
            me descuidei e fiz as variáveis com letra maiúscula 🫤
            */
            System.out.print("Comandos: ");

            int Ne = 0;
            while (x < todosObjetivos[i][0] & y < todosObjetivos[i][1]) {
                x += 1;
                y += 1;
                distanciaTotal += Math.sqrt(2);
                Ne++;
            }
            if (Ne > 0)
                System.out.printf("Ne=%d ", Ne);

            int Se = 0;
            while (x < todosObjetivos[i][0] & y > todosObjetivos[i][1]) {
                x += 1;
                y -= 1;
                distanciaTotal += Math.sqrt(2);
                Se++;
            }
            if (Se > 0)
                System.out.printf("Se=%d ", Se);

            int Nw = 0;
            while (x > todosObjetivos[i][0] & y < todosObjetivos[i][1]) {
                x -= 1;
                y += 1;
                distanciaTotal += Math.sqrt(2);
                Nw++;
            }
            if (Nw > 0)
                System.out.printf("Nw=%d ", Nw);

            int Sw = 0;
            while (x > todosObjetivos[i][0] & y > todosObjetivos[i][1]) {
                x -= 1;
                y -= 1;
                distanciaTotal += Math.sqrt(2);
                Sw++;
            }
            if (Sw > 0)
                System.out.printf("Sw=%d ", Sw);

            int N = 0;
            while (y < todosObjetivos[i][1]) {
                y += 1;
                distanciaTotal += 1;
                N++;
            }
            if (N > 0)
                System.out.printf("N=%d ", N);

            int S = 0;
            while (y > todosObjetivos[i][1]) {
                y -= 1;
                distanciaTotal += 1;
                S++;
            }
            if (S > 0)
                System.out.printf("S=%d ", S);

            int E = 0;
            while (x < todosObjetivos[i][0]) {
                x += 1;
                distanciaTotal += 1;
                E++;
            }
            if (E > 0)
                System.out.printf("E=%d ", E);

            int W = 0;
            while (x > todosObjetivos[i][0]) {
                x -= 1;
                distanciaTotal += 1;
                W++;
            }
            if (W > 0)
                System.out.printf("W=%d ", W);


            if (melhorDistancia > distanciaTotal) {
                melhorObjetivo = i + 1;
                melhorDistancia = distanciaTotal;
            }

            System.out.println("\nDistancia Total de Viajem = " + distanciaTotal);
        }
        
        System.out.println("\n-------------------------------------------------");
        System.out.println("Objetivo menos distante = Objetivo " + melhorObjetivo);
        System.out.println("Obrigado por usar o programa.");

        leia.close();
    }
    
    public static int[] fazerCoordenadas() {
        Scanner scan = new Scanner(System.in);
        int coordenadas[] = new int[2];
        boolean valido;
        do {
            valido = true;
            System.out.print("X = ");
            coordenadas[0] = scan.nextInt();
            System.out.print("Y = ");
            coordenadas[1] = scan.nextInt();
            int tempx = coordenadas[0];
            int tempy = coordenadas[1];
            if (coordenadas[0] < 0)
                tempx *= -1;
            if (coordenadas[1] < 0)
                tempy *= -1;
            if (tempx > 50000 || tempy > 50000) {
                valido = false;
                System.out.println("Erro: suas coordenadas não podem passar de +-50000!");
            }
        } while (!valido);

        return coordenadas;
    }
    
}