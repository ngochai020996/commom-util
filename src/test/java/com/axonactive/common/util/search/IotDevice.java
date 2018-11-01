/**
 * 
 */
package com.axonactive.common.util.search;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author nvmuon
 *
 */
@NoArgsConstructor @AllArgsConstructor
public @Data class IotDevice {

    private String code;

    private String name;

    private String macAddress;
}
