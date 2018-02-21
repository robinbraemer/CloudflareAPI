/*
 * Copyright (c) RoboFlax. All rights reserved.
 * Use is subject to license terms.
 *
 * Project cloudflare created by RoboFlax.
 * Class created on 22.12.2017 at 10:54.
 *
 */
package eu.roboflax.cloudflare;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.ArchUtils;

import java.util.ArrayList;
import java.util.List;

import static eu.roboflax.cloudflare.CloudflareAccess.jsonParser;

public class CloudflareResponse {
    
    /**
     * The whole json object.
     */
    private JsonObject jsonObject;
    
    /**
     * If the request was successful.
     */
    @SerializedName("success")
    private final boolean successful;
    
    /**
     * A list of errors.
     */
    @Getter
    private List<CloudflareError> errors = new ArrayList<>(  );
    
    /**
     * Messages.
     */
    @Getter
    private List<String> messages = new ArrayList<>(  );
    
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
     *
     * Running isSuccessful() on the HttpResponse<CloudflareResponse> object would be enough.
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
