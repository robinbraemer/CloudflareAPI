/*
 * Copyright (c) RoboFlax. All rights reserved.
 * Use is subject to license terms.
 *
 * Project cloudflare created by RoboFlax.
 * Class created on 22.12.2017 at 09:37.
 *
 */
package eu.roboflax.cloudflare.service;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import eu.roboflax.cloudflare.CloudflareAccess;
import eu.roboflax.cloudflare.CloudflareResponse;
import eu.roboflax.cloudflare.objects.Identifiable;
import io.joshworks.restclient.http.HttpResponse;
import lombok.Getter;
import org.apache.http.client.HttpResponseException;

import java.net.HttpURLConnection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

@Getter
public abstract class Service {
    
    public CloudflareAccess cloudflareAccess;
    public static Gson gson = CloudflareAccess.gson;
    public static JsonParser jsonParser = CloudflareAccess.jsonParser;
    
    public Service( CloudflareAccess cloudflareAccess ) {
        this.cloudflareAccess = cloudflareAccess;
    }
    
    /**
     * Checks if the http response is valid.
     *
     * @param httpResponse the httpResponse
     * @return true if successful, otherwise false
     */
    public boolean isValidHttpResponse( HttpResponse<CloudflareResponse> httpResponse ) {
        return httpResponse != null &&
                HttpURLConnection.HTTP_OK == httpResponse.getStatus() &&
                httpResponse.getBody().isSuccessful();
    }
    
    /**
     * Checks if the http response is valid and
     * complete the passed future exceptionally if so.
     *
     * @param httpResponse the httpResponse
     * @param future the future to complete exceptionally by invalid response
     * @return true if successful, otherwise false
     */
    public boolean isValidHttpResponse( HttpResponse<CloudflareResponse> httpResponse, CompletableFuture<?> future ) {
        if ( httpResponse == null ||
                HttpURLConnection.HTTP_OK != httpResponse.getStatus() ||
                !httpResponse.getBody().isSuccessful() ) {
            future.completeExceptionally( new HttpResponseException( httpResponse.getStatus(), httpResponse.getStatusText() ) );
            return false;
        }
        return true;
    }
    
    public static <V extends Identifiable> Map<String, V> buildObjectByIdMap( HttpResponse<CloudflareResponse> http, Class<V> valueType ) {
        Map<String, V> result = new HashMap<>();
        V value;
        for ( JsonElement element : http.getBody().getAsArray() ) {
            value = gson.fromJson( element.getAsJsonObject(), valueType );
            result.put( value.getId(), value );
        }
        return result;
    }
}
