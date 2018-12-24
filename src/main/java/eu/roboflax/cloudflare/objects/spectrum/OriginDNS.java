package eu.roboflax.cloudflare.objects.spectrum;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OriginDNS {

  @SerializedName("name")
  @Expose
  private String name;

  @SerializedName("ttl")
  @Expose
  private Integer ttl;
}
