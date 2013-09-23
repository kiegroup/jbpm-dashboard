Deployment onto Tomcat
==========================

Please follow the next steps in order to deploy the application.

Prerequisites
----------------------------------------

The jBPM Dashboard connects by default to an H2 datasource identified with the following JNDI name <code>jdbc/jbpm</code>
as specified in the following [file](https://github.com/droolsjbpm/jbpm-dashboard/blob/master/jbpm-dashboard-distributions/src/main/tomcat7/META-INF/context.xml)

The <code>jdbc/jbpm</code> datasource is shared between the jBPM Console and the jBPM Dashboard which in turns it's used to access
the jBPM's history log. So, it's **MANDATORY** to deploy the jBPM Human Task console (or a superset, i.e: kie-wb) first.
Otherwise, the jBPM Dashboard will not be initialized correctly and will not be possible to display its key performance indicators.

jBPM Dashboard Deployment
---------------------------------------

Get a WAR file (e.g. jbpm-dashbuilder-tomcat-7.war) that matchs the kie-wb version installed and deploy it to Tomcat.

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
