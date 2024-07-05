package br.com.Alura.LiterAlura.Model;

public enum Idioma {

    Portugues("pt"),
    Espanhol("es"),
    Ingles("en");


    private String idiomaLiterAlura;

    Idioma(String idiomaLiterAlura){
        this.idiomaLiterAlura = idiomaLiterAlura;


    }

    public static Idioma fromString(String texto) {
        for (Idioma categoria : Idioma.values())
            if ( categoria.idiomaLiterAlura.equalsIgnoreCase(texto) ) {
                return categoria;
            }
        throw new IllegalArgumentException("Nenhuma categoria encontrada: " + texto);
    }

}
