package Expressions;

public class EvaluationVisitor implements ExpressionVisitor {
    private double result;

    @Override
    public void visit(NumberExpression number) {
        result = number.getValue(); // Directly return the number's value
    }

    @Override
    public void visit(OperationExpression operation) {
        // Evaluate left and right operands recursively
        EvaluationVisitor leftEval = new EvaluationVisitor();
        EvaluationVisitor rightEval = new EvaluationVisitor();
        
        operation.getLeft().accept(leftEval);
        operation.getRight().accept(rightEval);
        
        // Perform the actual calculation
        switch(operation.getOperator()) {
            case "+" -> result = leftEval.result + rightEval.result;
            case "-" -> result = leftEval.result - rightEval.result;
            case "*" -> result = leftEval.result * rightEval.result;
            case "/" -> result = leftEval.result / rightEval.result;
        }
    }
    
    public double getResult() { return result; }
}