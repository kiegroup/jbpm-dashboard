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

* Open the WebSphere's Adminitration Console _http://127.0.0.1:9060/ibm/console_

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


jBPM Dashboard Deployment
----------------------------

Get the proper WAR file (e.g. _jbpm-dashbuilder-was-8.war_) and run the following steps:

* http://127.0.0.1:9060/ibm/console

    Then login (if you have administrative security setup)

* Deploy the WAR file

  - Left side panel click on *Applications > Application types > Websphere enterprise applications*
  - Click on _Install_, select the *jbpm-dashbuilder_was_8.war* file from your local filesystem. Click _Next_
  - From here, you will be asked with several deployments settings.
  - You'll need to select the datasource created above as the datasource to be used by the application.
  - **DO NOT FORGET** to set <code>dashbuilder</code> as the webapp context path since that path is referred by some kie-wb modules.
  - Click _Next_ until finished.

  Once deployed you can start/stop the application from the Websphere console.


Authentication and authorization
---------------------------------

Dashbuilder uses container managed authentication and authorization. The application roles are defined in the application's
<code>web.xml</code> file. In a nutshell, to get access to the aplication a new user has to be created and mapped with one
or several application roles. Next is the list of steps to achieve so from the WebSphere's Adminitration Console:

* Enable security

  - Left side panel click on *Security > Global security*
  - Section *Application security*, Check the box *Enable application security*
  - Click *Apply*, then save to master config.

   Then you have application security turned on. Now you need to map the users of your application to users within Websphere.

* Map users

  - _Applications > Enterprise Applications_ click on your application.
  - Under the _Detailed Properties_ section you will see a link _Security role to user/group mapping_. Click on it.
  - Select the roles you wish to use for authentication, _admin_ for instance.
  - Click look up users (you need to create users first, see below).
  - Click search and select users.
  - Use the arrows to move the selected users/groups to the right hand box.
  - Click ok and save to master configuration and restart the server.

* Create users

  - Just go to the left side panel and click on *Users and Groups > Manage Users*.
  - Click on the _Create_ button, fill out the form and click _Ok_.
  - The _User ID_ field is the login, the word to be used to sign into the application.
  - if you create a user with login=<code>root</code> it'll be granted with all the permissions within the application.

    Once created and mapped the user should be able to sign into the application.


User Authentication
--------------------------

The jBPM Process Dashboard can be accessed in two ways:

* As root, by selecting the jBPM Dashboard workspace at the top administration toolbar, or
* Typing the following URL: <code>http://localhost:8080/dashbuilder/workspace/jbpm-dashboard</code>

You can sign in with any of the users defined above.

You can create process instances and tasks from the jBPM Human Task console and later on refresh the jBPM Dashboard to
see how the different key performance monitoring indicators are updated.

