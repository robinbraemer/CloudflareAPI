/*
 * Copyright (c) RoboFlax. All rights reserved.
 * Use is subject to license terms.
 */
package eu.roboflax.cloudflare_old.objects.user.subscription;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ToStringBuilder;

@Getter
@Setter
public class ComponentValue {
    
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("value")
    @Expose
    private Integer value;
    @SerializedName("default")
    @Expose
    private Integer _default;
    @SerializedName("price")
    @Expose
    private Integer price;
    
    @Override
    public String toString( ) {
        return new ToStringBuilder( this ).append( "name", name ).append( "value", value ).append( "_default", _default ).append( "price", price ).toString();
    }
    
}
