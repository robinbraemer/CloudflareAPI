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

/**
 * @see <a href="https://api.cloudflare.com/#dns-records-for-a-zone-properties">https://api.cloudflare.com</a>
 */
@Getter
@Setter
public class DNSRecord implements Identifiable {
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("content")
    @Expose
    private String content;
    @SerializedName("proxiable")
    @Expose
    private Boolean proxiable;
    @SerializedName("proxied")
    @Expose
    private Boolean proxied;
    @SerializedName("ttl")
    @Expose
    private Integer ttl;
    @SerializedName("locked")
    @Expose
    private Boolean locked;
    @SerializedName("zone_id")
    @Expose
    private String zoneId;
    @SerializedName("zone_name")
    @Expose
    private String zoneName;
    @SerializedName("modified_on")
    @Expose
    private String modifiedOn;
    @SerializedName("created_on")
    @Expose
    private String createdOn;
    @SerializedName("meta")
    @Expose
    private Meta meta;
    
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
