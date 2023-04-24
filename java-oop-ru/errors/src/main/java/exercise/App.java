package exercise;

public class App {
    public static void printSquare(Circle circle) throws NegativeRadiusException {

        if (circle.getRadius() < 0) {
            try {
                throw new NegativeRadiusException("");
            } catch (NegativeRadiusException e) {
                System.out.println("Не удалось посчитать площадь\nВычисление окончено");
            }
        } else {
                System.out.println(circle.getSquare() + "\nВычисление окончено");
            }
        }

    public static void main(String[] args) throws NegativeRadiusException {
        Circle circle = new Circle(new Point(1, 2), -1);
        App.printSquare(circle);
    }
}
