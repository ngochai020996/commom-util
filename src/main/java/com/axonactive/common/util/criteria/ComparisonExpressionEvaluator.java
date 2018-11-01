/**
 * 
 */
package com.axonactive.common.util.criteria;

import java.util.Objects;

import org.apache.commons.collections.ComparatorUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * @author nvmuon
 *
 */
public class ComparisonExpressionEvaluator {

	private ComparisonExpressionEvaluator() {
	}

	public static boolean evaluate(Object value1, ComparisonExpression expression, Object value2) {
		if (ComparisonExpression.EQUAL == expression) {
			if (value1 instanceof String) {
				String str1 = (String) value1;
				String str2 = (String) value2;
				return StringUtils.equalsIgnoreCase(str1, str2);
			}
			return Objects.deepEquals(value1, value2);
		}
		
		@SuppressWarnings("unchecked")
		int result = ComparatorUtils.naturalComparator().compare(value1, value2);
		if (ComparisonExpression.NOT_EQUAL == expression) {
			return result != 0;
		}

		if (ComparisonExpression.GREATER_THAN == expression) {
			return result > 0;
		}

		if (ComparisonExpression.GREATER_THAN_OR_EQUAL == expression) {
			return result >= 0;
		}

		if (ComparisonExpression.LESS_THAN == expression) {
			return result < 0;
		}

		if (ComparisonExpression.LESS_THAN_OR_EQUAL == expression) {
			return result <= 0;
		}

		if (value1 instanceof String) {
			String str1 = (String) value1;
			str1 = str1.toLowerCase();
			String str2 = (String) value2;
			str2 = str2.toLowerCase();
			if (ComparisonExpression.LIKE == expression) {
				return str1.contains(str2);
			}
		}
		String message = StringUtils.joinWith(" ", "The comparison expression", expression, "is not supported for the value", value1);
		throw new UnsupportedOperationException(message);
	}
}
