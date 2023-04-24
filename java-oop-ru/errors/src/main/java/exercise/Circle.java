package exercise;

class Circle {

    private final Point point;

    private final int radius;

    public Circle(Point point, int radius) {
        this.point = point;
        this.radius = radius;
    }

    public int getRadius() {
        return radius;
    }

    public int getSquare() throws NegativeRadiusException {
        if (!(radius < 0)) {
            return (int) Math.round(Math.PI * radius * radius);
        }
        throw new NegativeRadiusException("");
    }
}
