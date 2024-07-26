package Function;

public class Sport {

    private String name;

    public Sport(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Sport: " + name;
    }
}
