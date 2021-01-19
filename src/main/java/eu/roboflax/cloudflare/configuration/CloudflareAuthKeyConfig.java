/*
 * Copyright (c) RoboFlax. All rights reserved.
 * Use is subject to license terms.
 */
package eu.roboflax.cloudflare.configuration;

import lombok.Getter;
import lombok.Setter;

import javax.annotation.Nullable;
import java.util.concurrent.ExecutorService;

/**
 * Configuration for a {@link eu.roboflax.cloudflare.CloudflareAccess} object.
 */
@Getter
@Setter
public class CloudflareAuthKeyConfig {
    
    private String xAuthKey;
    private String xAuthEmail;
    
    private ExecutorService threadPool;
    private Integer maxThreads;
    
    public CloudflareAuthKeyConfig(String xAuthKey, String xAuthEmail ) {
        this.xAuthKey = xAuthKey;
        this.xAuthEmail = xAuthEmail;
    }
    
    public CloudflareAuthKeyConfig(String xAuthKey, String xAuthEmail, @Nullable Integer maxThreads ) {
        this( xAuthKey, xAuthEmail );
        this.maxThreads = maxThreads;
    }
    
    public CloudflareAuthKeyConfig(String xAuthKey, String xAuthEmail, @Nullable ExecutorService threadPool ) {
        this( xAuthKey, xAuthEmail );
        this.threadPool = threadPool;
    }
}

