jBPM Dashboard Builder
=======================

[Dashboard Builder](https://github.com/droolsjbpm/dashboard-builder) is a web application for the visual composition of
dashboards. A dashboard is composed by a set of business indicators (aka Key Performance Indicators) which are feed from
data coming from heterogeneous sources of information like databases or CSV files.

The jBPM Dashboard is a customization of the Dashboard Builder project. This customization has the following goals in mind:

* To provide users with a full featured dashboard with a bunch of key performance indicators built on top of the jBPM history log.
* To replace the whole Dashbuilder's look&feel with jBPM's branding.
* To offer a tight integration between Dashbuilder and jBPM at the data connectivity layer.

If you discover pitfalls, tips and tricks not described in this document,
please update it using the [markdown syntax](http://daringfireball.net/projects/markdown/syntax).


Building the project
--------------------

If you want to build the project please, proceed as indicated:

1. Prerequisites:

  This guide assumes you have Java JDK 1.6 (set as JAVA_HOME), and Maven 3.0.4+ (set as MAVEN_HOME) in your system.
  The <code>java</code> and <code>mvn</code> commands must be added to the executable's path as well.

2. Open a terminal window, go to the project root directory and type the following command:

        $ mvn clean install -Dfull -DskipTests

  This command compiles, builds and runs the application. It'll last a few minutes (but only for the first time) because
  the maven build process needs to download a lot of third-party libraries.


JBoss Application Server
-------------------------

To deploy the jBPM dashboard to WildFly 8, first see "Building the project", then proceed as indicated:

1.  Go to the <code>jbpm-dashboard-distributions/target</code> directory and get the jbpm-dashbuilder-wildfly8.war

2.  Then take a look at the following [guide](https://github.com/droolsjbpm/jbpm-dashboard/blob/master/jbpm-dashboard-distributions/src/main/wildfly8/README.md).




