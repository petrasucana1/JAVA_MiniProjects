import java.util.Objects;

public class Concert extends Attraction implements Payable{
    private double price;

    public Concert(String town, String description, String singer, double price) {
        super(town,description,singer);
        this.price = price;
    }



    @Override
    public double getPrice() {
        return price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Concert concert = (Concert) o;
        return Double.compare(price, concert.price) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), price);
    }

    @Override
    public String toString() {
        return "Concert{" +
                "price=" + price +
                '}';
    }
}
