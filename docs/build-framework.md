# Description

We upgraded the existing gradle framework since it was severely outdated, and added Maven build framework as well.

# What is a build framework?

"What is gradle? what is Maven?" are some common questions. They are both build automation tools, which automate the application creation. 

The application building process consists of compiling the code, linking it, and packaging it too. 

# Why did we use it?

As mentioned above, the application building process contains many steps, because of this, there are many factors and discrepencies that can affect this. To curb or lessen the risk of inconsistency, we use build automation tools, they keep our process consistent. 


# The main parts of Gradle

There are a few main things to know in Gradle, including but not limited to:
 - Build scripts
    - A build script defines the project itself, and it's tasks. It also consists of the dependencies and any plugins used should be declared there as well.
 - Tasks
    - These are kind of actions, which is just a block of code that it should execute.

    In our case, this is how our task looked:
    ```
    tasks.named('wrapper') {
	description = 'Generates gradlew[.bat] scripts'
	gradleVersion = '7.4.2'
	distributionUrl = "https://services.gradle.org/distributions/gradle-${gradleVersion}-all.zip"
    } 
    ```
    We can see that we aren't actually defining a new task, but overwriting an existing one, that is because defining new tasks was deprecated a couple of versions ago, so this is the new way to do it. 
    This task is for the gradle wrapper, it will create the gradle wrapper jar that can be found in the `gradle/wrapper` directory. 

Gradle offers a couple of very useful commands we can use.

## In case of the common tasks:


This runs all checks and builds the assemblies:
```bash 
$ gradle build
```

This cleans the build directory and any output:
```bash 
$ gradle clean
```

This creates the artifacts:
```bash 
$ gradle jar
```

This runs the defined tests:
```bash 
$ gradle test
```

## In case of project reporting:

The project reporting commands came in handy, especially when dealing with dependencies.

To list the dependencies of the project:
```bash 
$ gradle dependencies
```

To generate a full report which can be viewed online, you could add the `--scan` option to the end of the task. We used it the most in this context:
```bash 
$ gradle dependencies --scan 
```

