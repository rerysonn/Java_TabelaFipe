package br.com.reryson.TabelaFipe.service;

public interface IConverteDados {
    <T> T obterDados(String json,Class<T> classe);
}
