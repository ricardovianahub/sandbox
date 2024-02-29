package net.ricardoviana.maprouter;

public class Poi {
    private final int row;
    private final int column;
    private final String identifier;

    public Poi(String identifier, int row, int column) {
        this.identifier = identifier;
        this.row = row;
        this.column = column;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public String getIdentifier() {
        return identifier;
    }

    @Override
    public String toString() {
        return "Poi{" +
                "label='" + identifier + '\'' +
                ", row=" + row +
                ", column=" + column +
                '}';
    }
}
