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

/**
 * Paginate the result json array.
 * Used for cloudflare request where the returned result property is a json array.
 *
 * @see <a href="https://api.cloudflare.com/#getting-started-requests">api.cloudflare.com under 'Pagination'</a>
 */
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
        if ( getPage() != null )
            queryStrings.put( "page", getPage() );
        if ( getPerPage() != null )
            queryStrings.put( "per_page", getPerPage() );
        if ( getOrder() != null )
            queryStrings.put( "order", getOrder() );
        if ( getDirection() != null )
            queryStrings.put( "direction", getDirection().name() );
        return queryStrings;
    }
}
