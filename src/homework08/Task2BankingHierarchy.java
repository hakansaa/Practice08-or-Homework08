///
package homework08;

// Базовий банківський рахунок
class BankAccount {
    private String iban;
    private String ownerName;
    protected double balance; // protected для зручного доступу в нащадках

    public BankAccount(String iban, String ownerName, double initialBalance) {
        this.iban = (iban != null) ? iban : "UA000000";
        this.ownerName = (ownerName != null) ? ownerName : "Невідомий власник";
        this.balance = Math.max(0.0, initialBalance);
    }

    public String getIban() { return iban; }
    public String getOwnerName() { return ownerName; }
    public double getBalance() { return balance; }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
        } else {
            System.out.println("⚠️ Поповнення повинно бути більше за 0!");
        }
    }

    public boolean withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            return true;
        }
        System.out.println("❌ Недостатньо коштів або некоректна сума зняття!");
        return false;
    }

    @Override
    public String toString() {
        return String.format("Рахунок[%s | Власник: %s | Баланс: %.2f грн]", iban, ownerName, balance);
    }
}

// Підклас: Ощадний рахунок
class SavingsAccount extends BankAccount {
    private double interestRate;
    private double minBalance;

    public SavingsAccount(String iban, String ownerName, double initialBalance, double interestRate, double minBalance) {
        super(iban, ownerName, initialBalance);
        this.interestRate = Math.max(0.0, interestRate);
        this.minBalance = Math.max(0.0, minBalance);
    }

    @Override
    public boolean withdraw(double amount) {
        if (balance - amount < minBalance) {
            System.out.printf("-> Спроба зняти %.0f грн: ВІДМОВА (порушення незнижуваного залишку %.2f грн)\n",
                    amount, minBalance);
            return false;
        }
        return super.withdraw(amount);
    }

    // Нарахування річних відсотків
    public void applyInterest() {
        double interest = balance * (interestRate / 100.0);
        deposit(interest);
    }

    @Override
    public String toString() {
        return String.format("SavingsAccount{%s, Ставка: %.1f%%, Мін.залишок: %.2f грн}",
                super.toString(), interestRate, minBalance);
    }
}

// Підклас: Кредитний рахунок
class CreditAccount extends BankAccount {
    private double creditLimit;
    private double feePercent;

    public CreditAccount(String iban, String ownerName, double initialBalance, double creditLimit, double feePercent) {
        super(iban, ownerName, initialBalance);
        this.creditLimit = Math.max(0.0, creditLimit);
        this.feePercent = Math.max(0.0, feePercent);
    }

    @Override
    public boolean withdraw(double amount) {
        if (amount <= 0) return false;

        // 1. Якщо вистачає власних коштів
        if (balance >= amount) {
            return super.withdraw(amount);
        }

        // 2. Якщо задіюються кредитні кошти
        double borrowed = amount - Math.max(0.0, balance);
        double fee = borrowed * (feePercent / 100.0);
        double totalToDebit = amount + fee;

        if (balance - totalToDebit < -creditLimit) {
            System.out.printf("-> Спроба зняти %.0f грн: ВІДМОВА (перевищення кредитного ліміту %.2f грн)\n",
                    amount, creditLimit);
            return false;
        }

        balance -= totalToDebit;
        System.out.printf("-> Покупка на %.0f грн (%.0f грн у кредит з комісією %.0f%% = %.0f грн)... УСПІШНО!\n",
                amount, borrowed, feePercent, fee);
        return true;
    }

    public double getCurrentDebt() {
        return balance < 0 ? Math.abs(balance) : 0.0;
    }

    @Override
    public String toString() {
        return String.format("CreditAccount{%s, Ліміт: %.2f грн, Борг: %.2f грн}",
                super.toString(), creditLimit, getCurrentDebt());
    }
}

// Головний клас для запуску Завдання 2
public class Task2BankingHierarchy {
    public static void main(String[] args) {
        System.out.println("================ БАНКІВСЬКА ІЄРАРХІЯ РАХУНКІВ ================");

        // 1. Тест Ощадного рахунку
        System.out.println("1. Ощадний рахунок (початковий стан):");
        SavingsAccount savings = new SavingsAccount("UA101", "Олена Коваль", 5000.0, 12.0, 1000.0);
        System.out.println(savings);

        savings.withdraw(4500.0); // Спроба порушити незнижуваний залишок

        System.out.println("-> Нарахування річних відсотків (+12%)...");
        savings.applyInterest();
        System.out.println(savings);

        System.out.println("\n2. Кредитний рахунок (початковий стан):");
        CreditAccount credit = new CreditAccount("UA202", "Максим Бондар", 2000.0, 10000.0, 2.0);
        System.out.println(credit);

        credit.withdraw(5000.0); // Успішна покупка в кредит
        System.out.println(credit);

        System.out.println("==============================================================");
    }
}