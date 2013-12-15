/*
 * Copyright 2001-2009 The Apache Software Foundation.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.juddi.tckrunner;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;

/**
 * This application will run the majority of the jUDDI project's UDDI Technical
 * Compatibility Kit (TCK) tests. Some of the tests that are used are slightly
 * different than when ran with jUDDI's build process.
 *
 * @author <a href="mailto:alexoree@apache.org">Alex O'Ree</a>
 * @since 3.3
 */
public class App {

        public static void main(String[] args) throws Exception {
                System.out.println("_________________________________________________");
                System.out.println("Running! this can take anywhere from 2-5 minutes!");
                System.out.println("Configure options using uddi.xml and tck.properties");
                System.out.println("java -Duddi.client.xml=uddi.xml -jar juddi-tck-runner-{VERSION}-SNAPSHOT-jar-with-dependencies.jar");
                System.out.println("_________________________________________________");
                System.out.println();

                if (!new File("tck.properties").exists()) {
                        System.out.println("tck.properties was not found! I give up!");
                        System.exit(1);
                }
                JUnitCore junit = new JUnitCore();
                Result result = junit.run(org.apache.juddi.v3.bpel.BPEL_010_IntegrationTest.class,
                        //the bpel tests really only test wsdl to uddi
                        // org.apache.juddi.v3.bpel.BPEL_010_IntegrationTest.class,
                        // org.apache.juddi.v3.bpel.BPEL_020_IntegrationTest.class,

                        org.apache.juddi.v3.tck.JUDDI_010_PublisherIntegrationTest.class,
                        org.apache.juddi.v3.tck.UDDI_010_PublisherIntegrationTest.class,
                        org.apache.juddi.v3.tck.UDDI_020_TmodelIntegrationTest.class,
                        org.apache.juddi.v3.tck.UDDI_030_BusinessEntityIntegrationTest.class,
                        //org.apache.juddi.v3.tck.UDDI_030_BusinessEntityLoadTest.class,
                        org.apache.juddi.v3.tck.UDDI_040_BusinessServiceIntegrationTest.class,
                        //org.apache.juddi.v3.tck.UDDI_040_BusinessServiceLoadTest.class,
                        org.apache.juddi.v3.tck.UDDI_050_BindingTemplateIntegrationTest.class,
                        org.apache.juddi.v3.tck.UDDI_060_PublisherAssertionIntegrationTest.class,
                        org.apache.juddi.v3.tck.UDDI_070_FindEntityIntegrationTest.class,
                        org.apache.juddi.v3.tck.UDDI_080_SubscriptionIntegrationTest.class,
                        //note that this is different, there is an IntegrationTest version
                        //however it's for hosting our own mail server and reconfiguring juddi
                        org.apache.juddi.v3.tck.UDDI_090_SubscriptionListenerExternalTest.class,
                        org.apache.juddi.v3.tck.JUDDI_091_RMISubscriptionListenerIntegrationTest.class,
                        org.apache.juddi.v3.tck.JUDDI_100_ClientSubscriptionInfoIntegrationTest.class,
                        org.apache.juddi.v3.tck.UDDI_110_FindBusinessIntegrationTest.class,
                        org.apache.juddi.v3.tck.UDDI_120_CombineCategoryBagsFindServiceIntegrationTest.class,
                        org.apache.juddi.v3.tck.UDDI_130_CombineCategoryBagsFindBusinessIntegrationTest.class,
                        org.apache.juddi.v3.tck.UDDI_140_NegativePublicationIntegrationTest.class,
                        org.apache.juddi.v3.tck.UDDI_141_JIRAIntegrationTest.class,
                        org.apache.juddi.v3.tck.UDDI_150_CustodyTransferIntegrationTest.class,
                        org.apache.juddi.v3.tck.UDDI_160_RESTIntergrationTest.class,
                        org.apache.juddi.v3.tck.UDDI_170_ValueSetValidation.class);

                String filename = "uddi-tck-results-" + new SimpleDateFormat("yyyyMMddhhmm").format(new Date()) + ".txt";
                FileWriter fw = new FileWriter(filename);

                BufferedWriter bw = new BufferedWriter(fw);
                bw.write("UDDI-TCK Test Results generated " + new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ").format(new Date()));
                bw.newLine();
                bw.write("____________________________________________");
                bw.write("Summary");
                bw.newLine();
                bw.write("Failed Test Cases: " + result.getFailureCount());
                bw.newLine();
                bw.write("Skipped Test Cases: " + result.getIgnoreCount());
                bw.newLine();
                bw.write("Ran Test Cases: " + result.getRunCount());
                bw.newLine();
                bw.write("Time: " + result.getRunTime());
                bw.newLine();
                bw.write("-------------------------------------");
                bw.newLine();
                bw.newLine();

                for (int i = 0; i < result.getFailures().size(); i++) {
                        bw.write(result.getFailures().get(i).getTestHeader());
                        bw.newLine();
                        bw.write(result.getFailures().get(i).getDescription().getClassName());
                        bw.newLine();
                        bw.write(result.getFailures().get(i).getDescription().getMethodName());
                        bw.newLine();
                        bw.write(result.getFailures().get(i).getMessage());
                        bw.newLine();
                        //result.getFailures().get(i).getException().printStackTrace();

                        bw.write(result.getFailures().get(i).getTrace());
                        bw.newLine();
                        bw.write("____________________________________________");
                }
                System.out.println("____________________________________________");
                System.out.println("Summary");
                System.out.println("Failed Test Cases: " + result.getFailureCount());
                System.out.println("Skipped Test Cases: " + result.getIgnoreCount());
                System.out.println("Ran Test Cases: " + result.getRunCount());
                System.out.println("Time: " + result.getRunTime());
                System.out.println("-------------------------------------");
                System.out.println("Results written to " + filename);

                bw.close();
                fw.close();
                junit = null;
                System.out.println("Exit code: " + result.getFailureCount());
                System.exit(result.getFailureCount());
        }
}
