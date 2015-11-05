A binding from SLF4J to Syslog4J, the main objective being to implement
the **"unix_syslog"** protocol of Syslog4J.

Although Java already has several sophisticated logging frameworks the
Lunix or Unix operating system also has a sophisticated logging system.

slf4j-syslog4j allows use of the superb SLF4J API to log from Java apps
directly into the syslog system without any altering or configuration of TCP/UDP.

This allows Java appa to co-exist nicely with other Lunix/Unix services and
have logging messages controlled by the syslog system, as it was
meant to be!