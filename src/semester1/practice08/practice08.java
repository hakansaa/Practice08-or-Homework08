
package semester1.practice08;

import java.util.Arrays;

// ==========================================
// БЛОК 1: Інкапсуляція, валідація та toString() (1-8)
// ==========================================

class Student {
    private String name;
    private int course;
    private double gpa;

    public Student(String name, int course, double gpa) {
        setName(name);
        setCourse(course);
        setGpa(gpa);
    }

    public void setName(String name) {
        this.name = (name == null || name.isBlank()) ? "Невідомо" : name;
    }

    public void setCourse(int course) {
        if (course >= 1 && course <= 6) {
            this.course = course;
        } else {
            this.course = 1;
        }
    }

    public void setGpa(double gpa) {
        if (gpa >= 0.0 && gpa <= 100.0) {
            this.gpa = gpa;
        } else {
            this.gpa = 60.0;
        }
    }

    public String getName() { return name; }
    public int getCourse() { return course; }
    public double getGpa() { return gpa; }

    @Override
    public String toString() {
        return "Student{name='" + name + "', course=" + course + ", gpa=" + gpa + "}";
    }
}

class Product {
    private String sku;
    private String title;
    private double price;
    private int stock;

    public Product(String sku, String title, double price, int stock) {
        this.sku = sku;
        this.title = title;
        setPrice(price);
        setStock(stock);
    }

    public void setPrice(double price) {
        this.price = Math.max(0.0, price);
    }

    public void setStock(int stock) {
        this.stock = Math.max(0, stock);
    }

    public double getTotalCost() {
        return price * stock;
    }

    @Override
    public String toString() {
        return String.format("[SKU: %s] %s | Ціна: %.2f грн | Залишок: %d шт. | Разом: %.2f грн",
                sku, title, price, stock, getTotalCost());
    }
}

class Circle {
    private double radius;

    public Circle(double radius) {
        setRadius(radius);
    }

    public void setRadius(double radius) {
        if (radius > 0) {
            this.radius = radius;
        } else {
            this.radius = 1.0;
        }
    }

    public double getRadius() { return radius; }
    public double getArea() { return Math.PI * radius * radius; }
    public double getCircumference() { return 2 * Math.PI * radius; }

    @Override
    public String toString() {
        return String.format("Circle{radius=%.2f, area=%.2f, circumference=%.2f}", radius, getArea(), getCircumference());
    }
}

class CardWallet {
    private String cardNumber;
    private String pinCode;
    private double balance;

    public CardWallet(String cardNumber, String pinCode, double balance) {
        this.cardNumber = (cardNumber != null && cardNumber.length() == 16) ? cardNumber : "0000000000000000";
        this.pinCode = (pinCode != null) ? pinCode : "1234";
        this.balance = Math.max(0.0, balance);
    }

    public boolean changePin(String oldPin, String newPin) {
        if (this.pinCode.equals(oldPin)) {
            this.pinCode = newPin;
            return true;
        }
        return false;
    }

    public double getBalance() { return balance; }

    @Override
    public String toString() {
        String masked = "**** **** **** " + cardNumber.substring(Math.max(0, cardNumber.length() - 4));
        return "CardWallet{card='" + masked + "', balance=" + String.format("%.2f", balance) + " UAH}";
    }
}

class SensorNode {
    private String sensorId;
    protected double calibrationOffset;
    private double rawValue;

    public SensorNode(String sensorId, double calibrationOffset, double rawValue) {
        this.sensorId = sensorId;
        this.calibrationOffset = calibrationOffset;
        this.rawValue = rawValue;
    }

    public String getSensorId() { return sensorId; }
    public double getCalibratedValue() { return rawValue + calibrationOffset; }

    @Override
    public String toString() {
        return "Sensor{id='" + sensorId + "', raw=" + rawValue + ", calibrated=" + getCalibratedValue() + "}";
    }
}

class WeatherDay {
    private String date;
    private double[] hourlyTemps;

    public WeatherDay(String date, double[] hourlyTemps) {
        this.date = date;
        if (hourlyTemps != null) {
            this.hourlyTemps = Arrays.copyOf(hourlyTemps, hourlyTemps.length);
        } else {
            this.hourlyTemps = new double[0];
        }
    }

    public double getMinTemp() {
        if (hourlyTemps.length == 0) return 0.0;
        double min = hourlyTemps[0];
        for (double t : hourlyTemps) if (t < min) min = t;
        return min;
    }

    public double getMaxTemp() {
        if (hourlyTemps.length == 0) return 0.0;
        double max = hourlyTemps[0];
        for (double t : hourlyTemps) if (t > max) max = t;
        return max;
    }

    public double getAverageTemp() {
        if (hourlyTemps.length == 0) return 0.0;
        double sum = 0;
        for (double t : hourlyTemps) sum += t;
        return sum / hourlyTemps.length;
    }

    @Override
    public String toString() {
        return "Weather{" + date + ", temps=" + Arrays.toString(hourlyTemps) + ", avg=" + String.format("%.1f", getAverageTemp()) + "}";
    }
}

class IntRange {
    private int min;
    private int max;

    public IntRange(int min, int max) {
        setRange(min, max);
    }

    public void setRange(int min, int max) {
        if (min <= max) {
            this.min = min;
            this.max = max;
        } else {
            this.min = max;
            this.max = min;
        }
    }

    public boolean contains(int value) {
        return value >= min && value <= max;
    }

    public int getLength() {
        return max - min;
    }

    @Override
    public String toString() {
        return "[" + min + " .. " + max + "] (довжина: " + getLength() + ")";
    }
}

class InvoiceItem {
    private String description;
    private int quantity;
    private double unitPrice;
    private double discountPercent;

    public InvoiceItem(String description, int quantity, double unitPrice, double discountPercent) {
        this.description = description;
        this.quantity = Math.max(1, quantity);
        this.unitPrice = Math.max(0.0, unitPrice);
        this.discountPercent = Math.min(50.0, Math.max(0.0, discountPercent));
    }

    public double getFinalAmount() {
        double total = quantity * unitPrice;
        return total * (1 - discountPercent / 100.0);
    }

    @Override
    public String toString() {
        return String.format("InvoiceItem{desc='%s', qty=%d, final=%.2f грн}", description, quantity, getFinalAmount());
    }
}


// ==========================================
// БЛОК 2: Однорівневе наслідування та super (9-16)
// ==========================================

class Employee {
    private String name;
    protected double salary;

    public Employee(String name, double salary) {
        this.name = (name != null) ? name : "Noname";
        this.salary = Math.max(0.0, salary);
    }

    public String getName() { return name; }
    public double getSalary() { return salary; }

    @Override
    public String toString() {
        return "Employee{name='" + name + "', salary=" + salary + "}";
    }
}

class Developer extends Employee {
    private String programmingLanguage;

    public Developer(String name, double salary, String programmingLanguage) {
        super(name, salary);
        this.programmingLanguage = programmingLanguage;
    }

    @Override
    public String toString() {
        return "Developer{" + super.toString() + ", lang='" + programmingLanguage + "'}";
    }
}

class Animal {
    private String name;
    private int age;
    private double weight;

    public Animal(String name, int age, double weight) {
        this.name = name;
        this.age = age;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Animal{name='" + name + "', age=" + age + ", weight=" + weight + "}";
    }
}

class Dog extends Animal {
    private String breed;

    public Dog(String name, int age, double weight, String breed) {
        super(name, age, weight);
        this.breed = breed;
    }

    public String fetchStick() { return "Собака принесла палку!"; }

    @Override
    public String toString() {
        return "Dog{" + super.toString() + ", breed='" + breed + "'}";
    }
}

class Cat extends Animal {
    private boolean isIndoor;

    public Cat(String name, int age, double weight, boolean isIndoor) {
        super(name, age, weight);
        this.isIndoor = isIndoor;
    }

    public String purr() { return "Мур-мур..."; }

    @Override
    public String toString() {
        return "Cat{" + super.toString() + ", indoor=" + isIndoor + "}";
    }
}

class Vehicle {
    protected String brand;
    protected int maxSpeed;
    protected double fuelTankCapacity;

    public Vehicle(String brand, int maxSpeed, double fuelTankCapacity) {
        this.brand = brand;
        this.maxSpeed = maxSpeed;
        this.fuelTankCapacity = fuelTankCapacity;
    }

    @Override
    public String toString() {
        return "Vehicle{brand='" + brand + "', maxSpeed=" + maxSpeed + "}";
    }
}

class PassengerCar extends Vehicle {
    private int passengerSeats;

    public PassengerCar(String brand, int maxSpeed, double fuelTankCapacity, int passengerSeats) {
        super(brand, maxSpeed, fuelTankCapacity);
        this.passengerSeats = passengerSeats;
    }

    @Override
    public String toString() {
        return "PassengerCar{" + super.toString() + ", seats=" + passengerSeats + "}";
    }
}

class CargoTruck extends Vehicle {
    private double maxLoadTons;

    public CargoTruck(String brand, int maxSpeed, double fuelTankCapacity, double maxLoadTons) {
        super(brand, maxSpeed, fuelTankCapacity);
        this.maxLoadTons = maxLoadTons;
    }

    @Override
    public String toString() {
        return "CargoTruck{" + super.toString() + ", maxLoad=" + maxLoadTons + "t}";
    }
}

class Rectangle {
    private double width;
    private double height;

    public Rectangle(double width, double height) {
        this.width = Math.max(0, width);
        this.height = Math.max(0, height);
    }

    public double getWidth() { return width; }
    public double getHeight() { return height; }
    public double getArea() { return width * height; }

    @Override
    public String toString() {
        return "Rectangle{w=" + width + ", h=" + height + "}";
    }
}

class Box3D extends Rectangle {
    private double depth;

    public Box3D(double width, double height, double depth) {
        super(width, height);
        this.depth = Math.max(0, depth);
    }

    public double getVolume() {
        return super.getArea() * depth;
    }

    @Override
    public String toString() {
        return "Box3D{" + super.toString() + ", depth=" + depth + ", vol=" + getVolume() + "}";
    }
}

class Person {
    private String fullName;
    private int birthYear;

    public Person(String fullName, int birthYear) {
        this.fullName = fullName;
        this.birthYear = birthYear;
    }

    public int getAge(int currentYear) {
        return currentYear - birthYear;
    }

    @Override
    public String toString() {
        return "Person{name='" + fullName + "', birth=" + birthYear + "}";
    }
}

class UniversityStudent extends Person {
    private String studentId;
    private String major;
    private int scholarship;

    public UniversityStudent(String fullName, int birthYear, String studentId, String major, int scholarship) {
        super(fullName, birthYear);
        this.studentId = studentId;
        this.major = major;
        this.scholarship = scholarship;
    }

    @Override
    public String toString() {
        return "Student{" + super.toString() + ", id='" + studentId + "', major='" + major + "'}";
    }
}

class Professor extends Person {
    private String academicTitle;
    private int publicationsCount;

    public Professor(String fullName, int birthYear, String academicTitle, int publicationsCount) {
        super(fullName, birthYear);
        this.academicTitle = academicTitle;
        this.publicationsCount = publicationsCount;
    }

    @Override
    public String toString() {
        return "Professor{" + super.toString() + ", title='" + academicTitle + "'}";
    }
}

class Gadget {
    private String brand;
    private double price;

    public Gadget(String brand, double price) {
        this.brand = brand;
        this.price = price;
    }

    @Override
    public String toString() {
        return "Gadget{brand='" + brand + "', price=" + price + "}";
    }
}

class Smartphone extends Gadget {
    private int memoryGb;
    private boolean has5G;

    public Smartphone(String brand, double price, int memoryGb, boolean has5G) {
        super(brand, price);
        this.memoryGb = memoryGb;
        this.has5G = has5G;
    }

    public Smartphone(String brand, double price, int memoryGb) {
        this(brand, price, memoryGb, true);
    }

    public Smartphone(String brand) {
        this(brand, 9999.0, 128);
    }

    @Override
    public String toString() {
        return "Smartphone{" + super.toString() + ", memory=" + memoryGb + "GB, 5G=" + has5G + "}";
    }
}

class Publication {
    private String title;
    private String author;
    private int releaseYear;

    public Publication(String title, String author, int releaseYear) {
        this.title = title;
        this.author = author;
        this.releaseYear = releaseYear;
    }

    @Override
    public String toString() {
        return "Publication{title='" + title + "', author='" + author + "'}";
    }
}

class PrintedBook extends Publication {
    private int pageCount;
    private String coverType;

    public PrintedBook(String title, String author, int releaseYear, int pageCount, String coverType) {
        super(title, author, releaseYear);
        this.pageCount = pageCount;
        this.coverType = coverType;
    }

    @Override
    public String toString() {
        return "PrintedBook{" + super.toString() + ", pages=" + pageCount + "}";
    }
}

class AudioBook extends Publication {
    private int durationMinutes;
    private String narrator;

    public AudioBook(String title, String author, int releaseYear, int durationMinutes, String narrator) {
        super(title, author, releaseYear);
        this.durationMinutes = durationMinutes;
        this.narrator = narrator;
    }

    public String getFormattedDuration() {
        int hours = durationMinutes / 60;
        int mins = durationMinutes % 60;
        return hours + " год " + mins + " хв";
    }

    @Override
    public String toString() {
        return "AudioBook{" + super.toString() + ", duration=" + getFormattedDuration() + "}";
    }
}

class Gamer {
    private String nickname;
    private int hoursPlayed;

    public Gamer(String nickname, int hoursPlayed) {
        this.nickname = nickname;
        this.hoursPlayed = hoursPlayed;
    }

    @Override
    public String toString() {
        return "Gamer{nick='" + nickname + "'}";
    }
}

class ProEsportsPlayer extends Gamer {
    private String teamName;
    private double prizeMoneyUSD;
    private int tournamentsWon;

    public ProEsportsPlayer(String nickname, int hoursPlayed, String teamName, double prizeMoneyUSD, int tournamentsWon) {
        super(nickname, hoursPlayed);
        this.teamName = teamName;
        this.prizeMoneyUSD = prizeMoneyUSD;
        this.tournamentsWon = tournamentsWon;
    }

    public double getAveragePrizePerWin() {
        if (tournamentsWon == 0) return 0.0;
        return prizeMoneyUSD / tournamentsWon;
    }

    @Override
    public String toString() {
        return "ProPlayer{" + super.toString() + ", team='" + teamName + "', avgPrize=" + getAveragePrizePerWin() + "}";
    }
}


// ==========================================
// БЛОК 3: Перевизначення методів @Override (17-24)
// ==========================================

class StaffMember {
    protected String name;
    protected double baseSalary;

    public StaffMember(String name, double baseSalary) {
        this.name = name;
        this.baseSalary = baseSalary;
    }

    public double calculateMonthlyPay() {
        return baseSalary;
    }

    @Override
    public String toString() {
        return "Staff{name='" + name + "', pay=" + calculateMonthlyPay() + "}";
    }
}

class Manager extends StaffMember {
    private double teamBonus;

    public Manager(String name, double baseSalary, double teamBonus) {
        super(name, baseSalary);
        this.teamBonus = teamBonus;
    }

    @Override
    public double calculateMonthlyPay() {
        return super.calculateMonthlyPay() + teamBonus;
    }
}

class SalesPerson extends StaffMember {
    private double salesVolume;
    private double commissionRate;

    public SalesPerson(String name, double baseSalary, double salesVolume, double commissionRate) {
        super(name, baseSalary);
        this.salesVolume = salesVolume;
        this.commissionRate = commissionRate;
    }

    @Override
    public double calculateMonthlyPay() {
        return super.calculateMonthlyPay() + salesVolume * (commissionRate / 100.0);
    }
}

class StandardTransfer {
    protected double amount;

    public StandardTransfer(double amount) {
        this.amount = amount;
    }

    public double getFee() { return amount * 0.01; }
    public double getTotalWithFee() { return amount + getFee(); }
}

class InternationalTransfer extends StandardTransfer {
    private String countryCode;

    public InternationalTransfer(double amount, String countryCode) {
        super(amount);
        this.countryCode = countryCode;
    }

    @Override
    public double getFee() {
        return super.getFee() * 2 + 15.0;
    }
}

class Ticket {
    private String eventName;
    private double basePrice;

    public Ticket(String eventName, double basePrice) {
        this.eventName = eventName;
        this.basePrice = basePrice;
    }

    public double getFinalPrice() { return basePrice; }

    @Override
    public String toString() {
        return "Ticket{event='" + eventName + "', price=" + getFinalPrice() + "}";
    }
}

class StudentTicket extends Ticket {
    private String studentCardNumber;

    public StudentTicket(String eventName, double basePrice, String studentCardNumber) {
        super(eventName, basePrice);
        this.studentCardNumber = studentCardNumber;
    }

    @Override
    public double getFinalPrice() {
        return super.getFinalPrice() * 0.5;
    }
}

class VipTicket extends Ticket {
    private boolean includesBackstage;
    private double loungeFee;

    public VipTicket(String eventName, double basePrice, boolean includesBackstage, double loungeFee) {
        super(eventName, basePrice);
        this.includesBackstage = includesBackstage;
        this.loungeFee = loungeFee;
    }

    @Override
    public double getFinalPrice() {
        return super.getFinalPrice() + loungeFee;
    }
}

class Weapon {
    private String title;
    private int baseDamage;

    public Weapon(String title, int baseDamage) {
        this.title = title;
        this.baseDamage = baseDamage;
    }

    public int calculateDamage() { return baseDamage; }

    @Override
    public String toString() {
        return "Weapon{title='" + title + "', dmg=" + calculateDamage() + "}";
    }
}

class EnchantedSword extends Weapon {
    private String element;
    private int magicPower;
    private int enchantmentLevel;

    public EnchantedSword(String title, int baseDamage, String element, int magicPower, int enchantmentLevel) {
        super(title, baseDamage);
        this.element = element;
        this.magicPower = magicPower;
        this.enchantmentLevel = enchantmentLevel;
    }

    @Override
    public int calculateDamage() {
        return super.calculateDamage() + magicPower * enchantmentLevel;
    }

    @Override
    public String toString() {
        return "EnchantedSword{" + super.toString() + ", element='" + element + "'}";
    }
}

class Parcel {
    private String trackingCode;
    private double weightKg;

    public Parcel(String trackingCode, double weightKg) {
        this.trackingCode = trackingCode;
        this.weightKg = weightKg;
    }

    public double calculateShippingCost() {
        return 50.0 + weightKg * 12.0;
    }
}

class FragileParcel extends Parcel {
    private boolean requiresWoodenBox;
    private double insuranceValue;

    public FragileParcel(String trackingCode, double weightKg, boolean requiresWoodenBox, double insuranceValue) {
        super(trackingCode, weightKg);
        this.requiresWoodenBox = requiresWoodenBox;
        this.insuranceValue = insuranceValue;
    }

    @Override
    public double calculateShippingCost() {
        double cost = super.calculateShippingCost() + (insuranceValue * 0.015);
        if (requiresWoodenBox) cost += 120.0;
        return cost;
    }
}

class Appliance {
    private String model;
    private double powerWatts;

    public Appliance(String model, double powerWatts) {
        this.model = model;
        this.powerWatts = powerWatts;
    }

    public double getDailyConsumptionKWh(int hours) {
        return (powerWatts * hours) / 1000.0;
    }
}

class EcoHeater extends Appliance {
    private boolean ecoModeActive;

    public EcoHeater(String model, double powerWatts, boolean ecoModeActive) {
        super(model, powerWatts);
        this.ecoModeActive = ecoModeActive;
    }

    @Override
    public double getDailyConsumptionKWh(int hours) {
        double base = super.getDailyConsumptionKWh(hours);
        return ecoModeActive ? base * 0.75 : base;
    }
}

class Point2D {
    private double x, y;

    public Point2D(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() { return x; }
    public double getY() { return y; }
    public double distanceToOrigin() {
        return Math.sqrt(x * x + y * y);
    }

    @Override
    public String toString() {
        return "(" + x + "; " + y + ")";
    }
}

class Point3D extends Point2D {
    private double z;

    public Point3D(double x, double y, double z) {
        super(x, y);
        this.z = z;
    }

    @Override
    public double distanceToOrigin() {
        return Math.sqrt(Math.pow(super.distanceToOrigin(), 2) + z * z);
    }

    @Override
    public String toString() {
        return "(" + getX() + "; " + getY() + "; " + z + ")";
    }
}

class Coffee {
    private String roastType;
    private int volumeMl;
    private double basePrice;

    public Coffee(String roastType, int volumeMl, double basePrice) {
        this.roastType = roastType;
        this.volumeMl = volumeMl;
        this.basePrice = basePrice;
    }

    public double getPrice() { return basePrice; }
}

class LatteWithAddons extends Coffee {
    private String milkType;
    private int syrupPumps;

    public LatteWithAddons(String roastType, int volumeMl, double basePrice, String milkType, int syrupPumps) {
        super(roastType, volumeMl, basePrice);
        this.milkType = milkType;
        this.syrupPumps = syrupPumps;
    }

    @Override
    public double getPrice() {
        double extra = 0;
        if (!milkType.equalsIgnoreCase("Звичайне")) extra += 20.0;
        extra += syrupPumps * 12.0;
        return super.getPrice() + extra;
    }
}


// ==========================================
// БЛОК 4: Трирівневе наслідування та final (25-32)
// ==========================================

class DotaHero {
    private String name;
    private String primaryAttribute;
    private int baseHp;

    public DotaHero(String name, String primaryAttribute, int baseHp) {
        this.name = name;
        this.primaryAttribute = primaryAttribute;
        this.baseHp = baseHp;
    }

    public int getMaxHp() { return baseHp; }

    @Override
    public String toString() {
        return "Hero{name='" + name + "', hp=" + getMaxHp() + "}";
    }
}

class StrengthHero extends DotaHero {
    private int strength;

    public StrengthHero(String name, int baseHp, int strength) {
        super(name, "Strength", baseHp);
        this.strength = strength;
    }

    @Override
    public int getMaxHp() {
        return super.getMaxHp() + strength * 22;
    }

    @Override
    public String toString() {
        return "Strength" + super.toString() + "";
    }
}

class AlchemistHero extends StrengthHero {
    private int greevilsGreedBonusGold;

    public AlchemistHero(String name, int baseHp, int strength, int gold) {
        super(name, baseHp, strength);
        this.greevilsGreedBonusGold = gold;
    }

    public void chemicalRage() {
        System.out.println("Alchemist активирует Chemical Rage!");
    }

    @Override
    public String toString() {
        return "Alchemist{" + super.toString() + ", goldBonus=" + greevilsGreedBonusGold + "}";
    }
}

class BaseVehicle {
    protected String brand;
    protected int year;

    public BaseVehicle(String brand, int year) {
        this.brand = brand;
        this.year = year;
    }

    @Override
    public String toString() {
        return "Base{brand='" + brand + "', year=" + year + "}";
    }
}

class MotorCar extends BaseVehicle {
    protected int horsePower;
    protected int doorsCount;

    public MotorCar(String brand, int year, int horsePower, int doorsCount) {
        super(brand, year);
        this.horsePower = horsePower;
        this.doorsCount = doorsCount;
    }

    @Override
    public String toString() {
        return "Motor{" + super.toString() + ", hp=" + horsePower + "}";
    }
}

class ElectricCar extends MotorCar {
    private double batteryCapacityKWh;
    private int rangeKm;

    public ElectricCar(String brand, int year, int horsePower, int doorsCount, double batteryCapacityKWh, int rangeKm) {
        super(brand, year, horsePower, doorsCount);
        this.batteryCapacityKWh = batteryCapacityKWh;
        this.rangeKm = rangeKm;
    }

    @Override
    public String toString() {
        return "ElectricCar{" + super.toString() + ", battery=" + batteryCapacityKWh + "kWh}";
    }
}

class PaymentCard {
    protected String cardHolder;
    protected double balance;

    public PaymentCard(String cardHolder, double balance) {
        this.cardHolder = cardHolder;
        this.balance = balance;
    }

    public boolean pay(double amount) {
        if (balance >= amount) {
            balance -= amount;
            return true;
        }
        return false;
    }
}

class DebitCard extends PaymentCard {
    protected double dailyLimit;

    public DebitCard(String cardHolder, double balance, double dailyLimit) {
        super(cardHolder, balance);
        this.dailyLimit = dailyLimit;
    }

    @Override
    public boolean pay(double amount) {
        if (amount <= dailyLimit) {
            return super.pay(amount);
        }
        return false;
    }
}

class CashbackPlatinumCard extends DebitCard {
    private double cashbackPercent;
    private double accumulatedCashback;

    public CashbackPlatinumCard(String cardHolder, double balance, double dailyLimit, double cashbackPercent) {
        super(cardHolder, balance, dailyLimit);
        this.cashbackPercent = cashbackPercent;
    }

    @Override
    public boolean pay(double amount) {
        boolean success = super.pay(amount);
        if (success) {
            accumulatedCashback += amount * (cashbackPercent / 100.0);
        }
        return success;
    }
}

class SecurityTerminal {
    public final String generateAuthHash(String user) {
        return "AUTH-SHA-" + (user != null ? user.toUpperCase() : "GUEST");
    }
}

class CustomTerminal extends SecurityTerminal {
    public String getWelcomeBanner() {
        return "Welcome to Secure System! Hash: " + generateAuthHash("admin");
    }
}

final class ImmutableServerCredentials {
    private final String host;
    private final int port;
    private final String apiKey;

    public ImmutableServerCredentials(String host, int port, String apiKey) {
        this.host = host;
        this.port = port;
        this.apiKey = apiKey;
    }

    public String getHost() { return host; }
    public int getPort() { return port; }

    @Override
    public String toString() {
        return "Server{host='" + host + "', port=" + port + ", key='***SECRET***'}";
    }
}

class Segment1D {
    protected double length;

    public Segment1D(double length) {
        this.length = Math.max(0, length);
    }
}

class Square2D extends Segment1D {
    public Square2D(double side) {
        super(side);
    }

    public double getPerimeter() { return length * 4; }
    public double getArea() { return length * length; }
}

class Cube3D extends Square2D {
    public Cube3D(double edge) {
        super(edge);
    }

    @Override
    public double getArea() {
        return 6 * super.getArea();
    }

    public double getVolume() {
        return length * length * length;
    }
}


// ==========================================
// ГОЛОВНИЙ КЛАС ЗАПУСКУ (Practice08)
// ==========================================

public class practice08 {
    public static void main(String[] args) {
        System.out.println("=== ПРАКТИЧНА РОБОТА №8: ДЕМОНСТРАЦІЯ ===");

        // Завдання 1
        Student st = new Student("Олександр", 2, 95.5);
        System.out.println(st);

        // Завдання 2
        Product pr = new Product("A-101", "Монітор", 6499.50, 4);
        System.out.println(pr);

        // Завдання 3
        Circle circle = new Circle(5.0);
        System.out.println(circle);

        // Завдання 4
        CardWallet wallet = new CardWallet("1234567812344589", "0000", 12500.00);
        System.out.println(wallet);

        // Завдання 9
        Developer dev = new Developer("Polina", 20000, "Java");
        System.out.println(dev);

        // Завдання 12
        Box3D box = new Box3D(3.0, 4.0, 5.0);
        System.out.println(box);

        // Завдання 25
        AlchemistHero alch = new AlchemistHero("Alchemist", 200, 25, 500);
        System.out.println(alch);

        // Завдання 35
        Point3D p3 = new Point3D(1, 2, 2);
        System.out.println("Відстань 3D точки до початку координат: " + p3.distanceToOrigin());

        System.out.println("\nВсі завдання виконані успішно!");
    }
}