package parser;

import expenditure.ExpenditureCalculator;
import org.junit.Assert;
import org.junit.Test;
import transaction.Transaction;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pp00344204 on 06/06/17.
 */
public class ExpenditureCalculatorTest {

    @Test
    public void findPrice() {
        String raw = "PAYTM MOBILE SOLUT INR www.paytm.in 33.00";
        ExpenditureCalculator finder = ExpenditureCalculator.getCalculator();
        float prices = finder.findExpenditure(raw);

        float actual = 33.00f;

        Assert.assertEquals(actual, prices, 0);
    }

    @Test
    public void findPriceofCommaValue() {
        String raw = "PAYTM MOBILE SOLUT INR www.paytm.in 33,3.00";
        ExpenditureCalculator finder = ExpenditureCalculator.getCalculator();
        float prices = finder.findExpenditure(raw);

        float actual = 333.00f;

        Assert.assertEquals(actual, prices, 0);
    }

    @Test
    public void find_price_negative() {
        String raw = "PAYTM MOBILE SOLUT INR www.paytm.in";
        ExpenditureCalculator finder = ExpenditureCalculator.getCalculator();
        float prices = finder.findExpenditure(raw);

        float actual = -1;

        Assert.assertEquals(actual, prices, 0);
    }

    @SuppressWarnings("unchecked")
    @Test
    public void findPrices() {
        String raw = "05/05/2017 www.vodafone.in        MUMBAI 356.50  \n" +
                "05/05/2017 PAYTM MOBILE SOLUT INR www.paytm.in 33.00  ";
        ExpenditureCalculator finder = ExpenditureCalculator.getCalculator();
        List<Transaction> prices = finder.findExpenditures(raw);

        List<Transaction> actual = new ArrayList();
        actual.add(new Transaction(356.50f));
        actual.add(new Transaction(33.00f));

        Assert.assertEquals(actual, prices);
    }
    @SuppressWarnings("unchecked")
    @Test
    public void find_prices_with_string_having_no_price() {
        String raw = "05/05/2017 www.vodafone.in        MUMBAI  \n" +
                "05/05/2017 PAYTM MOBILE SOLUT INR www.paytm.in 33.00  ";
        ExpenditureCalculator finder = ExpenditureCalculator.getCalculator();
        List<Transaction> prices = finder.findExpenditures(raw);

        List<Transaction> actual = new ArrayList();
        actual.add(new Transaction(33.00f));
        Assert.assertEquals(actual, prices);
    }

    @SuppressWarnings("unchecked")
    @Test
    public void find_prices_with_string_having_int() {
        String raw = "05/05/2017 www.vodafone.in        MUMBAI  43\n" +
                "05/05/2017 PAYTM MOBILE SOLUT INR www.paytm.in 33.00  ";
        ExpenditureCalculator finder = ExpenditureCalculator.getCalculator();
        List<Transaction> prices = finder.findExpenditures(raw);

        List<Transaction> actual = new ArrayList();
        actual.add(new Transaction(33.00f));
        Assert.assertEquals(actual, prices);
    }

    @Test
    public void findTotalPrice() {
        String raw = "05/05/2017 www.vodafone.in        MUMBAI 356.50  \n" +
                "05/05/2017 PAYTM MOBILE SOLUT INR www.paytm.in 33.00  ";
        ExpenditureCalculator finder = ExpenditureCalculator.getCalculator();
        double result = finder.calculateTotalExpenditure(raw);

        double actual = 389.50f;

        Assert.assertEquals(actual, result, 0.0d);
    }

}
