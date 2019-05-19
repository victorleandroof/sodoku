package br.com.victor;

import java.util.Scanner;

public class SudokuBacktracker {
	
	public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        //leitura do tabuleiro
        Integer N = input.nextInt();
        //criar o tamanho
        Integer TAMANHO = N * N;
        //criar celulas do tabulero
        Integer CELULAS = N * N * N * N;
        //inicia puzzle - campos vazio preencher com 0
        Integer[] puzzle = new Integer[CELULAS];
        for (Integer i = 0; i < CELULAS; i++) {
            puzzle[i] = input.nextInt();
        }
        //chama a funcao do backtracker verifica se verdadeiro
        if (backtrackerSodoku(puzzle)) {
            System.out.println("OOOOO ACHAMOS UMA SOLUCAO");
            for (int i = 0; i < CELULAS; i++) {
                System.out.print(" " + puzzle[i]);
                if ((i + 1) % N == 0) System.out.print(" |");
                if (i != 0 && (i + 1) % TAMANHO == 0) System.out.println();
                if ((i + 1) % (N*TAMANHO)  == 0) System.out.println("----------------------");
            }
        } else {
            System.out.println("NAO ACHAMOS UMA SOLUCAO");
        }
        input.close();
        System.out.println();
	}	
	
	
	public static Boolean backtrackerSodoku(Integer[] puzzle) {
		Integer N = (int) Math.round(Math.pow(puzzle.length, 0.25d));
        Integer TAMANHO = N * N;
        Integer CELULAS = TAMANHO * TAMANHO;
        Boolean celulasNaoVazia = true;
        Integer linha = 0, coluna = 0;
        for (int i = 0; i < CELULAS; i++) {
            if (puzzle[i] == 0) {
            	linha = i / TAMANHO;
            	coluna = i % TAMANHO;
            	celulasNaoVazia = false;
                break;
            }
        }
        if (celulasNaoVazia) return true;
        for (Integer escolha = 1; escolha <= TAMANHO; escolha++) {
            Boolean valido = true;
            int gridLinha = linha / N;
            int gridColuna = coluna / N;
            
            for (Integer row = N * gridLinha; row < N * gridLinha + N; row++)
                for (Integer col = N * gridColuna; col < N * gridColuna + N; col++)
                    if (puzzle[row * TAMANHO + col] == escolha)
                        valido = false;

            for (Integer j = 0; j < TAMANHO; j++)
                if (puzzle[TAMANHO * j + coluna] == escolha || puzzle[linha * TAMANHO + j] == escolha) {
                    valido = false;
                    break;
                }


            if (valido) {
                puzzle[linha * TAMANHO + coluna] = escolha;
                Boolean resolvido = backtrackerSodoku(puzzle);
                if (resolvido) return true;
                else puzzle[linha * TAMANHO + coluna] = 0;
            }
        }
        return false;
    }

}
