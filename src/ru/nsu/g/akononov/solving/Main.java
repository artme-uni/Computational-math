package ru.nsu.g.akononov.solving;

import ru.nsu.g.akononov.solving.Equation.Equation;
import ru.nsu.g.akononov.solving.Equation.EquationType;

import java.util.LinkedList;
import java.util.Scanner;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Решение кубического уравнения вида: x^3 + ax^2 + bx + c = 0");

        double a = scanner("a", in);
        double b = scanner("b", in);
        double c = scanner("c", in);
        double epsilon = 10E-10;
        double delta = scanner("delta", in);

        Equation equation = new Equation(EquationType.cubic, a,b,c);
        Set<Double> roots = equation.solve(epsilon, delta);
        System.out.println("Корни уравнения: ");

        LinkedList<Double> rootList = new LinkedList<>(roots);
        for(int i = 0; i < roots.size(); ++i){
            System.out.println(rootList.get(i));
        }
    }

    public static double scanner(String variableName, Scanner in) {
        System.out.print(variableName + " = ");
        String inputString = in.next();
        double number;
        try {
            number = Double.parseDouble(inputString);
        } catch (NumberFormatException e) {
            System.out.println("Некорректный ввод, ожидается число");
                number = scanner(variableName, in);
        }
        return number;
    }
}
