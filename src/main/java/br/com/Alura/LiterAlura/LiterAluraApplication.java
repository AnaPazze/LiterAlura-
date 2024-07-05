package br.com.Alura.LiterAlura;

import br.com.Alura.LiterAlura.Principal.principal;
import br.com.Alura.LiterAlura.Repository.IRepositorioLivro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LiterAluraApplication implements CommandLineRunner {


	@Autowired
	private IRepositorioLivro repositorio;

	public static void main(String[] args) {
		SpringApplication.run(LiterAluraApplication.class, args);

	}

	@Override
	public void run(String... args) throws Exception {

		principal Principal = new principal(repositorio);
		Principal.Menu();

	}
}
