package JAVA.OOP;

// 1 - Конструируем калькулятор, применяя архитектуру MVP (Model-View-Presenter), с возможностью выполнения базовых математических операций,
//  таких как сложение, вычитание, умножение и деление, работающий с дробными числами.
// Доп. Задача (*) Мы также обеспечим обработку возможных ошибок, чтобы предотвратить некорректные операции.


import java.util.Scanner;

public class OOP51 {
    public static void main(String[] args) {
        CalculatorModel model = new CalculatorModel();
        CalculatorView view = new CalculatorView();
        CalculatorPresenter presenter = new CalculatorPresenter(model, view);
        presenter.performOperation();
    }
}
/// P.S. Очень странно, не получается изначально ввести дробное число. Все оперции с целыми числами работают, в том числе и деление с дробным результатом. 
/// НО вот изначальные дробные числа не хотят работать :( 

class CalculatorModel {

    private double result;

    public void add(double num1, double num2) {
        result = num1 + num2;
    }
    public void subtract(double num1, double num2) {
        result = num1 - num2;
    }
    public void multiply(double num1, double num2) {
        result = num1 * num2;
    }
    public void divide(double num1, double num2) {
        if (num2 != 0) {
            result = num1 / num2;
        } else {
            System.out.println("Error: Делить на 0 нельзя");
        }
    }
    public double getResult() {
        return result;
    }
}

class CalculatorView {
    public void displayResult(double result) {
        System.out.println("Результат: " + result);
    }
    public double getNumberInput() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите число: ");
        return scanner.nextDouble();
    }
    public char getOperationInput() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите операцию из  этого списка -> +, -, *, /: ");
        return scanner.next().charAt(0);
    }
}

class CalculatorPresenter {
    private CalculatorModel model;
    private CalculatorView view;

    public CalculatorPresenter(CalculatorModel model, CalculatorView view) {
        this.model = model;
        this.view = view;
    }
    public void performOperation() {
        double num1 = view.getNumberInput();
        char operation = view.getOperationInput();
        double num2 = view.getNumberInput();

        switch (operation) {
            case '+':
                model.add(num1, num2);
                break;
            case '-':
                model.subtract(num1, num2);
                break;
            case '*':
                model.multiply(num1, num2);
                break;
            case '/':
                model.divide(num1, num2);
                break;
            default:
                System.out.println("Error: НЕВЕРНАЯ ОПЕРАЦИЯ. ПОПРОБУЙТЕ СНОВА");
                return;
        }
        view.displayResult(model.getResult());
    }
}