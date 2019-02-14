/*
 * Copyright (c) RoboFlax. All rights reserved.
 * Use is subject to license terms.
 */
package eu.roboflax.cloudflare;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import eu.roboflax.cloudflare.objects.ResultInfo;
import lombok.Getter;

import java.util.List;
import java.util.Map;

/**
 * Represents a cloudflare response.
 *
 * @param <T> is the type of the parsed result
 */
public class CloudflareResponse<T> {
    
    /**
     * The whole json object.
     * Is null when failed.
     */
    @Getter
    private final JsonObject json;
    
    /**
     * The "result" property in the json response parsed as the given type.
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
    private final String statusMessage;
    
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
    @Getter
    private final ResultInfo resultInfo;
    
    CloudflareResponse( JsonObject json, T object, boolean successful, int statusCode, String statusMessage ) {
        this.messages = Lists.newArrayList(); // TODO
        this.json = json;
        this.successful = successful;
        this.statusCode = statusCode;
        this.statusMessage = statusMessage;
        
        // Errors
        errors = Maps.newHashMap();
        JsonObject o;
        if(getJson().has( "errors" )) {
          for ( JsonElement e : getJson().getAsJsonArray( "errors" ) ) {
            o = e.getAsJsonObject();
            errors.put( o.get( "code" ).getAsInt(), o.get( "message" ).getAsString() );
          }
        }
        
        // Pagination infos
        if(getJson().has( "result_info" ))
            resultInfo = CloudflareAccess.getGson().fromJson( getJson().getAsJsonObject( "result_info" ), ResultInfo.class );
        else resultInfo = null;
        
        this.object = object;
    }
}
