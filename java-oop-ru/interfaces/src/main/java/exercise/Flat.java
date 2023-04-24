package exercise;

class Flat implements Home {
    private double area;
    private double balconyArea;
    private int floor;


    public Flat(double area, double balconyArea, int floor) {
        this.area = area;
        this.balconyArea = balconyArea;
        this.floor = floor;
    }

    public double getBalconyArea() {
        return balconyArea;
    }

    public int getFloor() {
        return floor;
    }

    public String toString() {
        return "Квартира площадью " + getArea() + " метров на " + getFloor() + " этаже";
    }

    @Override
    public double getArea() {
        return area + balconyArea;
    }

    @Override
    public int compareTo(Home another) {
        if(another.getArea() > getArea()) {
            return -1;
        }
        if(another.getArea() == getArea()) {
            return 0;
        }
        return 1;
    }
}
