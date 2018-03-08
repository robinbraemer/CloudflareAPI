/*
 * Copyright (c) RoboFlax. All rights reserved.
 * Use is subject to license terms.
 */
package eu.roboflax.cloudflare_old.util;

import com.google.gson.JsonElement;
import eu.roboflax.cloudflare_old.CloudflareAccess;
import eu.roboflax.cloudflare_old.CloudflareResponse;
import eu.roboflax.cloudflare_old.objects.Identifiable;
import io.joshworks.restclient.http.HttpResponse;
import org.apache.http.client.HttpResponseException;

import java.net.HttpURLConnection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

public class CloudflareUtils {
    
    /**
     * Checks if the http response is valid.
     *
     * @param httpResponse the httpResponse
     * @return true if successful, otherwise false
     */
    public static boolean isValidHttpResponse( HttpResponse<CloudflareResponse> httpResponse ) {
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
    public static boolean isValidHttpResponse( HttpResponse<CloudflareResponse> httpResponse, CompletableFuture<?> future ) {
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
            value = CloudflareAccess.gson.fromJson( element.getAsJsonObject(), valueType );
            result.put( value.getId(), value );
        }
        return result;
    }
}
