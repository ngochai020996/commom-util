package com.axonactive.common.util.criteria;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.axonactive.common.util.JsonUtils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * SearchCriteria defines a search pattern (field, value and expression) to
 * restrict the search result
 *
 * @author nvmuon
 */
@NoArgsConstructor @AllArgsConstructor
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public @Data class SearchCriteria implements Serializable {

	private String field;

	private ComparisonExpression expression;

	private Object value;
	
	public SearchCriteria(String json) {
		SearchCriteria that = SearchCriteria.fromString(json);
		this.field = that.field;
		this.expression = that.expression;
		this.value = that.value;
	}

	public static SearchCriteria fromString(String json) {
		return JsonUtils.fromJson(json, SearchCriteria.class);
	}
}	
