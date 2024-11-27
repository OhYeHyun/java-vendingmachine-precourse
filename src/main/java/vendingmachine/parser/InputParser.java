package vendingmachine.parser;

import java.util.Arrays;

public class InputParser {

    public static int parseToNumber(String input) {
        return Integer.parseInt(input);
    }

    public static String[] parseProducts(String products) {
        return Arrays.stream(products.split(";", -1)).map(product -> product.substring(1, product.length() -1)).toArray(String[]::new);
    }

    public static String parseNameByProduct(String product) {
        return product.split(",", -1)[0];
    }

    public static int parsePriceByProduct(String product) {
        return Integer.parseInt(product.split(",", -1)[1]);
    }

    public static int parseQuantityByProduct(String product) {
        return Integer.parseInt(product.split(",", -1)[2]);
    }
}
