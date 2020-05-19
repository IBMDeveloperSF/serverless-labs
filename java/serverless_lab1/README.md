## Steps to finish Lab

### Step 1: Install IBMCloud CLI

### Step 2: Deploy the function to IBM Cloud
Run mvn package to create the fat jar
```
» mvn package                                                        [16:14:07]
[INFO] Scanning for projects...
[INFO] 
[INFO] ---------< com.ibmdevelopersf.serverless_lab1:serverless_lab1 >---------
[INFO] Building serverless_lab1 1.0-SNAPSHOT
[INFO] --------------------------------[ jar ]---------------------------------
[INFO] 
[INFO] --- maven-resources-plugin:2.6:resources (default-resources) @ serverless_lab1 ---
[INFO] Using 'UTF-8' encoding to copy filtered resources.
[INFO] skip non existing resourceDirectory /Users/ulidder/Documents/icloud-documents/code-upkar/serverless-labs/java/serverless_lab1/src/main/resources
[INFO] 
[INFO] --- maven-compiler-plugin:3.1:compile (default-compile) @ serverless_lab1 ---
[INFO] Changes detected - recompiling the module!
[INFO] Compiling 1 source file to /Users/ulidder/Documents/icloud-documents/code-upkar/serverless-labs/java/serverless_lab1/target/classes
[INFO] 
[INFO] --- maven-resources-plugin:2.6:testResources (default-testResources) @ serverless_lab1 ---
[INFO] Using 'UTF-8' encoding to copy filtered resources.
[INFO] skip non existing resourceDirectory /Users/ulidder/Documents/icloud-documents/code-upkar/serverless-labs/java/serverless_lab1/src/test/resources
[INFO] 
[INFO] --- maven-compiler-plugin:3.1:testCompile (default-testCompile) @ serverless_lab1 ---
[INFO] Changes detected - recompiling the module!
[INFO] Compiling 1 source file to /Users/ulidder/Documents/icloud-documents/code-upkar/serverless-labs/java/serverless_lab1/target/test-classes
[INFO] 
[INFO] --- maven-surefire-plugin:2.12.4:test (default-test) @ serverless_lab1 ---
[INFO] Surefire report directory: /Users/ulidder/Documents/icloud-documents/code-upkar/serverless-labs/java/serverless_lab1/target/surefire-reports

-------------------------------------------------------
 T E S T S
-------------------------------------------------------
Running com.ibmdevelopersf.serverless_lab1.ServerlessLab1Test
Nov 26, 2019 4:14:14 PM com.ibmdevelopersf.serverless_lab1.ServerlessLab1 main
INFO: exiting ServerlessLab1
Nov 26, 2019 4:14:14 PM com.ibmdevelopersf.serverless_lab1.ServerlessLab1 main
INFO: exiting ServerlessLab1
Nov 26, 2019 4:14:14 PM com.ibmdevelopersf.serverless_lab1.ServerlessLab1 main
INFO: exiting ServerlessLab1
Nov 26, 2019 4:14:14 PM com.ibmdevelopersf.serverless_lab1.ServerlessLab1 main
INFO: exiting ServerlessLab1
Tests run: 4, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.082 sec

Results :

Tests run: 4, Failures: 0, Errors: 0, Skipped: 0

[INFO] 
[INFO] --- maven-jar-plugin:2.4:jar (default-jar) @ serverless_lab1 ---
[INFO] Building jar: /Users/ulidder/Documents/icloud-documents/code-upkar/serverless-labs/java/serverless_lab1/target/serverless_lab1.jar
[INFO] 
[INFO] --- maven-shade-plugin:3.1.0:shade (default) @ serverless_lab1 ---
[INFO] Including com.google.code.gson:gson:jar:2.8.2 in the shaded jar.
[INFO] Replacing original artifact with shaded artifact.
[INFO] Replacing /Users/ulidder/Documents/icloud-documents/code-upkar/serverless-labs/java/serverless_lab1/target/serverless_lab1.jar with /Users/ulidder/Documents/icloud-documents/code-upkar/serverless-labs/java/serverless_lab1/target/serverless_lab1-1.0-SNAPSHOT-shaded.jar
[INFO] Dependency-reduced POM written at: /Users/ulidder/Documents/icloud-documents/code-upkar/serverless-labs/java/serverless_lab1/dependency-reduced-pom.xml
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time: 3.398 s
[INFO] Finished at: 2019-11-26T16:14:15-08:00
[INFO] ------------------------------------------------------------------------
 
```

This creates `serverless_lab1.jar` file in `./target` folder.

### Step 3:  deploy the function to IBM Cloud using the CLI
1. Ensure you are logged into IBM Cloud
```$java
» ibmcloud target                                                    [16:15:49]

                      
API endpoint:      https://cloud.ibm.com   
Region:            us-south   
User:              upkar.ibm.watson.5@gmail.com   
Account:           Upkar Lidder's Account (a086ce7d00df4423ab024b123b587e76)   
Resource group:    No resource group targeted, use 'ibmcloud target -g RESOURCE_GROUP'   
CF API endpoint:   https://api.us-south.cf.cloud.ibm.com (API version: 2.142.0)   
Org:               upkar.ibm.watson.5@gmail.com   
Space:             dev   

```

2. deploy the jar file to ibm cloud
```$xslt
» ibmcloud fn action update  helloJava target/serverless_lab1.jar --kind java:8 --main com.ibmdevelopersf.serverless_lab1.ServerlessLab1
ok: updated action helloJava

```

3. invoke the function to ensure it works
```$xslt
» ibmcloud fn action invoke helloJava -r                             
{
    "greeting": "Hello World!"
}

```

4. Let's try with a param now
```$xslt
» ibmcloud fn action invoke helloJava -r -p name "upkar lidder"
{
    "greeting": "Hello upkar lidder"
}

```
