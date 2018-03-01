/*
 * Copyright (c) RoboFlax. All rights reserved.
 * Use is subject to license terms.
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
