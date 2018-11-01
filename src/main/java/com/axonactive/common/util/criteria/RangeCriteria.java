package com.axonactive.common.util.criteria;


import java.io.Serializable;
import java.util.Objects;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.axonactive.common.util.JsonUtils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * RangeCriteria defines the attributes to restrict the result for
 * pagination
 * @author nvmuon
 */
@NoArgsConstructor @AllArgsConstructor
@ValidRangeCriteria
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public @Data class RangeCriteria implements Serializable {
	
	private Integer from;
	
	private Integer to;
	
	public RangeCriteria(String json) {
		RangeCriteria that = RangeCriteria.fromString(json);
		this.from = that.from;
		this.to = that.to;
	}
	
	public static RangeCriteria fromString(String json) {
    	return JsonUtils.fromJson(json, RangeCriteria.class);
	}	
	
	public static boolean checkValid(Integer from, Integer to) {
		boolean validFrom = Objects.isNull(from) || from >= 0;
		boolean validTo = Objects.isNull(to) || to >= 0;
		return validFrom && validTo && from < to;
	}
}
