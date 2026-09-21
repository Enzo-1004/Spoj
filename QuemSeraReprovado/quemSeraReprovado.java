package QuemSeraReprovado;

import java.util.Scanner;
import java.util.InputMismatchException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Comparator;

public class quemSeraReprovado{
    public static void main(String[] args) {
        System.out.println("---------------Início do Programa---------------\n");
        Scanner leia = new Scanner(System.in, StandardCharsets.UTF_8); // DEVERIA FAZER COM QUE ACENTOS PUDESSEM SER LIDOS 
        int n=0;
        boolean erro;
        do {  // TESTE DO NÚMERO DE ALUNOS
            erro = false;
            try{
                System.out.print("Digite o número de alunos (min=1, max=100): ");
                n = leia.nextInt();
                if(n<1 || n>100)
                    throw new IllegalArgumentException("Digite um número entre 1 e 100!");
            } catch (IllegalArgumentException e){
                System.out.println("Erro: " + e.getMessage());
                leia.nextLine();
                erro=true;
            } catch (InputMismatchException e){
                System.out.println("Erro: Valor Inválido! Digite um número inteiro.");
                leia.nextLine();
                erro=true;
            }
        } while (erro);
        quemSeraReprovado user = new quemSeraReprovado();
        List<alunoNota> lista = user.fazerLista(n, leia); // FAZ UMA LISTA COM O TAMANHO QUE O USUÁRIO DIGITOU
        alunoNota piorAluno = user.descobrirReprovado(lista); // PEGA A LISTA E RETORNA O PIOR ALUNO, COM BASE NOS CRITÉRIOS DO EXERCÍCIO
        int posicaoPiorAluno = 0;
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getNome().equals(piorAluno.getNome())) {
                posicaoPiorAluno = i;
            }
        }
        System.out.println("\nInstância " + posicaoPiorAluno +"\nO aluno reprovado é: " + piorAluno.getNome() + "\nCom a nota: " + piorAluno.getNota());
        System.out.println("---------------Fim do Programa---------------\n");

        leia.close();
    }

    public List<alunoNota> fazerLista(int n, Scanner leia){
        List<alunoNota> lista = new ArrayList<>();
        leia = new Scanner(System.in);
        boolean erro;
        for (int i = 0; i < n; i++) { // ADICIONANDO N ALUNOS NA LISTA
            System.out.println();
            alunoNota novoAluno = new alunoNota();
            System.out.println("Instância " + i + ": ");
            do {
                erro = false;      
                try{ // TESTE PARA VER SE O NOME É REPETIDO
                    novoAluno.setNome(leia);
                    if(lista.stream().anyMatch(item -> item.getNome().trim().equalsIgnoreCase(novoAluno.getNome().trim())))
                        throw new IllegalArgumentException("Nome já cadastrado! Digite outro nome.");
                } catch (IllegalArgumentException e){
                    System.out.println("Erro: " + e.getMessage());
                    erro=true;
                }
            } while (erro);
            novoAluno.setNota(leia);
            lista.add(novoAluno); // ADICIONA QUEM PASSOU NOS TESTES
        }
        return lista;
    }

    public alunoNota descobrirReprovado(List<alunoNota> lista) {
        double menorNota = lista.get(0).getNota();
        for (int i = 1; i < lista.size(); i++) {
            if (lista.get(i).getNota() < menorNota) {
                menorNota = lista.get(i).getNota();
            }
        }
        List<alunoNota> pioresAlunos = new ArrayList<>();// FILTRA QUEM TEM A PIOR NOTA PARA OUTRA LISTA
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getNota() == menorNota) {
                pioresAlunos.add(lista.get(i));
            }
        }
        pioresAlunos.sort(Comparator.comparing(alunoNota::getNome).reversed()); // ORDENA EM ORDEM ALFABÉTICA E REVERTE

        alunoNota piorAluno = pioresAlunos.get(0); // PEGA O PRIMEIRO DA LISTA DE PIORES ALUNOS
        return piorAluno;

    }

    //  IDEIAS DESCARTADAS:


    // public List<alunoNota> ordemAlfabeticaPiores(List<alunoNota> lista) {
    //     
    //     // MAS REVERTE A ORDEM PARA QUE O NOME MAIS PERTO DE Z SEJA O PRIMEIRO
    //     List<alunoNota> pioresAlunos = lista.stream()
    //             .filter(n -> n.getNota() == lista.get(0).getNota())
    //             .sorted(Comparator.comparing(alunoNota::getNome).reversed()) // ORDENA E REVERTE
    //             .toList(); // DEIXA A LISTA IMUTÁVEL, POR ISSO JÁ USEI O SORT ANTES
    //     return pioresAlunos;

        

    // }
    
    // BUBBLE SORT, fiz a toa para a parte de descobrir reprovado
        /*
        for (int i = 0; i < lista.size() - 1; i++) {
            boolean trocou = false;
            for (int j = 0; j < lista.size() - i - 1; j++) {
                if (lista.get(i).getNota() > lista.get(i + 1).getNota()) {
                    alunoNota temp = lista.get(i);
                    lista.set(i, lista.get(i + 1));
                    lista.set(i + 1, temp);
                    list
                    trocou = true;
                }
            }
            if (!trocou) {
                break;
            }
        }
        */

    /* TENTATIVA QUE PAREI NO MEIO:
        
        boolean deNovo = true;
        int num = 1;
        do {
            if (lista.get(0).getNota() == lista.get(num).getNota()) {
        
            }
        
        } while (deNovo);
        
        if (lista.get(0).getNome().charAt(0) == lista.get(1).getNome().charAt(0)) {
        
        }
        */
}