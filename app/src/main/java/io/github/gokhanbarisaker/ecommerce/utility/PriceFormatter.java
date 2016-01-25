package io.github.gokhanbarisaker.ecommerce.utility;

import java.text.DecimalFormat;

import io.github.gokhanbarisaker.ecommerce.model.Function;

/**
 * Created by gokhanbarisaker on 1/24/16.
 */
public class PriceFormatter implements Function<Double, String> {
    DecimalFormat decimalFormat = new DecimalFormat("#0.00");

    /**
     * Converts given double amount to a displayable string.
     * Extra fractions will be rounded and added to the value. e.g. 4.567 will be 4.57
     *
     * @param amount
     * @return
     */
    @Override
    public String apply(Double amount) {
        return decimalFormat.format(amount.doubleValue());
    }
}
