 There are 2 runner file:
 1. ParallelRun --> Runs Test in parallel
 2. GroupRun.xml --> Run Test group in parallel
 
 mvn command:
 
 mvn test -D suiteXmlFile=TestNGRunner/{XmlFileName}
 
 e.g.
 mvn test -D suiteXmlFile=TestNGRunner/ParallelRun.xml
 mvn test -D suiteXmlFile=TestNGRunner/GroupRun.xml