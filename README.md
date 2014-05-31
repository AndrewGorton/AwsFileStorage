SimpleDropWizardEcho
====================

A simple dropwizard.io echo service for use with testing.

Compile
=======
  `mvn clean package`

Run
===
  `java -jar target/SimpleDropWizardEcho-1.0-SNAPSHOT.jar server`

Test
====
  * `curl http://localhost:8080/echo`
  * `curl "http://localhost:8080/echo?echo=Something+To+Echo"`

Development Environment
=======================

  * MacOS => 10.9.2
  * `mvn -version` => `Maven 3.1.1`
  * `java -version` => `Java 1.7.0_45`