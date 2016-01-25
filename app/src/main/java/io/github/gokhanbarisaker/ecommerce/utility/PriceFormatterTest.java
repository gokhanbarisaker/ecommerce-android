package io.github.gokhanbarisaker.ecommerce.utility;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by gokhanbarisaker on 1/24/16.
 */
public class PriceFormatterTest {
    PriceFormatter formatter;

    @Before
    public void before() {
        formatter = new PriceFormatter();
    }

    @Test
    public void testWhole() {
        Double amount = 12.;

        String textExpected = "12.00";
        String textActual = formatter.apply(amount);

        assertThat(textActual, is(equalTo(textExpected)));
    }

    @Test
    public void testFraction1() {
        Double amount = 291.9;

        String textExpected = "291.90";
        String textActual = formatter.apply(amount);

        assertThat(textActual, is(equalTo(textExpected)));
    }

    @Test
    public void testFraction2() {
        Double amount = .24;

        String textExpected = "0.24";
        String textActual = formatter.apply(amount);

        assertThat(textActual, is(equalTo(textExpected)));
    }

    @Test
    public void testFraction3() {
        Double amount = 10.845;

        String textExpected = "10.85";
        String textActual = formatter.apply(amount);

        assertThat(textActual, is(equalTo(textExpected)));
    }
}
