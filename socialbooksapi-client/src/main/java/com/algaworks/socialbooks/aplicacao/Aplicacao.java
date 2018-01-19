package com.algaworks.socialbooks.aplicacao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import com.algaworks.socialbooks.client.LivrosClient;
import com.algaworks.socialbooks.client.domain.Livro;

public class Aplicacao {

	public static void main(String[] args) throws ParseException {
		
		LivrosClient cliente = new LivrosClient("http://localhost:8080", "impa", "senh4");
		
		List<Livro> listaLivros = cliente.listar();
		
		for (Livro livro : listaLivros) {
			System.out.println("Livro: " + livro.getNome());
		}
		
		//Incluir um livro (POST)
		Livro livro = new Livro();
		livro.setNome("Algoritmo");
		livro.setEditora("IMPA");		
		SimpleDateFormat publicacao = new SimpleDateFormat("dd/MM/yyyy"); 
		livro.setPublicacao(publicacao.parse("01/01/2018"));
		livro.setResumo("Livro de logica");
		
		String localizacao = cliente.salvar(livro);
		System.out.println("URI do livro salvo: " + localizacao);
		
		//Buscar somente um livro
		Livro livroBuscado = cliente.buscar(localizacao);
		
		System.out.println("Libro buscado: " + livroBuscado.getNome());
		
	}
}
