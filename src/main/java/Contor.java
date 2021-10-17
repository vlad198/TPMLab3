import lombok.Data;

//@Data
public class Contor {
    private int value = 0;

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Contor{" +
                "value=" + value +
                '}';
    }
}
