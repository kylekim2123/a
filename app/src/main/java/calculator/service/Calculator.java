package calculator.service;

import calculator.entity.Expression;
import calculator.entity.Notation;
import calculator.entity.Operator;
import java.util.Stack;

public class Calculator {

    private final Notation notation;

    public Calculator(Notation notation) {
        this.notation = notation;
    }

    public int calculate(String expression) {
        String[] elements = notation.makeElements(expression);

        return evaluate(elements);
    }

    private int evaluate(String[] elements) {
        Stack<Integer> operands = new Stack<>();
        Stack<Operator> operators = new Stack<>();

        for (String element : elements) {
            notation.makeExpression(operands, operators, element)
                .map(Expression::evaluate)
                .ifPresent(operands::push);
        }

        return operands.pop();
    }
}
