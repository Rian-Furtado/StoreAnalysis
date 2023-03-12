package application;

import entities.Sale;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);

        System.out.print("Entre com o caminho do arquivo: ");
        String path = sc.nextLine();

        List<Sale> saleList = new ArrayList<>();

        System.out.println();

        try (BufferedReader bf = new BufferedReader(new FileReader(path))) {

            String line = bf.readLine();

            while (line != null) {
                String[] text = line.split(",");
                saleList.add(new Sale(Integer.parseInt(text[0]),
                        Integer.parseInt(text[1]),
                        text[2], Integer.parseInt(text[3]),
                        Double.parseDouble(text[4])));

                line = bf.readLine();
            }

            System.out.println("Cinco primeiras vendas de 2016 de maior preço médio:");

            List<Sale> salesListOrdenada = saleList.stream().filter(x -> x.getYear() == 2016).sorted(Comparator.comparing(Sale::averagePrice).reversed()).toList();
            salesListOrdenada.stream().limit(5).forEach(System.out::println);

            Double totalValue = saleList.stream().filter(x -> (x.getSeller().equals("Logan")) && (x.getMoth() == 1 || x.getMoth() == 7)).map(Sale::getTotal).reduce(0.0, Double::sum);

            System.out.println();

            System.out.println("Valor total vendido pelo vendendor Logan nos meses 1 e 7 = " + totalValue);

        } catch (IOException e) {
            System.out.println("Erro: " + path + "( O sistema não pode encontrar o arquivo especificado");
        }


    }
}