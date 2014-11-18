Deployment onto Tomcat
==========================

Please follow the next steps in order to deploy the application.

Prerequisites
--------------------------------

The jBPM Dashboard requires the jBPM History log's database tables to exist. So, it's **MANDATORY** to deploy the
jBPM Human Task console (or a superset, i.e: kie-wb) first. Otherwise, the jBPM Dashboard will not be initialized
correctly, will not be possible to display its key performance indicators and you are certain to see some database
exceptions on the log, something similar to _ERROR: relationship «processinstancelog» does not exists_.

Configure a data source
--------------------------------

The jBPM Dashboard also requires a data source which **MUST BE** configured against the same database used by kie-wb
in order to get access to the jBPM's history log. The _dashbuilder.war/META-INF/context.xml_  file contains the
data source configuration used by dashbuilder. Edit & change it according to your needs before deploy the WAR (see next).

jBPM Dashboard Deployment
---------------------------------------

Get a WAR file (e.g. jbpm-dashbuilder-tomcat-7.war) that matchs the kie-wb version installed and deploy it on Tomcat.

**DO NOT FORGET** to set <code>dashbuilder</code> as the context path since that path is referred by some kie-wb modules.
One easy way to do so is leverage the Tomcat autodeployment feature by copying the <code>jbpm-dashbuilder-tomcat-7.war</code>
as <code>dashbuilder.war</code> under the Tomcat's <code>webapps</code> deployment folder. That way Tomcat will take the war
file name as the application's context path.

Authentication and authorization
---------------------------------

The jBPM Console and the jBPM dashboard module uses container managed authentication and authorization.

Two roles must be defined: "admin" and "analyst".

In a default Tomcat installation, edit <code><tomcat_home>/conf/tomcat-users.xml</code> and add the following sample lines:

         <role rolename="admin"/>
         <role rolename="analyst"/>
         <user username="root"   password="root"  roles="admin"/>
         <user username="admin"  password="admin"  roles="admin"/>
         <user username="demo"   password="demo"  roles="analyst"/>

The <code>root</code> user is an special user that is granted all the permissions.

Once started, open a browser and type the following URL:
<code>http://localhost:8080/dashbuilder</code>. A login page should be displayed.

You can sign in with any of the users defined above.


Single Sign On
---------------------------------

In order to enable SSO between the jBPM Dashboard and the jBPM Console, please, edit the
<code>[tomcat-7]/conf/server.xml</code> file and make sure the <code>SingleSignOn</code> valve is added under the Host tag.

    ...
      <Host name="localhost"appBase="webapps" unpackWARs="true" autoDeploy="true">
        <Valve className="org.apache.catalina.authenticator.SingleSignOn" />
        <Valve className="org.kie.integration.tomcat.JACCValve" />
      </Host>
    ...
