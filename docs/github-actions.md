# Description

This project initially had no CI framework, so we decided to add one to automate and streamline the work flow.

# What does the pipeline do

The pipeline has multiple steps. First it checks out the code -> It then sets up java -> Then it validates the graddle wrapper
-> It then builds the project with gradle -> Then it saves and uploads the created artifact.

# Running the pipeline

The pipeline will run on every push or pull request to the master branch.
