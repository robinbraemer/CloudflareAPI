/*
 * Copyright (c) RoboFlax. All rights reserved.
 * Use is subject to license terms.
 */
package eu.roboflax.cloudflare;

import com.google.common.base.Preconditions;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import eu.roboflax.cloudflare.json.ZoneSettingDeserializer;
import eu.roboflax.cloudflare.objects.zone.ZoneSetting;
import io.joshworks.restclient.http.RestClient;
import lombok.AccessLevel;
import lombok.Getter;
import org.apache.http.client.config.CookieSpecs;

import javax.annotation.Nullable;
import java.io.Closeable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CloudflareAccess implements Closeable {
    
    @Getter
    private final String xAuthKey;
    @Getter
    private final String xAuthEmail;
    
    @Getter(AccessLevel.PROTECTED)
    private final RestClient restClient;
    
    private ExecutorService threadPool;
    
    
    public static final String API_BASE_URL = "https://api.cloudflare.com/client/v4/";
    
    /**
     * This gson object is used for parsing results fora CloudflareResponse
     * Change this gson for own specifications.
     */
    @Getter
    private static Gson gson = new GsonBuilder()
            .registerTypeAdapter( ZoneSetting.class, new ZoneSettingDeserializer() )
            .create();
    
    public CloudflareAccess( String xAuthKey, String xAuthEmail, @Nullable ExecutorService threadPool ) {
        this.xAuthKey = xAuthKey;
        this.xAuthEmail = xAuthEmail;
        this.threadPool = threadPool;
        restClient = RestClient.builder()
                .baseUrl( API_BASE_URL )
                .defaultHeader( "Content-Type", "application/json" )
                .defaultHeader( "X-Auth-Key", this.getXAuthKey() )
                .defaultHeader( "X-Auth-Email", this.getXAuthEmail() )
                .followRedirect( false )
                .cookieSpec( CookieSpecs.IGNORE_COOKIES )
                .build();
    }
    
    public CloudflareAccess( String xAuthKey, String xAuthEmail, int maxThreads ) {
        this( xAuthKey, xAuthEmail, Executors.newFixedThreadPool( maxThreads ) );
    }
    
    public CloudflareAccess( String xAuthKey, String xAuthEmail ) {
        this( xAuthKey, xAuthEmail, null );
    }
    
    public CloudflareAccess( CloudflareConfig config ) {
        this( config.getXAuthKey(), config.getXAuthEmail(), config.getThreadPool() );
    }
    
    public static void setGson( Gson gson ) {
        CloudflareAccess.gson = Preconditions.checkNotNull( gson );
    }
    
    public ExecutorService getThreadPool( ) {
        if ( this.threadPool == null )
            this.threadPool = Executors.newFixedThreadPool( 100 );
        return threadPool;
    }
    
    @Override
    public void close( ) {
        getRestClient().close();
        if ( threadPool != null )
            threadPool.shutdownNow();
    }
}
