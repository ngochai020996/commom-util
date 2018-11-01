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
 * SortCriteria defines an order pattern (field, descending) to sort the result
 *
 * @author nvmuon
 */
@NoArgsConstructor @AllArgsConstructor
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public @Data class OrderCriteria implements Serializable {

    private String field = null;
    
    private boolean descending;
    
    /**
	 * @param field
	 */
	public OrderCriteria(String field) {
		this.field = field;
		this.descending = false;
	}

    public static OrderCriteria fromString(String json) {
        return JsonUtils.fromJson(json, OrderCriteria.class);
    }
}
