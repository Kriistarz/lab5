package lab5;

public class MeowCounter implements Meowable {

    private final Meowable original;
    private int count = 0;

    public MeowCounter(Meowable original) {
        this.original = original;
    }

    @Override
    public void meow() {
        count++;
        original.meow();
    }

    public int getCount() {
        return count;
    }

    @Override
    public String toString() {
        return original.toString() + " (мяукал " + count + " раз)";
    }
}
