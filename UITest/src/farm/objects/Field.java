package farm.objects;

public class Field {
    private Plot[][] plots;
    private int columns;
    private int rows;

    public Field(int columns, int rows) {
        this.columns = columns;
        this.rows = rows;
        plots = new Plot[columns][rows];
        for (int i = 0; i < columns; i++) {
            for (int j = 0; j < rows; j++) {
                plots[i][j] = new Plot();
            }
        }
    }

    public Plot getPlot(int column, int row) {
        return plots[column][row];
    }

    public int getColumns() {
        return columns;
    }

    public int getRows() {
        return rows;
    }
}
