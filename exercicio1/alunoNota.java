package exercicio1;

import java.util.InputMismatchException;
import java.util.Scanner;

public class alunoNota{
    private String nome;
    private double nota;
    
    public void setNome(Scanner leia) {
        System.out.print("Digite o nome do aluno (acentos não são lidos): ");
        String nome = leia.nextLine();
        nome.substring(0, Math.min(nome.length(), 20));
        this.nome = nome;        
    }

    public String getNome(){
        return this.nome;
    }

    public void setNota(Scanner leia) {
        boolean erro;
        double nota=0;
        do { // TESTES PARA CADA NOVA NOTA QUE FOR INSERIDA
            erro = false;
            try{
                System.out.print("Digite a nota de "+this.nome+" (min=0, max=10): ");
                nota = leia.nextDouble();
                leia.nextLine();
                if(nota<0 || nota>10)
                    throw new IllegalArgumentException("Digite um numero entre 0 e 10!");
            } catch (IllegalArgumentException e) {
                erro = true;
                System.out.println("Erro: " + e.getMessage());
            } catch(InputMismatchException e){
                erro = true;
                System.out.println("Erro: Valor Inválido!");
                leia.nextLine();
            }
        } while (erro);
        this.nota = nota;
    }

    public double getNota(){
        return this.nota;
    }
}

