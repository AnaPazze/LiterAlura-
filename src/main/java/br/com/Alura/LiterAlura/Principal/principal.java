package br.com.Alura.LiterAlura.Principal;

import br.com.Alura.LiterAlura.Model.*;
import br.com.Alura.LiterAlura.Repository.IRepositorioLivro;
import br.com.Alura.LiterAlura.Service.ConsumoApi;
import br.com.Alura.LiterAlura.Service.ConverteDados;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class principal {

    private Scanner leitura = new Scanner(System.in);
    private ConsumoApi consumo = new ConsumoApi();
    private ConverteDados conversor = new ConverteDados();
    private final String ENDERECO = "https://gutendex.com/books/";
    private String livroEscolhido;
    private List<Livro> livros;


    private IRepositorioLivro repositorio;

    public principal(IRepositorioLivro repositorio){
        this.repositorio = repositorio;
    }

    public void Menu() {
        var option = -1;
        while (option != 0) {
            var menu = """
                    1 - Buscar livro por título
                    2 - Listar todos os livros 
                    3 - Listar todos os autores 
                    4 - Listar autores vivos em determinado ano
                    5 - Listar livros por idioma
                    0 - Sair
                    """;
            System.out.println(menu);
            option = leitura.nextInt();
            leitura.nextLine();

            switch (option) {
                case 1:
                    buscarLivroWeb();
                    break;
                case 2:
                    listarTodosLivros();
                    break;
                case 3:
                    listarTodosAutores();
                    break;
                case 4:
                    buscarAutoresAno();
                    break;
                case 5:
                    buscarLivrosPorIdioma();
                    break;

                    case 0:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida");

            }
        }

    }

    private String getDataUsuario() {
        System.out.println("Digite um nome de um livro para buscar");
         livroEscolhido = leitura.nextLine();
         return livroEscolhido;
    }

    private Data getDataApi(String livroTitulo) {
        var json = consumo.obterDados(ENDERECO + "?search=%20" + livroTitulo.replace(" ", "+"));
        var data = conversor.obterDados(json, Data.class);

        return data;

    }

    private Optional<Livro> getBookInfo(Data livroData, String livroTitulo) {

        Optional<Livro> livros = livroData.results().stream()
                .filter(l -> l.título().toLowerCase().contains(livroTitulo.toLowerCase()))
                .map(b -> new Livro(b.título(), b.idioma(), b.autores(), b.downloads()))
                .findFirst();
        return livros;
    }




    private void buscarLivroWeb() {
        String titulo = getDataUsuario();
        Data data = getDataApi(titulo);
        Livro livro = new Livro(data.results());
        repositorio.save(livro);
        System.out.println(livro);
        

    }

    private Optional<Livro> getLivroData () {
        String livroTitulo = getDataUsuario();
        Data bookInfo = getDataApi(livroTitulo);
        Optional<Livro> livro = getBookInfo(bookInfo, livroTitulo);

        if ( livro.isPresent() ) {
            var b = livro.get();
            repositorio.save(b);
            System.out.println(b);
        } else {
            System.out.println("\nLivro nao encontrado\n");
        }

        return livro;
    }


    private void listarTodosLivros() {
        List<Livro> livro = repositorio.findAll();
        livro.stream()
                .sorted(Comparator.comparing(Livro::getTítulo))
                .forEach(System.out::println);


    }

    //Lista de todos os autores registrados

    private void listarTodosAutores() {
        List<Autor> autores = repositorio.getAutorLista();

        autores.stream()
                .sorted(Comparator.comparing(Autor::getNome))
                .forEach(a -> System.out.printf( "Autor : %s, Nasceu: %d, Morreu: %d",
                        a.getNome(), a.getDataNascimento(), a.getDataFalecimento()));

    }


    //Listar autores vivos em determinado ano

    private void buscarAutoresAno() {
        System.out.println("Digite um ano para buscar autores vivos em determinado ano");

        int data = leitura.nextInt();
        leitura.nextLine();

        List<Autor> autorInfo = repositorio.getAutorAno(data);

        autorInfo.stream()
                .sorted(Comparator.comparing(Autor::getNome))
                .forEach(a -> System.out.printf( "Autor : %s, Nasceu: %d, Morreu: %d",
                        a.getNome(), a.getDataNascimento(), a.getDataFalecimento()));

    }

    // Encontrar livros por idioma

    private void buscarLivrosPorIdioma() {

        String listaIdioma = """
                Escolha uma das opcões de idiomas para buscar livros:
                
                en - Inglês
                es - Espanhol
                pt - Português
                
                """;
        System.out.println(listaIdioma);
        String texto =  leitura.nextLine();
        var idioma = Idioma.fromString(texto);

        List<Livro> idiomaLivro= repositorio.findByIdioma(idioma);
        idiomaLivro.stream()
                .forEach(System.out::println);


    }
}