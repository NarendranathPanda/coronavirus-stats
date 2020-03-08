package io.n4b.csv;

public class CsVObject implements Comparable {

    String columnOne;
    String columnTwo;
    //coronoa virus
    int columnFour;
    int columnThree;

    public String getColumnOne() {
        return columnOne;
    }

    public void setColumnOne(String columnOne) {
        this.columnOne = columnOne;
    }

    public String getColumnTwo() {
        return columnTwo;
    }

    public void setColumnTwo(String columnTwo) {
        this.columnTwo = columnTwo;
    }

    public int getColumnThree() {
        return columnThree;
    }

    public void setColumnThree(int columnThree) {
        this.columnThree = columnThree;
    }


    public int getColumnFour() {
        return columnFour;
    }

    public void setColumnFour(int columnFour) {
        this.columnFour = columnFour;
    }

    @Override
    public String toString() {
        return "CsVObject{" +
                "columnOne='" + columnOne + '\'' +
                ", columnTwo='" + columnTwo + '\'' +
                ", columnThree='" + columnThree + '\'' +
                ", columnFour='" + columnFour + '\'' +
                '}';
    }

    @Override
    public int compareTo(Object o) {
        return Integer.compare(getColumnFour(), ((CsVObject) o).getColumnFour());
    }
}
