/*
*  Copyright (c) 2005-2013, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
*
*  WSO2 Inc. licenses this file to you under the Apache License,
*  Version 2.0 (the "License"); you may not use this file except
*  in compliance with the License.
*  You may obtain a copy of the License at
*
*    http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing,
* software distributed under the License is distributed on an
* "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
* KIND, either express or implied.  See the License for the
* specific language governing permissions and limitations
* under the License.
*/

package org.wso2.carbon.launcher.bootstrapLogging;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.wso2.carbon.launcher.utils.Constants;
import org.wso2.carbon.launcher.utils.Utils;

import java.io.*;
import java.util.Properties;

/**
 * Convenience class for configuring log4j.logging to append to
 * the configured log4j log.  This could be used for bootstrap logging
 * prior to start of the framework.
 */
public class BootstrapLogManager {
    static Logger log = Logger.getLogger(BootstrapLogManager.class);

    public static void setBootstrapLogger() {
        Properties log4jProperties = new Properties();

        FileInputStream fis = null;
        try {
            fis = new FileInputStream(Utils.getRepositoryConfDir() + File.separator + Constants.LOG4J_FILE);
            log4jProperties.load(fis);
        } catch (IOException e) {
            log.error("Could not initialize : " + Constants.LOG4J_FILE + " File");
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException ioe) {
                    ioe.printStackTrace();
                }
            }
        }
        PropertyConfigurator.configure(log4jProperties);
    }

}