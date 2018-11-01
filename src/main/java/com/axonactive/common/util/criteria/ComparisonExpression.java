package com.axonactive.common.util.criteria;

/**
 * @author nvmuon
 */
public enum ComparisonExpression {

    EQUAL("equal"),
    LIKE("like"),
    NOT_EQUAL("notequal"),
    GREATER_THAN("gt"),
    GREATER_THAN_OR_EQUAL("ge"),
    LESS_THAN("lt"),
    LESS_THAN_OR_EQUAL("le"),
    BETWEEN("between"),
    NOT_BETWEEN("notbetween");

    private String expression;

    ComparisonExpression(String expression) {
        this.expression = expression;
    }

    @Override
    public String toString() {
        return this.expression.toLowerCase();
    }

   
}
