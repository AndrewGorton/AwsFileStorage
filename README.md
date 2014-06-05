AwsFileStorage
====================

Simple DropWizard microservice for accessing AWS-backed storage.

Compile
=======
  `mvn clean package`

Run
===
Create a file with the following contents

```
#!/bin/bash

export AWS_ACCESS_KEY=<your_access_key_goes_here>
export AWS_SECRET_ACCESS_KEY=<your_secret_access_key_goes_here>
export AWSFILESTORAGE_BUCKET=<a_bucket_you_control>

java -jar target/AwsFileStorage-1.0.0-SNAPSHOT.jar server
```

Test
====
  * `curl -v -F file=@source.jpg http://localhost:8080/awsfilestorage/a.jpg`
  * `curl -v http://localhost:8080/awsfilestorage/`
  * `curl -v -X DELETE http://localhost:8080/awsfilestorage/a`


Development Environment
=======================

  * MacOS => 10.9.2
  * `mvn -version` => `Maven 3.1.1`
  * `java -version` => `Java 1.7.0_45`