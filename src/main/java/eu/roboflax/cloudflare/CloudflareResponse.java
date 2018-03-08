/*
 * Copyright (c) RoboFlax. All rights reserved.
 * Use is subject to license terms.
 */
package eu.roboflax.cloudflare;

import com.google.common.collect.Maps;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import eu.roboflax.cloudflare.objects.ResultInfo;
import lombok.Getter;

import java.util.List;
import java.util.Map;

public class CloudflareResponse<T> {
    
    /**
     * The whole json object.
     * Is null when failed.
     */
    @Getter
    private final JsonObject json;
    
    /**
     * The result parsed as the given type.
     */
    @Getter
    private final T object;
    
    /**
     * If the request was successful.
     */
    @Getter
    private final boolean successful;
    
    /**
     * The returned status code
     */
    @Getter
    private final int statusCode;
    
    /**
     * The returned status text
     */
    @Getter
    private final String statusText;
    
    /**
     * Messages.
     */
    @Getter
    private final List<String> messages;
    
    /**
     * A list of errors -> error message by error code.
     */
    @Getter
    private final Map<Integer, String> errors;
    
    /**
     * Some infos about the returned result regarding the pagination.
     */
    private final ResultInfo resultInfo;
    
    CloudflareResponse( JsonObject json, T object, boolean successful, int statusCode, String statusText ) {
        this.messages = null; // todo how is their structure?
        this.json = json;
        this.successful = successful;
        this.statusCode = statusCode;
        this.statusText = statusText;
        
        // Errors
        errors = Maps.newHashMap();
        JsonObject o;
        for ( JsonElement e : getJson().getAsJsonArray( "errors" ) ) {
            o = e.getAsJsonObject();
            errors.put( o.get( "code" ).getAsInt(), o.get( "message" ).getAsString() );
        }
        
        // Pagination infos
        if(getJson().has( "result_info" ))
            resultInfo = CloudflareAccess.getGson().fromJson( getJson().getAsJsonObject( "result_info" ), ResultInfo.class );
        else resultInfo = null;
        
        this.object = object;
    }
}
