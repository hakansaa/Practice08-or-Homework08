package homework08;

// Базовий клас "Коло"
class Circle {
    private double radius;

    public Circle(double radius) {
        setRadius(radius);
    }

    public void setRadius(double radius) {
        if (radius <= 0) {
            System.out.println("⚠️ Попередження (Circle): Радіус повинен бути > 0! Встановлено значення за замовчуванням: 1.0");
            this.radius = 1.0;
        } else {
            this.radius = radius;
        }
    }

    public double getRadius() {
        return radius;
    }

    // Площа круга: S = π * r²
    public double getArea() {
        return Math.PI * radius * radius;
    }

    // Довжина кола: C = 2 * π * r
    public double getCircumference() {
        return 2 * Math.PI * radius;
    }

    @Override
    public String toString() {
        return String.format("Circle[r=%.2f, S=%.2f, C=%.2f]", radius, getArea(), getCircumference());
    }
}

// Клас-нащадок "Циліндр"
class Cylinder extends Circle {
    private double height;

    public Cylinder(double radius, double height) {
        super(radius); // Виклик конструктора Circle
        setHeight(height);
    }

    public void setHeight(double height) {
        if (height <= 0) {
            System.out.println("⚠️ Попередження (Cylinder): Висота повинна бути > 0! Встановлено значення за замовчуванням: 1.0");
            this.height = 1.0;
        } else {
            this.height = height;
        }
    }

    public double getHeight() {
        return height;
    }

    // Об'єм циліндра: V = S_осн * h
    public double getVolume() {
        return super.getArea() * height;
    }

    // Площа повної поверхні: S_повн = 2 * S_осн + C_осн * h
    public double getTotalSurfaceArea() {
        return 2 * super.getArea() + super.getCircumference() * height;
    }

    @Override
    public String toString() {
        return String.format("Cylinder[base=%s, h=%.2f, V=%.2f, TotalArea=%.2f]",
                super.toString(), height, getVolume(), getTotalSurfaceArea());
    }
}

// Клас-нащадок "Конус"
class Cone extends Circle {
    private double height;

    public Cone(double radius, double height) {
        super(radius); // Виклик конструктора Circle
        setHeight(height);
    }

    public void setHeight(double height) {
        if (height <= 0) {
            System.out.println("⚠️ Попередження (Cone): Висота повинна бути > 0! Встановлено значення за замовчуванням: 1.0");
            this.height = 1.0;
        } else {
            this.height = height;
        }
    }

    public double getHeight() {
        return height;
    }

    // Твірна конуса за теоремою Піфагора: l = √(r² + h²)
    public double getSlantHeight() {
        double r = getRadius();
        return Math.sqrt(r * r + height * height);
    }

    // Об'єм конуса: V = (1/3) * S_осн * h
    public double getVolume() {
        return (1.0 / 3.0) * super.getArea() * height;
    }

    // Площа повної поверхні: S_повн = S_осн + π * r * l
    public double getTotalSurfaceArea() {
        return super.getArea() + Math.PI * getRadius() * getSlantHeight();
    }

    @Override
    public String toString() {
        return String.format("Cone[base=%s, h=%.2f, l=%.2f, V=%.2f, TotalArea=%.2f]",
                super.toString(), height, getSlantHeight(), getVolume(), getTotalSurfaceArea());
    }
}

// Головний клас для запуску Завдання 1
public class Task1GeometryInheritance {
    public static void main(String[] args) {
        System.out.println("================ ГЕОМЕТРІЯ ТІЛ ОБЕРТАННЯ ================");

        Circle circle = new Circle(5.0);
        System.out.println("Базове коло:");
        System.out.println(circle);

        System.out.println("\nЦиліндр (успадковано від Circle):");
        Cylinder cylinder = new Cylinder(5.0, 10.0);
        System.out.println(cylinder);

        System.out.println("\nКонус (успадковано від Circle):");
        Cone cone = new Cone(3.0, 4.0);
        System.out.println(cone);

        System.out.println("=========================================================");
    }
}