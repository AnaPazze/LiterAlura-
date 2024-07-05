package br.com.Alura.LiterAlura.Model;


import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;
import java.util.Optional;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Data(
        @JsonAlias("results")List<DadosLivro> results


        ) {



}
