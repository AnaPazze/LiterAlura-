package br.com.Alura.LiterAlura.Model;


import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosLivro(
        @JsonAlias("title") String título,
        @JsonAlias("authors") List<DadosAutor> autores,
        @JsonAlias("languages") List <String> idioma,
        @JsonAlias("download_count") Double downloads


        ) {
}
