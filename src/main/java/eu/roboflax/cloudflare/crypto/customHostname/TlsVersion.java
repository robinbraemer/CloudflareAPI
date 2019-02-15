package eu.roboflax.cloudflare.objects.crypto.customHostname;

import com.google.gson.annotations.SerializedName;

public enum TlsVersion {
  @SerializedName("1.0")
  TLS_1_0("1.0"),

  @SerializedName("1.1")
  TLS_1_1("1.1"),

  @SerializedName("1.2")
  TLS_1_2("1.2"),

  @SerializedName("1.3")
  TLS_1_3("1.3");

  private final String value;

  TlsVersion(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }
}
