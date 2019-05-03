# jenkins-admin-helper-scripts
Groovy scripts for helping out Jenkins Admins

### jenkins-env-variables-restore
In Jenkins instances whose version >= 2.120, it has been observed, on restart, that the environment variables are wiped off from the jenkins master .

NOTE :- The above mentioned wipe-out of environment variables happens very sporadically. To be a little more accurate, I guess it has occured 4 times out of the last 30 restarts in my our development jenkins masters.

This has often led to someone from our team perform the mundane task of copy-pasting env variables from one of the production jenkins masters onto the development jenkins master .

The above mentioned script is an attempt to spare developers from the above-mentioned tedious task.
