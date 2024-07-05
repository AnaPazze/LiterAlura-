package br.com.Alura.LiterAlura.Repository;

import br.com.Alura.LiterAlura.Model.Autor;
import br.com.Alura.LiterAlura.Model.Idioma;
import br.com.Alura.LiterAlura.Model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IRepositorioLivro extends JpaRepository<Livro, Long> {


    @Query("SELECT a FROM Livro b JOIN b.autores a")
    List<Autor> getAutorLista();

    @Query("SELECT a FROM Livro b JOIN b.autores a WHERE dataNascimento > :data")
    List<Autor> getAutorAno(int data);


    List<Livro> findByIdioma(Idioma idioma);



}
