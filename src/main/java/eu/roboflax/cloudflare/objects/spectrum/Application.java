package eu.roboflax.cloudflare.objects.spectrum;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import eu.roboflax.cloudflare.objects.Identifiable;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

/**
 * @see <a href="https://api.cloudflare.com/#spectrum-applications-list-spectrum-applications">https://api.cloudflare.com</a>
 */
@Getter
@Setter
public class Application implements Identifiable {

  @SerializedName("id")
  @Expose
  private String id;

  @SerializedName("protocol")
  @Expose
  private String protocol;

  @SerializedName("dns")
  @Expose
  private DNS dns;

  @SerializedName("origin_direct")
  @Expose
  private List<String> originDirect;

  @SerializedName("origin_dns")
  @Expose
  private OriginDNS originDNS;

  @SerializedName("origin_port")
  @Expose
  private Integer originPort;

  @SerializedName("proxy_protocol")
  @Expose
  private Boolean proxyProtocol;

  @SerializedName("ip_firewall")
  @Expose
  private Boolean ipFirewall;

  @SerializedName("tls")
  @Expose
  private Tls tls;

  @SerializedName("created_on")
  @Expose
  private String createdOn;

  @SerializedName("modified_on")
  @Expose
  private String modifiedOn;
}
