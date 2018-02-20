/*
 * Copyright (c) RoboFlax. All rights reserved.
 * Use is subject to license terms.
 *
 * Project cloudflare created by RoboFlax.
 * Class created on 22.12.2017 at 09:47.
 *
 */
package eu.roboflax.cloudflare.constants;

public abstract class ParamValues {
    
    public enum Frequency {
        WEEKLY, MONTHLY, QUARTERLY, YEARLY
    }
    
    public enum Mode {
        BLOCK, CHALLENGE, WHITELIST, JS_CHALLENGE
    }
    
    public enum Status {
        ACCEPTED, REJECTED
    }
    
}
