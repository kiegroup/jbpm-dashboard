/**
 * Copyright (C) 2012 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jbpm.dashboard.i18n;

import org.jboss.dashboard.i18n.KpisFileConverter;
import org.jboss.dashboard.i18n.WorkspaceFileConverter;
import org.jboss.dashboard.i18n.XmlToBundleConverter;
import java.io.File;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;

/**
 * Tool for extracting all the i18n literals from the jBPM Dashboard UI and saves them into classic resource bundles.
 * The final goal behind this tool is to be able to pass the generated bundles to a translation service like Zanata.
 */
public class jBPMDashboardBundleExtractor {

    public static void main(String[] args) throws Exception{
        String rootDir = System.getProperty("user.dir");

        // Process the Showcase KPIs file
        XmlToBundleConverter converter = new KpisFileConverter();
        converter.bundleDir = new File(rootDir, "src/main/resources/org/jbpm/dashboard/kpis");
        converter.xmlFile = new File(rootDir, "src/main/webapp/WEB-INF/deployments/jbpmKPIs_v2.kpis");
        Map<Locale,Properties> bundles = converter.extract(); // Extract bundles from source XML
        converter.write(bundles); // Write literals to bundle files.

        // Process the Showcase Workspace file
        converter = new WorkspaceFileConverter();
        converter.bundleDir = new File(rootDir, "src/main/resources/org/jbpm/dashboard/workspace");
        converter.xmlFile = new File(rootDir, "src/main/webapp/WEB-INF/deployments/jbpmWorkspace_v2.workspace");
        bundles = converter.extract(); // Extract bundles from source XML
        converter.write(bundles); // Write literals to bundle files.
    }
}