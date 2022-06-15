package mvc.model;

public class Word {
    private final String value;
    private int amount;

    public Word(String value, int amount) {
        this.value = value;
        this.amount = amount;
    }

    public String getValue() {
        return value;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
