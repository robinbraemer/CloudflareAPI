/*
 * Copyright (c) RoboFlax. All rights reserved.
 * Use is subject to license terms.
 */
package eu.roboflax.cloudflare.objects.accessrule;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * @see <a href="https://api.cloudflare.com/#user-level-firewall-access-rule-properties">https://api.cloudflare.com</a>
 */
@Getter
@Setter
public class Configuration {
    
    @SerializedName("target")
    @Expose
    private String target;
    @SerializedName("value")
    @Expose
    private String value;
    
    @Override
    public String toString() {
        return new ToStringBuilder(this).append("target", target).append("value", value).toString();
    }
}
