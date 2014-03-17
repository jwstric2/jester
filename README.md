Jester [![Build Status](https://travis-ci.org/jscep/jester.png?branch=master)](https://travis-ci.org/jscep/jester)
======

Jester is an open source Java implementation of [Enrollment over Secure Transport](http://tools.ietf.org/html/rfc7030) (RFC 7030).

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

You should now have a functioning EST server at: [https://localhost:8443/.well-known/est/](https://localhost:8443/.well-known/est/).

Using OpenSSL
-------------

You should be able to download the CA store at [https://localhost:8443/.well-known/est/cacerts](https://localhost:8443/.well-known/est/cacerts) and parse it with OpenSSL, like so:

```bash
curl --insecure --silent https://localhost:8443/.well-known/est/cacerts \
  | base64 --decode \
  | openssl pkcs7 -inform DER -print_certs -text -noout
```

The above command will typically produce the following output:

```
Certificate:
    Data:
        Version: 1 (0x0)
        Serial Number: 1395086926 (0x5327564e)
        Signature Algorithm: sha512WithRSAEncryption
        Issuer: C=US, CN=Jester
        Validity
            Not Before: Mar 17 20:08:46 2014 GMT
            Not After : Mar 12 20:08:46 2034 GMT
        Subject: C=US, CN=Jester
        Subject Public Key Info:
            Public Key Algorithm: rsaEncryption
            RSA Public Key: (2048 bit)
                Modulus (2048 bit):
                    00:a3:b3:2c:08:b6:ff:63:1e:07:ea:6b:79:9a:9c:
                    c9:1d:70:85:33:d0:d5:b7:4d:b7:91:dc:58:b8:5c:
                    bb:56:ce:4d:9c:5a:a1:ad:74:14:61:a9:9a:34:0b:
                    cd:bc:37:ed:09:e2:f9:7c:e9:e8:85:ca:f7:35:36:
                    d4:7f:43:5f:ac:3e:a6:0c:52:8e:9c:45:09:6e:36:
                    ab:15:8b:ee:b5:c8:9d:86:bc:d7:1c:09:f2:86:40:
                    62:f3:49:7b:62:e4:45:de:c1:a6:5c:64:c3:2d:b4:
                    68:0a:57:fd:75:c1:b6:0c:ac:a1:0a:df:c0:68:0c:
                    8e:e6:83:a0:a3:c0:53:77:66:24:84:b6:06:80:c7:
                    6e:80:1f:8f:6e:a9:0c:5f:e2:eb:1a:68:e2:a7:9e:
                    2d:e3:21:bd:62:4a:2d:12:d7:a8:60:07:be:ba:2d:
                    94:6d:18:1a:da:ef:22:bd:70:50:11:f9:0b:af:e2:
                    b4:54:6c:d5:48:b5:37:78:2d:37:20:64:bf:9e:31:
                    04:9d:30:b3:9e:d2:e9:21:07:96:47:e6:52:4d:d2:
                    44:2c:d1:77:52:54:72:2f:d0:7a:59:e0:17:8e:6b:
                    67:b3:2d:08:1a:e7:b1:73:33:d0:32:15:63:9f:1c:
                    83:d4:c9:0e:6b:bf:61:bc:9a:c7:d3:f4:4c:62:28:
                    41:71
                Exponent: 65537 (0x10001)
    Signature Algorithm: sha512WithRSAEncryption
        6c:5e:68:d1:60:77:ba:9d:6d:4b:55:59:0c:bf:20:97:b3:e4:
        e4:34:21:25:7e:03:1b:6a:e3:4d:8b:3a:07:72:90:da:39:1a:
        e7:41:ae:ce:96:08:87:27:27:21:e9:dd:7c:c4:1c:ae:2b:b0:
        ba:ba:b9:4e:20:87:e7:54:7d:cd:de:98:8b:38:3e:26:37:bd:
        d9:58:00:94:c7:5d:4b:73:97:93:01:c1:27:72:6b:7c:24:82:
        58:39:38:c1:6f:aa:2d:1d:b1:f5:09:7f:81:b2:53:81:37:7f:
        41:fe:d6:d3:53:ae:3a:01:a1:c8:64:3c:36:78:7a:63:18:33:
        a7:f5:e0:7b:e3:c4:2a:7a:89:e1:bd:01:49:fc:2a:2b:1f:9d:
        87:8d:21:14:5c:1f:45:09:2f:af:ae:c4:02:1b:ed:4d:3e:a3:
        af:89:ce:ab:ae:e7:26:a2:aa:16:0c:80:f9:1d:9e:0f:cb:15:
        ae:c2:ea:69:ba:5b:49:d2:f5:f7:36:b4:6f:b1:1d:11:c0:71:
        ff:bf:ef:7b:a4:a3:6f:d1:24:79:71:3c:47:e4:9f:e5:d3:33:
        3c:4a:0b:d9:2e:49:80:76:5d:aa:d8:39:f7:df:bd:71:f2:6f:
        ad:7c:71:d2:41:48:f2:13:80:69:19:18:85:c6:e1:0f:fe:84:
        36:06:a2:cc
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
