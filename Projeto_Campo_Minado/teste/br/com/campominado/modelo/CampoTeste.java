package br.com.campominado.modelo;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.campominado.excecao.ExplosaoException;

public class CampoTeste {


	private Campo campo;

	@BeforeEach
	void iniciarCampo() {
		campo = new Campo(3,3);
	}

	@Test
	void testeVizinhoEsquerdo(){
		Campo vizinho = new Campo (3,2);
		boolean resultado = campo.adicionarVizinho(vizinho);
		assertTrue(resultado);  
	}

	@Test
	void testeVizinhoDireito(){
		Campo vizinho = new Campo (3,4);
		boolean resultado = campo.adicionarVizinho(vizinho);
		assertTrue(resultado);  
	}

	@Test
	void testeVizinhoEmCima(){
		Campo vizinho = new Campo (2,3);
		boolean resultado = campo.adicionarVizinho(vizinho);
		assertTrue(resultado);  
	}

	@Test
	void testeVizinhoEmBaixo(){
		Campo vizinho = new Campo (4,3);
		boolean resultado = campo.adicionarVizinho(vizinho);
		assertTrue(resultado);  
	}

	@Test
	void testeNaoVizinho(){
		Campo vizinho = new Campo (1,1);
		boolean resultado = campo.adicionarVizinho(vizinho);
		assertFalse(resultado);  
	}

	@Test
	void testeDistancia2(){
		Campo vizinho = new Campo (2,2);
		boolean resultado = campo.adicionarVizinho(vizinho);
		assertTrue(resultado);  
	}

	@Test
	void testeValorPadraorMarcacao() {
		assertFalse(campo.isMarcado());
	}

	@Test
	void testeAlternarMarcacao() {
		campo.alterarMarcacao();
		assertTrue(campo.isMarcado());
	}

	@Test
	void testaMarcacaoDuasChamadas() {
		campo.alterarMarcacao();
		campo.alterarMarcacao();
		assertFalse(campo.isMarcado());
	}

	@Test
	void testaAbrirCampoNaoMarcado() {
		assertTrue(campo.abrir());
	}

	@Test
	void testaAbrirMinadoMarcado() {
		campo.alterarMarcacao();
		campo.minar();
		assertFalse(campo.abrir());
	}

	@Test
	void testaAbrirMinadoNapMarcado() {
		campo.minar();

		assertThrows(ExplosaoException.class,() -> {
			campo.abrir();
		});
	}
	
	@Test
	void testeAbrirComVizinhos(){
		Campo campo11 = new Campo(1,1);
		Campo campo22 = new Campo(2,2);
		
		campo22.adicionarVizinho(campo11);
		campo.adicionarVizinho(campo22);
		
		campo.abrir();
		
		
		assertTrue(campo22.isAberto() && campo11.isAberto());
	}

	@Test
	void testeAbrirComVizinhos2(){
		Campo campo11 = new Campo(1,1);
		Campo campo22 = new Campo(2,2);
		Campo campo12 = new Campo(1,2);
		campo12.minar();
		
		campo22.adicionarVizinho(campo11);
		campo22.adicionarVizinho(campo12);
		
		campo.adicionarVizinho(campo22);
		campo.abrir();
		
		assertTrue(campo22.isAberto() && campo11.isFechado());
	}
}
