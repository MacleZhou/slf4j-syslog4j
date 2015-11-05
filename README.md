A binding from SLF4J to Syslog4J, the main objective being to implement
the **"unix_syslog"** protocol of Syslog4J.

Although Java already has several sophisticated logging frameworks the
Lunix or Unix operating system also has a sophisticated logging system.

slf4j-syslog4j allows use of the superb SLF4J API to log from Java apps
directly into the syslog system without any altering or configuration of TCP/UDP.

This allows Java apps to co-exist nicely with other Lunix/Unix services and
have logging messages controlled by the syslog system, as it was
meant to be!

**System Properties**

To configure slf4j-syslog4j there are several system properties to be set:

`syslog4j.level = DEBUG | INFO | WARN | any syslog level`
`syslog4j.facility = USER | DAEMON | KERN | any syslog facility`
`syslog4j.ident = <your app id>`

