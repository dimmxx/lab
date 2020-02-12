package com.example.demo;

public class CellPosition {

    private int row;
    private int column;

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    @Override
    public String toString() {
        return "CellPosition{" +
                "row=" + row +
                ", column=" + column +
                '}';
    }
}
