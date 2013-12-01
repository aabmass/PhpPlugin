PhpPlugin
=========

A basic, working example of a plugin for SweetHttpd that can extend SweetHttpd to serve pages generated with php.

This example accomplishes this in 62 lines of code.

Compilation
----------

1. Make sure that you link/copy the actual SweetHttpd jar into a folder in the project directory called "lib". The dist.sh script will use this to compile against.
2. run the dist.sh script: ```$ ./dist.sh ```

That will create a runnable jar in the jar folder.
