package eu.roboflax.cloudflare.objects.crypto.customHostname;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.HashSet;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Settings {

  @SerializedName("http2")
  @Expose
  private Status http2;

  @SerializedName("min_tls_version")
  @Expose
  private TlsVersion minTlsVersion;

  @SerializedName("tls_1_3")
  @Expose
  private Status tls13;

  @SerializedName("ciphers")
  @Expose
  private Set<String> ciphers = new HashSet<>();
}
