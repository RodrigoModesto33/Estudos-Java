package br.com.campominado.visao;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Scanner;

import br.com.campominado.excecao.ExplosaoException;
import br.com.campominado.excecao.SairException;
import br.com.campominado.modelo.Tabuleiro;

public class TabuleiroConsole {

	private Tabuleiro tabuleiro;
	private Scanner entrada = new Scanner(System.in);
	public TabuleiroConsole(Tabuleiro tabuleiro) {
		this.tabuleiro = tabuleiro;

		executarJogo();
	}

	private void executarJogo() {
		try {
			boolean continuar = true;

			while (continuar) {
				cicloDoJogo();

				System.out.println("Outra partida? (S/n)");
				String resposta = entrada.nextLine();

				if ("n".equalsIgnoreCase(resposta)) {
					continuar = false;
				}
				else {
					tabuleiro.reiniciar();
				}

			}
		} catch (SairException e) {
			System.out.println("Adeus!!!");
		} finally {
			entrada.close();
		}
	}

	private void cicloDoJogo() {
		try {
			while (!tabuleiro.objetivoAlcancado()) {
				System.out.println(tabuleiro); 

				String digitado = capturarValorDigitado("Digite as coordenandas ou sair para encerrar: ");

				Iterator <Integer> coordenada = Arrays.stream(digitado.split(","))
						.map(e -> Integer.parseInt(e.trim()))
						.iterator();

				digitado = capturarValorDigitado("1-Abrir\n2-Para (Des)Marcar: ");

				if("1".equals(digitado)) {
					tabuleiro.abrir(coordenada.next(),coordenada.next());
				}else if("2".equals(digitado)) {
					tabuleiro.alterarMarcacao(coordenada.next(),coordenada.next());
				}
			}
			System.out.println("Você ganhou!!!");
		} catch (ExplosaoException e) {
			System.out.println(tabuleiro);
			System.out.println("Você perdeu!!!");
		}
	}

	private String capturarValorDigitado(String texto) {
		System.out.println(texto);
		String digitado = entrada.nextLine();
		if ("sair".equalsIgnoreCase(digitado)) {
			throw new SairException();
		}
		return digitado;
	}
}
