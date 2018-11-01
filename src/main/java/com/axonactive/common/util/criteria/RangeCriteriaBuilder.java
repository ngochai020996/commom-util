/**
 * 
 */
package com.axonactive.common.util.criteria;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * @author nvmuon
 *
 */
@NoArgsConstructor(access = AccessLevel.PACKAGE)
public class RangeCriteriaBuilder {

	private Integer from;

	private Integer to;
	
	public RangeCriteriaBuilder from(Integer from) {
		this.from = from;
		return this;
	}

	public RangeCriteriaBuilder to(Integer to) {
		this.to = to;
		return this;
	}

	public RangeCriteria build() {
		if (!RangeCriteria.checkValid(this.from, this.to)) {
			throw new IllegalArgumentException("The range is incorrect");
		}
		
		return  new RangeCriteria(this.from, this.to);
	}

}
