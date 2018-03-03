/*
 * Copyright (c) RoboFlax. All rights reserved.
 * Use is subject to license terms.
 */
package eu.roboflax.cloudflare.objects;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class ResultInfo {
    
    @SerializedName("page")
    @Expose
    public Integer page;
    @SerializedName("per_page")
    @Expose
    public Integer perPage;
    @SerializedName("count")
    @Expose
    public Integer count;
    @SerializedName("total_count")
    @Expose
    public Integer totalCount;
    
    @Override
    public String toString( ) {
        return new ToStringBuilder( this )
                .append( "page", page )
                .append( "perPage", perPage )
                .append( "count", count )
                .append( "totalCount", totalCount ).toString();
    }
    
}