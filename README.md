Rest Example
============

Setup
-----

````
$ git clone https://github.com/kmcorbett/restx.git
$ cd restx
````

Build 
-----

Clean build: 
````
$ mvn -U clean install 
````

Compile only:
````
$ mvn compile
````

Start server
------------ 

````
$ mvn exec:java -Dexec.mainClass=restx.RestExample 
````

Test REST API
-------------

````
$ curl http://localhost:9001/things/list
{"count":0,"things":[]}
$ curl http://localhost:9001/things/delete 
{"status":"-1"}
$ curl http://localhost:9001/things/create 
{"status":"0"}
$ curl http://localhost:9001/things/create 
{"status":"0"}$
$ curl http://localhost:9001/things/delete 
{"status":"0"}$
$ curl http://localhost:9001/things/list 
{"count":1,"things":[{"id":"0","name":"temp name","date":"<unknown>"}]}
````
