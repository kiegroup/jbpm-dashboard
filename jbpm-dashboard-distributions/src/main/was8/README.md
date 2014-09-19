Deployment onto Websphere 8
=============================

Please follow the next steps in order to deploy the application on Websphere.

Prerequisites
--------------------------------

The jBPM Dashboard requires the jBPM History log's database tables to exist. So, it's **MANDATORY** to deploy the
jBPM Human Task console (or a superset, i.e: kie-wb) first. Otherwise, the jBPM Dashboard will not be initialized
correctly, will not be possible to display its key performance indicators and you are certain to see some database
exceptions on the log, something similar to _ERROR: relationship «processinstancelog» does not exists_.


Configure a data source
--------------------------------

The jBPM Dashboard also requires a data source which **MUST BE** configured against the same database used by kie-wb
in order to get access to the jBPM's history log. To create this data source, follow the next steps:

* Open the WebSphere's Administration Console _http://127.0.0.1:9060/ibm/console_

   Then login (if you have administrative security setup)

* Create the JDBC provider

  - Left side panel, click on _Resources > JDBC > JDBC Providers_
  - Select the appropriate scope and click on the _New_ button.
  - Fill out the form. For non-listed database types (i.e: H2, Postgres & Mysql) you need to provide the path to the JDBC driver jar file plus the following class name:

          +------------+-------------------------------------------------------------+
          | Database   |  Implementation class name                                  |
          +------------+-------------------------------------------------------------+
          | H2         | org.h2.jdbcx.JdbcDataSource                                 |
          | Postgres   | org.postgresql.xa.PGXADataSource                            |
          | Mysql      | com.mysql.jdbc.jdbc2.optional.MysqlConnectionPoolDataSource |
          +------------+-------------------------------------------------------------+

   When you finish, click _Ok_. If there are no data entry errors, you should be back at the list of JDBC Providers, where you should now see your new provider displayed.

* Create the data source

  - Left side panel, click on _Resources > JDBC > Data sources_
  - Select the appropriate scope and click on the _New_ button.
  - Fill out the creation form. Set the _JNDI name_ as, for instance, _jdbc/dashbuilder_. Click _Next_.
    (must match the data source defined in the _WEB-INF/etc/hibernate.cfg.xml.xml_ file contained in the _dashbuilder.war_)
  - Select the existing JDBC provider you created. Click _Next_.
  - Keep clicking _Next_ until _Finish_.
  - Save to master configuration.
  - Edit the datasource you just created and click on the _Custom properties_ link.
  - Edit and fill the appropriate values required to set-up the connection. This depends on the database type.

           +------------+------------------------------------------------------+
           | Database   | Datasource custom properties                         |
           +------------+------------------------------------------------------+
           | H2         | URL, user, password                                  |
           | Postgres   | serverName, databaseName, portNumber, user, password |
           | Mysql      | serverName, databaseName, port, user, password       |
           +------------+------------------------------------------------------+


Security settings
------------------------------

The following settings are required in order to enable the container managed authentication mechanisms provided by the app. server.

Go to **_Security > Global security_**

   Ensure the option _Enable Application security_ is checked.

Go to **_Users and groups > Manage groups_**

   Create 5 groups: admin, analyst, developer, manager, user

Go to **_Users and groups > Manage users_**

   Create a single user with the groups defined above.

jBPM Dashboard Deployment
----------------------------

**Deploy the WAR file**

  - Left side panel click on *Applications > Application types > Websphere enterprise applications*
  - Click on _Install_, select the *jbpm-dashbuilder_was_8.war* file from your local filesystem. Click _Next_
  - From here, you will be asked with several deployments settings.
  - You'll need to select the datasource created above as the datasource to be used by the application.
  - **DO NOT FORGET** to set <code>dashbuilder</code> as the webapp context path since that path is referred by some kie-wb modules.
  - Click _Next_ until finished.

**App. settings**

Go to _Applications > Application types > Websphere enterprise applications > dashbuilder app > Security role to user/group mapping_

   - Check all the app. roles.
   - Click on _Map Special Subjects_ and select the _All Authenticated in Application's Realm_ option.

  Once deployed you can start/stop the application from the Websphere console.

  Once started you should be able to access the application by typing the following URL: _http://http://localhost:9080/dashbuilder_

  You can sign in with any of the users defined above.

  You can create process instances and tasks from the jBPM Human Task console and later on refresh the jBPM Dashboard to
  see how the different key performance monitoring indicators are updated.



