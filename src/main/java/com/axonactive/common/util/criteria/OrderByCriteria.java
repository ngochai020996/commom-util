package com.axonactive.common.util.criteria;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import com.axonactive.common.util.JsonUtils;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * SortByCriteria defines lists of orderCriteria objects for a sort
 *
 * @author nvmuon
 */
@AllArgsConstructor
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public @Data class OrderByCriteria implements Serializable {

	private List<OrderCriteria> order = null;

	public OrderByCriteria() {
		this.order = new ArrayList<>();
	}

	public OrderByCriteria(String queryParamArg)  {
		OrderByCriteria queryObject = JsonUtils.fromJson(queryParamArg, OrderByCriteria.class);
		this.order = queryObject.order;
	}

	public boolean hasField(String field) {

		if (CollectionUtils.isEmpty(order)) {
			return false;
		}

		return this.order.stream()
				.anyMatch(each -> StringUtils.equalsIgnoreCase(each.getField(), field));
	}

	public void addSortCriteria(OrderCriteria criteria) {
		if (Objects.isNull(order)) {
			this.order = new ArrayList<>();
		}
		this.order.add(criteria);
	}

	public static final boolean hasCriteria(OrderByCriteria orderBy) {
		return	Objects.nonNull(orderBy) 
				&& Objects.nonNull(orderBy.getOrder()) 
				&& (!orderBy.getOrder().isEmpty());
	}
}
