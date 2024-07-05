package br.com.Alura.LiterAlura.Service;

public interface IConverteDados {


    <T> T  obterDados(String json, Class<T> tclass);
}



