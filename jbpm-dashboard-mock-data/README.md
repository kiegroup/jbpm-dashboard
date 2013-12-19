Mock data for the jBPM Dashboard
=================================

After installing the BPMS product (kie-wb and jbpm-dashboard) the Process dashboard is empty since there is no
process instances created yet. Next up, a pretty simple mechanism to populate such dashboard with mock
data it's described. The mechanism is only intended for demo purposes as a quick way to fill the dashboard indicators
with full data charts.

1. First ensure the app server is stopped and the Dashbuilder database is clean an empty (no tables created yet).

        In a default EAP installation the H2 in-memory database is destroyed every time you shutdown the server. So nothing extra to do in this case.

2. Copy this file to the following directory **WEB-INF/etc/sql** under the dashbuilder WAR deployment structure.

        You have to deploy the war file first but not execute it until the file mentioned is copied.

3. Start the app. server. This script will be executed automatically.

        This is carried out by an internal Dashbuilder's database initialization mechanism.

4. Login into the tooling, navigate to the jBPM Dashboard.

        Nice charts should be displayed!


This mechanism only works for installations on H2.


