# Description

This project initially had no CI framework, so we decided to add one that can be integrated simply into our project, Github actions. 

# Why have a CI pipeline?

The pipeline allows us to Automate the build and test steps to ensure that any changes being made to our master branch are reliable.
This method of using github actions also allows us to seemlessly upload the artifacts from the build into our repository.

# What does the pipeline do

These are the steps of the pipeline:
```
steps:
    - uses: actions/checkout@v3
    - uses: actions/setup-java@v3
      with:
        java-version: '11'
        distribution: 'adopt'
    - name: Validate Gradle wrapper
      uses: gradle/wrapper-validation-action@e6e38bacfdf1a337459f332974bb2327a31aaf4b
    - name: Build with Gradle
      uses: gradle/gradle-build-action@0d13054264b0bb894ded474f08ebb30921341cee
      with:
        arguments: build
    - uses: actions/upload-artifact@v3
      with:
        name: Package
        path: build/libs
```
- First it checks out the code

- It then sets up java

- Then it validates the gradle wrapper
    - This means it validates the checksum of the gradle, and if an unknown Gradle wrapper 
    jar is found, it will fail the validation.

- It then builds the project with gradle

- Then it saves and uploads the created artifact.
    - This is useful because it allows us to store and share data between jobs when the workflow is completed.

# Running the pipeline

The pipeline will run on every push or pull request to the master branch.
