package eu.roboflax.cloudflare.objects.spectrum;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class OriginDNS {

  @SerializedName("name")
  @Expose
  private String name;

  @SerializedName("ttl")
  @Expose
  private Integer ttl;
}
