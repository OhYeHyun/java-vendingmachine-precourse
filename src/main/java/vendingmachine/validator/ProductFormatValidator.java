package vendingmachine.validator;

import vendingmachine.errorMessage.FormatErrorMessage;

public class ProductFormatValidator {
    private static final String PRICE_LABEL = "가격";
    private static final String QUANTITY_LABEL = "수량";

    private ProductFormatValidator() {}

    public static void validate(String productInput) {
        String[] products = productInput.split(";", -1);
        for (String product : products) {
            validateProductDelimiter(product);
            validateAttributeDelimiter(product);
            validatePrice(product);
            validateQuantity(product);
        }
    }

    private static void validateProductDelimiter(String product) {
        if (!product.startsWith("[") || !product.endsWith("]")) {
            throw new IllegalArgumentException(FormatErrorMessage.NOT_ALLOWED_PRODUCT_FORMAT.getMessage());
        }
    }

    private static void validateAttributeDelimiter(String product) {
        if (product.substring(1, product.length() - 1).split(",", -1).length != 3) {
            throw new IllegalArgumentException(FormatErrorMessage.NOT_ALLOWED_ATTRIBUTE_FORMAT.getMessage());
        }
    }

    private static void validatePrice(String product) {
        String price = product.substring(1, product.length() - 1).split(",", -1)[1];
        try {
            Integer.parseInt(price);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(FormatErrorMessage.MUST_BE_NUMERIC.getMessage(PRICE_LABEL));
        }
    }

    private static void validateQuantity(String product) {
        String quantity = product.substring(1, product.length() - 1).split(",", -1)[2];
        try {
            Integer.parseInt(quantity);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(FormatErrorMessage.MUST_BE_NUMERIC.getMessage(QUANTITY_LABEL));
        }
    }
}
