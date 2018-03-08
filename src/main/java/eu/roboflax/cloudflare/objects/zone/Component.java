/*
 * Copyright (c) RoboFlax. All rights reserved.
 * Use is subject to license terms.
 */
package eu.roboflax.cloudflare.objects.zone;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * @see <a href="https://api.cloudflare.com/#zone-properties">https://api.cloudflare.com</a>
 */
@Getter
@Setter
public class Component {
    
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("default")
    @Expose
    private Integer _default;
    @SerializedName("unit_price")
    @Expose
    private Integer unitPrice;
    
    
    @Override
    public String toString( ) {
        return new ToStringBuilder( this ).append( "name", name ).append( "_default", _default ).append( "unitPrice", unitPrice ).toString();
    }
    
}