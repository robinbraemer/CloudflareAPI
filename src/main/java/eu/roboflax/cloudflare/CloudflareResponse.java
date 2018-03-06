/*
 * Copyright (c) RoboFlax. All rights reserved.
 * Use is subject to license terms.
 */
package eu.roboflax.cloudflare;

import com.google.common.collect.Lists;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;
import eu.roboflax.cloudflare.objects.ResultInfo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

import static eu.roboflax.cloudflare.CloudflareAccess.gson;
import static eu.roboflax.cloudflare.CloudflareAccess.jsonParser;

public class CloudflareResponse {
    
    /**
     * The whole json object.
     */
    private final JsonObject jsonObject;
    
    /**
     * If the request was successful.
     */
    @SerializedName("success")
    private final boolean successful;
    
    /**
     * Messages.
     */
    // todo how are they structured?
    @Getter
    private final List<String> messages = Lists.newArrayList();
    
    private ResultInfo resultInfo;
    
    /**
     * A list of errors.
     */
    @Getter
    private final List<CloudflareError> errors = Lists.newArrayList();
    
    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    public class CloudflareError {
        
        @SerializedName("code")
        private int errorCode;
        
        @SerializedName("message")
        private String errorMessage;
    }
    
    public CloudflareResponse( String json ) {
        if ( json == null || "".equals( json.trim() ) ) {
            jsonObject = new JsonObject();
            successful = false;
        } else {
            jsonObject = jsonParser.parse( json ).getAsJsonObject();
            successful = getRoot().get( "success" ).getAsBoolean() && resultExists();
            if ( isSuccessful() && getRoot().has( "result_info" ) )
                resultInfo = gson.fromJson( getRoot().getAsJsonObject( "result_info" ), ResultInfo.class );
            JsonObject o;
            for ( JsonElement e : getRoot().getAsJsonArray( "errors" ) ) {
                o = e.getAsJsonObject();
                getErrors().add( new CloudflareError( o.get( "code" ).getAsInt(), o.get( "message" ).getAsString() ) );
            }
        }
    }
    
    /**
     * Get the "result" entry from the root as json object.
     *
     * @return the JsonObject
     */
    public JsonObject getAsObject( ) {
        return getRoot().getAsJsonObject( "result" );
    }
    
    /**
     * Get the "result" entry from the root as json array.
     *
     * @return the JsonArray
     */
    public JsonArray getAsArray( ) {
        return getRoot().getAsJsonArray( "result" );
    }
    
    /**
     * Get the "result" entry from the root as an json element.
     *
     * @return the JsonElement
     */
    public JsonElement getAsElement( ) {
        return getRoot().get( "result" );
    }
    
    /**
     * Check if the "result" entry from the root exists and is not null.
     *
     * @return the JsonElement
     */
    public boolean resultExists( ) {
        return getRoot().has( "result" ) && getAsElement() != null;
    }
    
    public JsonObject getRoot( ) {
        return jsonObject;
    }
    
    /**
     * Check if the request was successful and
     * the response contains the right output.
     * <p>
     * Running isSuccessful() on the HttpResponse<CloudflareResponse> object would be enough.
     *
     * @return true if everything was successful
     */
    public boolean isSuccessful( ) {
        return successful;
    }
    
    @Override
    public String toString( ) {
        return getRoot().toString();
    }
}
