package asmt4;

interface Expression {
    int interpret();
}

class NumberExpression implements Expression {
    private int number;

    public NumberExpression(int number) {
        this.number = number;
    }

    public int interpret() {
        return number;
    }
}

class OperationExpression implements Expression {
    private Expression left;
    private Expression right;
    private Operator operator;

    public OperationExpression(Expression left, Expression right, Operator operator) {
        this.left = left;
        this.right = right;
        this.operator = operator;
    }

    public int interpret() {
        if (operator == Operator.ADD) {
            return left.interpret() + right.interpret();
        } else if (operator == Operator.SUBTRACT) {
            return left.interpret() - right.interpret();
        }
        return 0;
    }
}

enum Operator {
    ADD, SUBTRACT;

    public static Operator fromChar(char operator) {
        switch (operator) {
            case '+':
                return ADD;
            case '-':
                return SUBTRACT;
            default:
                return null;
        }
    }
}

class Interpreter {
    public static Expression parse(String input) {
        String[] tokens = input.split(" ");

        Expression result = new NumberExpression(Integer.parseInt(tokens[0]));

        for (int i = 1; i < tokens.length; i += 2) {
            char operatorChar = tokens[i].charAt(0);
            Operator operator = Operator.fromChar(operatorChar);

            int number = Integer.parseInt(tokens[i + 1]);
            Expression right = new NumberExpression(number);
            result = new OperationExpression(result, right, operator);
        }

        return result;
    }
}

public class Part_1 {
    public static void main(String[] args) {
        String problem = "5 + 2 - 3";
        Expression expression = Interpreter.parse(problem);
        int result = expression.interpret();
        System.out.println("Result: " + result);
    }
}
