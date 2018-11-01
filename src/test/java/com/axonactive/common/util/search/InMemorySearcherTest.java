/**
 * 
 */
package com.axonactive.common.util.search;

import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.hamcrest.core.Is;
import org.junit.Before;
import org.junit.Test;

import com.axonactive.common.util.criteria.ComparisonExpression;
import com.axonactive.common.util.criteria.Criteria;
import com.axonactive.common.util.criteria.OrderByCriteria;
import com.axonactive.common.util.criteria.SearchByCriteria;

/**
 * @author nvmuon
 *
 */
public class InMemorySearcherTest {

	
	private List<IotDevice> devices;
	
	private InMemorySearcher<IotDevice> searcher;
	
	@Before
	public void initData() {
		this.devices = new ArrayList<>();
		this.devices.add(new IotDevice("d01", "Uno board", "12-34-F2-F8-12"));
		this.devices.add(new IotDevice("d02", "Due board", "12-34-F2-F8-11"));
		this.devices.add(new IotDevice("d03", "Vivo sensor", "12-34-F2-F8-10"));
		this.devices.add(new IotDevice("d04", "Wifi card", "12-34-F2-F8-39"));
		this.devices.add(new IotDevice("d05", "Female wire", "12-34-F2-F8-08"));
		this.devices.add(new IotDevice("d06", "Male wire", "12-34-F2-F8-07"));
		this.devices.add(new IotDevice("d07", "ESB8266", "12-34-F2-F8-06"));
		this.devices.add(new IotDevice("d08", "Wifi card V3", "12-34-F2-F8-19"));
		searcher = SearchEngine.createInMemorySearcher(devices);
	}
	
	@Test
	public void givenAListOfIoTDeviceWhenSearchByExistingNameEquallyThenOneDiviceIsReturned() {
		SearchByCriteria criteria = Criteria.createSearchByCriteriaBuilder().add("name", ComparisonExpression.EQUAL, "ESB8266").build();
		
		List<IotDevice> result =searcher.where(criteria).execute();
		
		assertThat(result.size(), Is.is(1));
		assertThat(result.get(0).getCode(), Is.is("d07"));
	}
	
	@Test
	public void givenAListOfIoTDeviceWhenSearchByExistingNameNotEquallyThenSevenDivicesAreReturned() {
		SearchByCriteria criteria = Criteria.createSearchByCriteriaBuilder().add("name", ComparisonExpression.NOT_EQUAL, "ESB8266").build();
		
		List<IotDevice> result =searcher.where(criteria).execute();
		
		assertThat(result.size(), Is.is(7));
	}
	
	@Test
	public void givenAListOfIoTDeviceWhenSearchByExistingNameLikelyThenTwoDiviceAreReturned() {
		SearchByCriteria criteria = Criteria.createSearchByCriteriaBuilder().add("name", ComparisonExpression.LIKE, "Wifi card").build();
		
		List<IotDevice> result =searcher.where(criteria).execute();
		
		assertThat(result.size(), Is.is(2));
		assertThat(result.get(0).getCode(), Is.is("d04"));
		assertThat(result.get(1).getCode(), Is.is("d08"));
	}
	
	@Test(expected = UnsupportedOperationException.class)
	public void givenAListOfIoTDeviceWhenSearchByUnsupportedFieldThenUnsupportedOperationExceptionThrown() {
		SearchByCriteria criteria = Criteria.createSearchByCriteriaBuilder().add("non-existing", ComparisonExpression.LIKE, "Wifi card").build();
		
		searcher.where(criteria).execute();
	}
	
	@Test 
	public void givenAListOfIoTDeviceWhenSearchByExistingNameLikelyAndOtherByMacAddressThenTwoOrderedDiviceAreReturned() {
		SearchByCriteria criteria = Criteria.createSearchByCriteriaBuilder().add("name", ComparisonExpression.LIKE, "Wifi card").build();
		
		OrderByCriteria sortCriteria = Criteria.creatSortByCriteriaBuilder().addAscending("macAddress").build();
		
		List<IotDevice> result =searcher.where(criteria).orderBy(sortCriteria).execute();
		
		assertThat(result.size(), Is.is(2));
		assertThat(result.get(0).getMacAddress(), Is.is("12-34-F2-F8-19"));
		assertThat(result.get(1).getMacAddress(), Is.is("12-34-F2-F8-39"));
	}
	
	@Test 
	public void givenAListOfIoTDeviceWhenSearchByExistingNameLikelyAndOtherByMacAddressDescendinglyThenTwoOrderedDiviceAreReturned() {
		SearchByCriteria criteria = Criteria.createSearchByCriteriaBuilder().add("name", ComparisonExpression.LIKE, "Wifi card").build();
		
		OrderByCriteria sortCriteria = Criteria.creatSortByCriteriaBuilder().addDescending("macAddress").build();
		
		List<IotDevice> result =searcher.where(criteria).orderBy(sortCriteria).execute();
		
		assertThat(result.size(), Is.is(2));
		assertThat(result.get(0).getMacAddress(), Is.is("12-34-F2-F8-39"));
		assertThat(result.get(1).getMacAddress(), Is.is("12-34-F2-F8-19"));
	}
	
	@Test
	public void  givenAListOfIoTDeviceWhenSearchAllAndRangeFrom0To4Then5DeviesReturned() {
		
		List<IotDevice> result =searcher.range(0, 4).execute();
		assertThat(result.size(), Is.is(4));
	}
	
}
