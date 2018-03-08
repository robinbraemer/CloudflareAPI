/*
 * Copyright (c) RoboFlax. All rights reserved.
 * Use is subject to license terms.
 */
package eu.roboflax.cloudflare_old.json;

import eu.roboflax.cloudflare_old.CloudflareResponse;
import io.joshworks.restclient.http.mapper.ObjectMapper;

public class GsonMapper implements ObjectMapper {

    @Override
    public <T> T readValue( String value, Class<T> valueType ) {
        return (T) new CloudflareResponse( value );
    }
    
    @Override
    public String writeValue( Object value ) {
        return null;
    }
}
