package br.com.reryson.TabelaFipe.principal;

import br.com.reryson.TabelaFipe.model.DadosVeiculos;
import br.com.reryson.TabelaFipe.model.Modelos;
import br.com.reryson.TabelaFipe.service.ConsumoAPI;
import br.com.reryson.TabelaFipe.service.ConverteDados;

import java.net.URL;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Principal {

    private Scanner leitura = new Scanner(System.in);
    private ConsumoAPI consumo = new ConsumoAPI();
    private ConverteDados conversor = new ConverteDados();
    private final String URL_BASE = "https://parallelum.com.br/fipe/api/v1/"; // Constante: URL que não se altera.

    public void exibeMenu(){
        var menu = """
                ----- Opções de Veículos -----
                
                Carro
                Moto
                Caminhão
                
                Digite a opcao de veiculo que deseja consultar: """;
        System.out.print(menu);
        var escolha = leitura.nextLine();
        String endereco;

        if (escolha.toLowerCase().contains("car") || escolha.toLowerCase().contains("carr")){
            endereco = URL_BASE + "carros/marcas/";
        } else if (escolha.toLowerCase().contains("mot")) {
            endereco = URL_BASE + "motos/marcas/";
        }else {
            endereco = URL_BASE + "caminhoes/marcas/";
        }

        var json = consumo.obterDados(endereco);
        System.out.println(json);

        var marcas = conversor.obterLista(json, DadosVeiculos.class);
        marcas.stream()
                .sorted(Comparator.comparing(DadosVeiculos::codigo))
                .forEach(System.out::println);

        System.out.print("Digite o código da marca que voce deseja consultar: ");
        var marcas_codigo = leitura.nextLine();

        endereco = endereco + marcas_codigo + "/modelos";
        json = consumo.obterDados(endereco);

        var modeloLista = conversor.obterDados(json, Modelos.class);

        System.out.print("Modelos para esta marca: ");
        modeloLista.modelos().stream()
                .sorted(Comparator.comparing(DadosVeiculos::codigo))
                .forEach(System.out::println);

        System.out.print("Digite um trecho do nome do veiculo para consulta: ");
        var nome_veiculo = leitura.nextLine();

        List<DadosVeiculos> modelosFiltrados = modeloLista.modelos().stream()
                .filter(m ->m.nome().toLowerCase().contains(nome_veiculo.toLowerCase()))
                    .toList();

        System.out.println("Modelos Filtrados: ");
        modelosFiltrados.forEach(System.out::println);
    }
}
