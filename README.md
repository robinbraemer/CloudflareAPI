# Cloudflare: The Cloudflare - API/Library for Java

This Cloudflare API/Library interacts with [Cloudflare's fast API v4](https://api.cloudflare.com/).
It allows you to access every feature of Cloudflare's API _(even if it isn't added yet)_.

## Latest release

todo

## Features
- **able to access every corner of cloudflare's api**
- **implemented services which simplifies a cloudflare request (+async)**
- **object oriented representation of the cloudflare api** (60%)
- **it is an extremely flexible and stable api**

## Getting Started
First, create an entry-point to use this api.
```java
String CF_EMAIL = "your_cloudflare_email@example.com";
String CF_API_KEY = "your_cloudflare_api_key";

CloudflareAccess cfAccess = new CloudflareAccess(CF_EMAIL, CF_API_KEY);
```
From there you can use already implemented services.
```java
cfAccess.zoneService()...
cfAccess.userService()...
// and more services...
```

## Learn more about the Cloudflare - API/Library

- Our users' guide in the wiki, [Cloudflare - API/Library explained](https://github.com/RoboFlax/Cloudflare/wiki).

### Licensing
Licensed under the MIT license. See the [LICENSE](LICENSE) file for details.