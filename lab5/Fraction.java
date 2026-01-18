package lab5;

public class Fraction implements FractionInterface {

    protected int numerator;
    protected int denominator;

    public Fraction(int numerator, int denominator) {
        setNumerator(numerator);
        setDenominator(denominator);
    }

    @Override
    public double getValue() {
        return (double) numerator / denominator;
    }

    @Override
    public void setNumerator(int numerator) {
        this.numerator = numerator;
        normalize();
    }

    @Override
    public void setDenominator(int denominator) {
        if (denominator == 0) {
            throw new IllegalArgumentException("Знаменатель не должен быть равен 0.");
        }
        this.denominator = denominator;
        normalize();
    }

    protected void normalize() {
        if (denominator < 0) {
            denominator = -denominator;
            numerator = -numerator;
        }
    }

    @Override
    public String toString() {
        return numerator + "/" + denominator;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Fraction other)) {
            return false;
        }
        return this.numerator == other.numerator
                && this.denominator == other.denominator;
    }

    @Override
    public int hashCode() {
        int result = numerator;
        result = 31 * result + denominator;
        return result;
    }
}
