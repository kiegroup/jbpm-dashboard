#!/usr/bin/env bash  
#
# Copyright (C) 2012 JBoss Inc
#
# Licensed under the Apache License, Version 2.0 (the "License");
# you may not use this file except in compliance with the License.
# You may obtain a copy of the License at
#
#       http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.
#

#
# Startup script for the jBPM Dashboard Builder demo.

#
# JAVA_HOME: environment variable indicates the JDK installation to use, If not set, java (from the PATH) 
# will be used.
#

# JETTY_PORT=8080 uncomment this line if you want to use a port different than the default port 8080
# JAVA_OPTIONS="-Xms2024 -Xmx3048 -XX:MaxPermSize=512m"
JAVA_OPTIONS=

if [ "$JAVA_HOME" ]
  then
    JAVA="$JAVA_HOME/bin/java"    
  else
    JAVA=$(which java)	
  fi

if [ -z "$JAVA" ]
  then
    echo "Cannot find a valid JDK, please configure the JAVA_HOME environment variable."
    exit 1
fi


if [ "$JETTY_PORT" ] 
then
  JAVA_OPTIONS="$JAVA_OPTIONS -Djetty.port=$JETTY_PORT"
fi

echo "*****************************************************************************"
echo "*"
echo "* Starting the jBPM Dashboard Builder Demo"
echo "*"
echo "* Using JAVA_HOME=$JAVA_HOME"
echo "* $JAVA $JAVA_OPTIONS -jar start.jar"
echo "*"
echo "*"
echo "*****************************************************************************"

echo "Populating the jBPM Process Dashboard with some sample data."
$JAVA -cp ./db/h2*.jar org.h2.tools.RunScript -url "jdbc:h2:./db/dashbuilder" -user "dashbuilder" -password "dashbuilder" -script "./db/jbpm_demo_1000_h2.sql"
$JAVA $JAVA_OPTIONS $JAVA_OPTS -jar jetty-runner.jar jbpm-dashbuilder-demo.war
