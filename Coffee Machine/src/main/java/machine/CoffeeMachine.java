package machine;

import java.util.Scanner;

public class CoffeeMachine {

    private final Scanner scanner;
    private int water;
    private int milk;
    private int beans;
    private int cups;
    private int money;

    public CoffeeMachine(int water, int milk, int beans, int cups, int money) {
        this.water = water;
        this.milk = milk;
        this.beans = beans;
        this.cups = cups;
        this.money = money;
        this.scanner = new Scanner(System.in);
    }

    public static void main(String[] args) {
        CoffeeMachine coffeeMachine = new CoffeeMachine(400, 540, 120, 9, 550);
        coffeeMachine.startAction();
    }

    private void startAction() {
        while (true) {
            System.out.println("Write action (buy, fill, take, remaining, exit): ");
            String scanAction = scanner.nextLine();
            Action action = Action.valueOf(scanAction.toUpperCase());
            switch (action) {
                case BUY:
                    giveCoffee();
                    break;
                case FILL:
                    fillIngredients();
                    break;
                case TAKE:
                    giveMoney();
                    break;
                case REMAINING:
                    showRemainOfIngredients();
                    break;
                case EXIT:
                    return;
                default:
                    break;
            }
        }
    }

    private void giveMoney() {
        money = 0;
        System.out.println();
        System.out.println("I gave you $" + money);
        System.out.println();
    }

    private void fillIngredients() {
        System.out.println();
        System.out.println("Write how many ml of water do you want to add: ");
        this.water += scanner.nextInt();
        System.out.println("Write how many ml of milk do you want to add: ");
        this.milk += scanner.nextInt();
        System.out.println("Write how many grams of coffee beans do you want to add: ");
        this.beans += scanner.nextInt();
        System.out.println("Write how many disposable cups of coffee do you want to add: ");
        this.cups += scanner.nextInt();
        System.out.println();
        // nextLine() を入れないと次の入力で空文字が取り込まれてExceptionになる
        scanner.nextLine();
    }

    private void giveCoffee() {
        Coffee coffee = findCoffee();
        if (coffee == coffee.UNDEFINED) {
            return;
        }
        if (!canMakeCoffee(coffee)) {
            return;
        }

        System.out.println("I have enough resources, making you a coffee!");
        makeCoffee(coffee);
        System.out.println();
    }

    private Coffee findCoffee() {
        System.out.println();
        System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu: ");
        String selectedCoffee = scanner.nextLine();
        Coffee coffee = Coffee.UNDEFINED;
        for (Coffee value : Coffee.values()) {
            if (value.code.equals(selectedCoffee)) {
                coffee = value;
                break;
            }
        }
        return coffee;
    }

    private void makeCoffee(Coffee coffee) {
        cups--;
        water -= coffee.water;
        milk -= coffee.milk;
        beans -= coffee.beans;
        money += coffee.money;
    }

    private boolean canMakeCoffee(Coffee coffee) {
        if (cups == 0) {
            System.out.println("Sorry, not enough cups of coffee!");
            System.out.println();
            return false;
        }
        if (this.water < coffee.water) {
            System.out.println("Sorry, not enough water!");
            System.out.println();
            return false;
        }
        if (this.milk < coffee.milk) {
            System.out.println("Sorry, not enough milk!");
            System.out.println();
            return false;
        }
        if (this.beans < coffee.beans) {
            System.out.println("Sorry, not enough coffee beans!");
            System.out.println();
            return false;
        }
        return true;
    }

    private void showRemainOfIngredients() {
        System.out.println();
        System.out.println("The coffee machine has:");
        System.out.println(this.water + " of water");
        System.out.println(this.milk + " of milk");
        System.out.println(this.beans + " of coffee beans");
        System.out.println(this.cups + " of disposable cups");
        System.out.println("$" + this.money + " of money");
        System.out.println();
    }

    public enum Action {
        BUY, FILL, TAKE, REMAINING, EXIT
    }

    public enum Coffee {
        ESPRESSO("1", 250, 0, 16, 4),
        LATTE("2", 350, 75, 20, 7),
        CAPPUCCINO("3", 200, 100, 12, 6),
        UNDEFINED("", 0, 0, 0, 0);

        private final String code;
        private final int water;
        private final int milk;
        private final int beans;
        private final int money;

        Coffee(String code, int water, int milk, int beans, int money) {
            this.code = code;
            this.water = water;
            this.milk = milk;
            this.beans = beans;
            this.money = money;
        }
    }
}
