/*
 * Copyright (c) RoboFlax. All rights reserved.
 * Use is subject to license terms.
 */
package eu.roboflax.cloudflare;

import com.google.common.base.Preconditions;
import com.google.common.util.concurrent.MoreExecutors;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import eu.roboflax.cloudflare.configuration.CloudflareConfig;
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
import java.util.concurrent.TimeUnit;

public class CloudflareAccess implements Closeable {
    
    @Getter
    private final String xAuthKey;
    @Getter
    private final String xAuthEmail;
    
    @Getter(AccessLevel.PROTECTED)
    private final RestClient restClient;
    
    private ExecutorService threadPool;
    
    
    public static final String API_BASE_URL = "https://api.cloudflare.com/client/v4/";
    
    private static ExecutorService newDefaultThreadPool( int maxThreads ) {
        return Executors.newFixedThreadPool( maxThreads );
    }
    
    public static final int DEFAULT_MAX_THREADS = 100;
    public static final ExecutorService DEFAULT_THREAD_POOL = newDefaultThreadPool( DEFAULT_MAX_THREADS );
    
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
        this( xAuthKey, xAuthEmail, newDefaultThreadPool( maxThreads ) );
    }
    
    public CloudflareAccess( String xAuthKey, String xAuthEmail ) {
        this( xAuthKey, xAuthEmail, null );
    }
    
    public CloudflareAccess( CloudflareConfig config ) {
        this( config.getXAuthKey(),
                config.getXAuthEmail(),
                config.getThreadPool() != null ?
                        config.getThreadPool() : newDefaultThreadPool(
                        config.getMaxThreads() != null ? config.getMaxThreads() : DEFAULT_MAX_THREADS ) );
    }
    
    public static void setGson( Gson gson ) {
        CloudflareAccess.gson = Preconditions.checkNotNull( gson );
    }
    
    public ExecutorService getThreadPool( ) {
        if ( this.threadPool == null )
            this.threadPool = DEFAULT_THREAD_POOL;
        return threadPool;
    }
    
    
    public void close( long timeout, TimeUnit unit ) {
        getRestClient().close();
        if ( threadPool != null )
            MoreExecutors.shutdownAndAwaitTermination( threadPool, timeout, unit );
    }
    
    @Override
    public void close( ) {
        close( 4, TimeUnit.SECONDS );
    }
}
