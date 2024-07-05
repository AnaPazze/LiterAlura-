package br.com.Alura.LiterAlura.Model;


import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
@Table(name = "livros")
public class Livro {

    ;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String título;

    @Enumerated(EnumType.STRING)
    private Idioma idioma;


    @OneToMany(mappedBy = "livro", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Autor> autores;
    private Double downloads;


    public Livro(String título, List<String >idioma, List<DadosAutor> autores,Double downloads){

        this.título= título;
        this.idioma = Idioma.fromString(idioma.get(0));
        this.downloads = downloads;
        this.autores = new ArrayList<>();
        for(DadosAutor autorInfo : autores){
            Autor autor = new Autor(autorInfo.nome(),
                    autorInfo.dataNascimento(), autorInfo.dataFalecimento(), this);
            this.autores.add(autor);

        }

             }

             public Livro(){}

    public Livro(List<DadosLivro> results) {
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTítulo() {
        return título;
    }

    public void setTítulo(String título) {
        this.título = título;
    }

    public Idioma getIdioma() {
        return idioma;
    }

    public void setIdioma(Idioma idioma) {
        this.idioma = idioma;
    }

    public List<Autor> getAutores() {
        return autores;
    }

    public void setAutores(List<Autor> autores) {
        this.autores = autores;
    }

    public Double getDownloads() {
        return downloads;
    }

    public void setDownloads(Double downloads) {
        this.downloads = downloads;
    }


    @Override
    public String toString() {
        return
                "título='" + título + '\'' +
                ", idioma=" + idioma +
                ", autores=" + autores +
                ", downloads=" + downloads
               ;
    }
}



