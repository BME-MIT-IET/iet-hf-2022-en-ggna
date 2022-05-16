# SonarQube

SonarQube is an static code analyses for projects, which can easily check bugs, code duplications, errors and etc.

# SonarQube installation and configuration

1. For SonarQube [Java 11](https://www.oracle.com/java/technologies/javase/jdk11-archive-downloads.html) is recommended. After installation we need to setup right environment variables to make it work properly.

2. After, download [SonarQube](https://www.sonarqube.org/downloads/), unzip it and add to PATH using corresponding system location in bin folder.

3. We can check if it works using command with "StartSonar", using cmd with administrator to the actual SonarQube directory.

4. Build takes some time, but after successfull execution wwe can access the SonarQube on a localhost. It will ask for an username and password which by defaault is admin, admin, but it'll ask to change it after sign in. 

5. we can access the unique token under user>>my account>>security, under security user should name the token and press generate which will generate the token.

# Adding SonarScanner and cxx plugin

1. Download the [sonarscanner](https://docs.sonarqube.org/latest/analysis/scan/sonarscanner/) and add the bin folder location
 to PATH.

2. it's netter to downloar lates versioon of a sonarQube and [cxx plugin](https://github.com/SonarOpenCommunity/sonar-cxx/releases/tag/latest-snapshot) file: sonar-cxx-plugin-2.1.0.198.jar

3. unzip the jar file and copy it to the SonarQube extensions>>plugins folder. 

# Configuring sonar scanner

to configure the server, we need to add sonar-project.properties file, with project name. and copy the lines below.
``` java
# must be unique in a given instance
sonar.projectKey=my:project
# mandatory: files handled by the _cxx plugin_
sonar.cxx.file.suffixes=.h,.cpp
```
If there will be error about binary files add this line to the properties:
sonar.java.binaries=target

As I explained above using authenticationToken, the server can be started Run a sonar scanner analysis on your local project with following command:

```
sonar-scanner -D Asonar.login=Token
```

If build was successfull. we can see the outcome, and followw the corresponding link to see the results. There we can find an analysys.

