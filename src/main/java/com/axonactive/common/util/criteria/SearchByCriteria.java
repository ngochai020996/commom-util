package com.axonactive.common.util.criteria;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.collections.CollectionUtils;

import com.axonactive.common.util.JsonUtils;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * SearchByCriteria defines lists of searchCriteria objects for a search By
 * default each searchCriteria object defined on the criteria list is applied
 * with a select where "and" logical operator.
 *
 * @author nvmuon
 */
@AllArgsConstructor
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public @Data class SearchByCriteria implements Serializable {

	private List<SearchCriteria> criteria = null;

	// Support only OR, AND was removed to simplify the search criterias
	private List<SearchByCriteria> or = null;

	public SearchByCriteria() {
		// We need this to help jackson parse request's data to an SearchByCriteria
		this.criteria = new ArrayList<>();
		this.or = new ArrayList<>();
	}  

	public SearchByCriteria(String json) {
		SearchByCriteria queryObject;
		queryObject = JsonUtils.fromJson(json, SearchByCriteria.class);
		this.criteria = queryObject.criteria;
		this.or = queryObject.or;
	}


	public static SearchByCriteria fromString(String json) {
		if (json == null) {
			return null;
		}
		return JsonUtils.fromJson(json, SearchByCriteria.class);
	}

	public void addAnd(SearchCriteria and) {
		if (Objects.isNull(this.criteria)) {
			this.criteria = new ArrayList<>();
		}

		if (Objects.nonNull(and)) {
			this.criteria.add(and);
		}
	}

	public void addOr(SearchCriteria or) {
		if (Objects.isNull(this.or)) {
			this.or = new ArrayList<>();
		}

		if (Objects.nonNull(or)) {
			SearchByCriteria orCriteria = new SearchByCriteria();
			orCriteria.addAnd(or);
			this.or.add(orCriteria);
		}
	}

	public static final boolean hasCriteria(SearchByCriteria searchBy) {
		return Objects.nonNull(searchBy)
				&& (hasAndCriteria(searchBy.criteria) || hasOrCriteria(searchBy.or)); 
	}
	
	public static final boolean hasAndCriteria(List<SearchCriteria> criteria) {
		return CollectionUtils.isNotEmpty(criteria);
	}
	
	public static final boolean hasOrCriteria(List<SearchByCriteria> criteria) {
		return CollectionUtils.isNotEmpty(criteria); 
	}
}
