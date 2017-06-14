package transaction;

/**
 * Created by pp00344204 on 06/06/17.
 */
public class Transaction {
    private float price;

    public Transaction() {

    }

    public Transaction(float price) {
        this.price = price;
    }

    public float getPrice() {
        return price;
    }

// --Commented out by Inspection START (08/06/17, 10:35 PM):
//    public void setPrice(float price) {
//        this.price = price;
//    }
// --Commented out by Inspection STOP (08/06/17, 10:35 PM)

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Transaction that = (Transaction) o;

        return Float.compare(that.price, price) == 0;
    }

    @Override
    public int hashCode() {
        return (price != +0.0f ? Float.floatToIntBits(price) : 0);
    }
}
