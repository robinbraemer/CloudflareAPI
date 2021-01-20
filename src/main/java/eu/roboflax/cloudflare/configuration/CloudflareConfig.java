/*
 * Copyright (c) RoboFlax. All rights reserved.
 * Use is subject to license terms.
 */
package eu.roboflax.cloudflare.configuration;

import eu.roboflax.cloudflare.CloudflareAccess;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.Nullable;
import java.util.concurrent.ExecutorService;

/**
 * Configuration for a {@link eu.roboflax.cloudflare.CloudflareAccess} object.
 */
@Getter
@Setter
public class CloudflareConfig {

    private String xAuthKey;
    private String xAuthEmail;

    private String xAuthToken;

    private ExecutorService threadPool;
    private Integer maxThreads;


    //
    // Key + Email auth
    //

    public CloudflareConfig( String xAuthKey, String xAuthEmail ) {
        this.xAuthKey = xAuthKey;
        this.xAuthEmail = xAuthEmail;
    }

    public CloudflareConfig( String xAuthKey, String xAuthEmail, @Nullable Integer maxThreads ) {
        this(xAuthKey, xAuthEmail);
        this.maxThreads = maxThreads;
    }

    public CloudflareConfig( String xAuthKey, String xAuthEmail, @Nullable ExecutorService threadPool ) {
        this(xAuthKey, xAuthEmail);
        this.threadPool = threadPool;
    }

    //
    // Token auth
    //

    public CloudflareConfig( String xAuthToken ) {
        this.xAuthToken = xAuthToken;
    }

    public CloudflareConfig( String xAuthToken, @Nullable Integer maxThreads ) {
        this(xAuthToken);
        this.maxThreads = maxThreads;
    }

    public CloudflareConfig( String xAuthToken, @Nullable ExecutorService threadPool ) {
        this(xAuthToken);
        this.threadPool = threadPool;
    }

    /**
     * Creates a new {@link eu.roboflax.cloudflare.CloudflareAccess} from this configuration.
     *
     * @return the new CloudflareAccess
     */
    public CloudflareAccess createAccess() {
        if ( this.xAuthToken != null ) {
            if ( this.threadPool != null ) {
                return new CloudflareAccess(this.xAuthToken, this.threadPool);
            }
            if ( this.maxThreads != null ) {
                return new CloudflareAccess(this.xAuthToken, this.maxThreads);
            }
            return new CloudflareAccess(this.xAuthToken);
        }
        if ( this.threadPool != null ) {
            return new CloudflareAccess(this.xAuthKey, this.xAuthEmail, this.threadPool);
        }
        if ( this.maxThreads != null ) {
            return new CloudflareAccess(this.xAuthKey, this.xAuthEmail, this.maxThreads);
        }
        return new CloudflareAccess(this.xAuthKey, this.xAuthEmail);
    }
}

