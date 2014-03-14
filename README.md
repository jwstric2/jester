Jester [![Build Status](https://travis-ci.org/jscep/jester.png?branch=master)](https://travis-ci.org/jscep/jester)
======

Java Implementation of [Enrollment over Secure Transport](http://tools.ietf.org/html/rfc7030).

Requirements
------------

* Java 7

Running Jester
--------------

You can start the Jester server like so:

```bash
mvn clean install
cd jester-sample-war
mvn jetty:run
```

You should now have a functioning EST server at: [http://localhost:8080/.well-known/est/](http://localhost:8080/.well-known/est/).

Using OpenSSL
-------------

You should be able to download the CA store at [http://localhost:8080/.well-known/est/cacerts](http://localhost:8080/.well-known/est/cacerts) and parse it with OpenSSL, like so:

```bash
$ curl --silent http://localhost:8080/.well-known/est/cacerts \
  | base64 --decode \
  | openssl pkcs7 -inform DER -print_certs
```

The above command will typically produce the following content:

```
subject=/C=Unknown/ST=Unknown/L=Unknown/O=Unknown/OU=Unknown/CN=Jester
issuer=/C=Unknown/ST=Unknown/L=Unknown/O=Unknown/OU=Unknown/CN=Jester
-----BEGIN CERTIFICATE-----
MIIDMjCCAvCgAwIBAgIEE7xPBTALBgcqhkjOOAQDBQAwazEQMA4GA1UEBhMHVW5r
bm93bjEQMA4GA1UECBMHVW5rbm93bjEQMA4GA1UEBxMHVW5rbm93bjEQMA4GA1UE
ChMHVW5rbm93bjEQMA4GA1UECxMHVW5rbm93bjEPMA0GA1UEAxMGSmVzdGVyMB4X
DTEzMDMwNTE2MzIzNFoXDTIzMDMwMzE2MzIzNFowazEQMA4GA1UEBhMHVW5rbm93
bjEQMA4GA1UECBMHVW5rbm93bjEQMA4GA1UEBxMHVW5rbm93bjEQMA4GA1UEChMH
VW5rbm93bjEQMA4GA1UECxMHVW5rbm93bjEPMA0GA1UEAxMGSmVzdGVyMIIBtzCC
ASwGByqGSM44BAEwggEfAoGBAP1/U4EddRIpUt9KnC7s5Of2EbdSPO9EAMMeP4C2
USZpRV1AIlH7WT2NWPq/xfW6MPbLm1Vs14E7gB00b/JmYLdrmVClpJ+f6AR7ECLC
T7up1/63xhv4O1fnxqimFQ8E+4P208UewwI1VBNaFpEy9nXzrith1yrv8iIDGZ3R
SAHHAhUAl2BQjxUjC8yykrmCouuEC/BYHPUCgYEA9+GghdabPd7LvKtcNrhXuXmU
r7v6OuqC+VdMCz0HgmdRWVeOutRZT+ZxBxCBgLRJFnEj6EwoFhO3zwkyjMim4TwW
eotUfI0o4KOuHiuzpnWRbqN/C/ohNWLx+2J6ASQ7zKTxvqhRkImog9/hWuWfBpKL
Zl6Ae1UlZAFMO/7PSSoDgYQAAoGAf825OevadfjDgljqZIk68NLt7ImHwwKjLXx5
XbziLVO1zAgZ8Tn5c625Wcl7JFk+aZ6R7w8bu7mZ22v+/rpTyoIktkpoBstHOZhy
y8sCj5js9vtVto9btNipwKjcxKSiLo6RPbHoJgNO9EInEY2ssZnHa+JqNFtZM6L5
j1Lyk4ujITAfMB0GA1UdDgQWBBQe9oFQ5h7ECde2C92KzQoPa/+KiTALBgcqhkjO
OAQDBQADLwAwLAIUXgy3MvGdAJYCwK0/Z8sehGwZ6a4CFDoBLnTz7hashYRY4Ttc
iV7Vefqz
-----END CERTIFICATE-----
```

Related Documents
-----------------

  - [Certificate Management over CMS (CMC)](http://tools.ietf.org/html/rfc5272)
  - [Certificate Management over CMS (CMC): Transport Protocols](http://tools.ietf.org/html/rfc5273)
  - [Certificate Management Messages over CMS (CMC): Compliance Requirements](http://tools.ietf.org/html/rfc5274)
  - [The Transport Layer Security (TLS) Protocol Version 1.1](http://tools.ietf.org/html/rfc4346)
  - [Hypertext Transfer Protocol -- HTTP/1.1](http://tools.ietf.org/html/rfc2616)
  - [Internet X.509 Public Key Infrastructure Certificate Management Protocol (CMP)](http://tools.ietf.org/html/rfc4210)
  - [HTTP Over TLS](http://tools.ietf.org/html/rfc2818)
  - [PKCS #10: Certification Request Syntax Version 1.5](http://tools.ietf.org/html/rfc2314)
  - [Using the Secure Remote Password (SRP) Protocol for TLS Authentication](http://tools.ietf.org/html/rfc5054)
  - [Uniform Resource Identifier (URI): Generic Syntax](http://tools.ietf.org/html/rfc3986)
  - [Suite B Profile of Certificate Management over CMS](http://tools.ietf.org/html/rfc6403)
  - [HTTP Authentication: Basic and Digest Access Authentication](http://tools.ietf.org/html/rfc2617)
  - [The application/pkcs10 Media Type](http://tools.ietf.org/html/rfc5967)

Out of Scope
------------

  - §3.3.3 - Certificate-less TLS Mutual Authentication (No RFC 5054 in JSSE)
  - §3.5 - Linking Identity and PoP information (No RFC 5929 in JSSE)
