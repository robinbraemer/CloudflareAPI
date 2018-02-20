/*
 * Copyright (c) RoboFlax. All rights reserved.
 * Use is subject to license terms.
 *
 * Project cloudflare created by RoboFlax.
 * Class created on 22.12.2017 at 12:04.
 *
 */
package eu.roboflax.cloudflare.json;

import eu.roboflax.cloudflare.CloudflareResponse;
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
