Distribution Builder
==========================

To generate all the distribution files go to the jbpm-dashboard root directory and type the following command:

    $ mvn clean install -Dfull -DskipTests

Currently, the following artifacts are generated:

* **jbpm-dashbuilder-jboss-as7.war:**  Product distribution for JBoss EAP 6.1 and JBoss AS 7.1 servers.

  Read the [src/main/jbossas7/README.md](https://github.com/droolsjbpm/jbpm-dashboard/blob/master/jbpm-dashboard-distributions/src/main/jbossas7/README.md) file to get more information.

* **jbpm-dashbuilder-tomcat-7.war:**  Product distribution for Apache Tomcat 7 server.

  Read the [src/main/tomcat7/README.md](https://github.com/droolsjbpm/jbpm-dashboard/blob/master/jbpm-dashboard-distributions/src/main/tomcat7/README.md) file to get more information.
