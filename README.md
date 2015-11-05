A binding from SLF4J to Syslog4J, the main objective being to implement
the **"unix_syslog"** protocol of Syslog4J.

Although Java already has several sophisticated logging frameworks the
Lunix or Unix operating system also has a sophisticated logging system.

slf4j-syslog4j allows use of the superb SLF4J API to log from Java apps
directly into the syslog system without any altering or configuration of TCP/UDP.

This allows Java apps to co-exist nicely with other Lunix/Unix services and
have logging messages controlled by the syslog system, as it was
meant to be!

**Configuration**

There are only a few simple options for configuration:

1. Add system properties syslog4j.facility, syslog4j.level and syslog4j.ident

2. Add a single system property syslog4j.configurationFile with the path to a properties
file that contains the properties specified in (1)

3. Add a syslog4j.properties file to the top level of your jar file with the
properties specified in (1)

**System Properties**

To configure slf4j-syslog4j there are 3 properties to be set:

`syslog4j.level = DEBUG | INFO | WARN | any syslog level`

`syslog4j.facility = USER | DAEMON | KERN | any syslog facility`

`syslog4j.ident = <your app id>`


