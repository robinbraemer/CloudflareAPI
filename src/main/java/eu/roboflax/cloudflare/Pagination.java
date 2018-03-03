/*
 * Copyright (c) RoboFlax. All rights reserved.
 * Use is subject to license terms.
 */
package eu.roboflax.cloudflare;

import com.google.common.collect.Maps;
import eu.roboflax.cloudflare.constants.Direction;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class Pagination {
    
    private Integer page;
    
    private Integer perPage;
    
    private String order;
    
    private Direction direction;
    
    public Map<String, Object> getAsQueryStringsMap( ) {
        Map<String, Object> queryStrings = Maps.newHashMap();
        queryStrings.put( "page", getPage() );
        queryStrings.put( "per_page", getPerPage() );
        queryStrings.put( "order", getOrder() );
        queryStrings.put( "direction", getDirection().name().toLowerCase() );
        return queryStrings;
    }
}
