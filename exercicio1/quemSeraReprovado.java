package exercicio1;

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
        System.out.println("\nO aluno reprovado é: " + piorAluno.getNome() + "\nCom a nota: " + piorAluno.getNota());
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
        // BUBBLE SORT
        for (int i = 0; i < lista.size() - 1; i++) {
            for (int j = 0; j < lista.size() - i - 1; j++) {
                if (lista.get(i).getNota() > lista.get(i + 1).getNota()) {
                    alunoNota temp = lista.get(i);
                    lista.set(i, lista.get(i + 1));
                    lista.set(i + 1, temp);
                }
            }
        }
        alunoNota piorAluno = ordemAlfabeticaPiores(lista).get(0); // PEGA O PRIMEIRO DA LISTA DE PIORES ALUNOS
        return piorAluno;
        
    }

    public List<alunoNota> ordemAlfabeticaPiores(List<alunoNota> lista) {
        // FILTRA QUEM TEM A PIOR NOTA PARA OUTRA LISTA E ORDENA EM ORDEM ALFABÉTICA
        // MAS REVERTE A ORDEM PARA QUE O NOME MAIS PERTO DE Z SEJA O PRIMEIRO
        List<alunoNota> pioresAlunos = lista.stream()
                .filter(n -> n.getNota() == lista.get(0).getNota())
                .sorted(Comparator.comparing(alunoNota::getNome).reversed()) // ORDENA E REVERTE
                .toList(); // DEIXA A LISTA IMUTÁVEL, POR ISSO JÁ USEI O SORT ANTES
        return pioresAlunos;

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
}