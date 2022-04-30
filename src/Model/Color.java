package Model;

public enum Color {
    BLANC,
    NOIR;

    public Color next(){
        Color[] values = Color.values();
        return values[(this.ordinal() + 1) % values.length];
    }

}
