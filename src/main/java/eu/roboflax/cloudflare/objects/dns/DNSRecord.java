/*
 * Copyright (c) RoboFlax. All rights reserved.
 * Use is subject to license terms.
 */
package eu.roboflax.cloudflare.objects.dns;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import eu.roboflax.cloudflare.objects.Identifiable;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ToStringBuilder;

@Getter
@Setter
public class DNSRecord implements Identifiable {
    @SerializedName("id")
    @Expose
    public String id;
    @SerializedName("type")
    @Expose
    public String type;
    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("content")
    @Expose
    public String content;
    @SerializedName("proxiable")
    @Expose
    public Boolean proxiable;
    @SerializedName("proxied")
    @Expose
    public Boolean proxied;
    @SerializedName("ttl")
    @Expose
    public Integer ttl;
    @SerializedName("locked")
    @Expose
    public Boolean locked;
    @SerializedName("zone_id")
    @Expose
    public String zoneId;
    @SerializedName("zone_name")
    @Expose
    public String zoneName;
    @SerializedName("modified_on")
    @Expose
    public String modifiedOn;
    @SerializedName("created_on")
    @Expose
    public String createdOn;
    @SerializedName("meta")
    @Expose
    public Meta meta;
    
    @Override
    public String toString( ) {
        return new ToStringBuilder( this )
                .append( "id", id )
                .append( "type", type )
                .append( "name", name )
                .append( "content", content )
                .append( "proxiable", proxiable )
                .append( "proxied", proxied )
                .append( "ttl", ttl )
                .append( "locked", locked )
                .append( "zoneId", zoneId )
                .append( "zoneName", zoneName )
                .append( "modifiedOn", modifiedOn )
                .append( "createdOn", createdOn )
                .append( "meta", meta ).toString();
    }
}
