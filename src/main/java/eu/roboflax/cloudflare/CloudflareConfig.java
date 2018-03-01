/*
 * Copyright (c) RoboFlax. All rights reserved.
 * Use is subject to license terms.
 */
package eu.roboflax.cloudflare;

import lombok.Getter;
import lombok.Setter;

import javax.annotation.Nullable;
import java.util.concurrent.ExecutorService;

@Getter
@Setter
public class CloudflareConfig {
    
    private String xAuthKey;
    private String xAuthEmail;
    private ExecutorService threadPool;
    private Integer maxThreads;
    
    public CloudflareConfig( String xAuthKey, String xAuthEmail ) {
        this.xAuthKey = xAuthKey;
        this.xAuthEmail = xAuthEmail;
    }
    
    public CloudflareConfig( String xAuthKey, String xAuthEmail, @Nullable ExecutorService threadPool ) {
        this( xAuthKey, xAuthEmail );
        this.threadPool = threadPool;
    }
}
