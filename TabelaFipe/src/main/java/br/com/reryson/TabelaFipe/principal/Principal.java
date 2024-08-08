package br.com.reryson.TabelaFipe.principal;

import br.com.reryson.TabelaFipe.service.ConsumoAPI;

import java.net.URL;
import java.util.Scanner;

public class Principal {
    Scanner leitura = new Scanner(System.in);
    ConsumoAPI consumo = new ConsumoAPI();
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
            endereco = URL_BASE + "carros/marcas";
        } else if (escolha.toLowerCase().contains("mot")) {
            endereco = URL_BASE + "motos/marcas";
        }else {
            endereco = URL_BASE + "caminhoes/marcas";
        }

        var json = consumo.obterDados(endereco);
        System.out.println(json);
    }
}
