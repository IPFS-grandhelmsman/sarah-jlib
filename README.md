# sarahos-java

## Build

Install [java jdk1.8.0_221](https://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)

Install [Maven](http://maven.apache.org/download.cgi)

Set build enviroment
```shell
export M2_HOME=/usr/local/apache-maven-3.6.1
export JAVA_HOME=/usr/local/jdk1.8.0_221
export CLASSPATH=.:$JAVA_HOME/lib/dt.jar:$JAVA_HOME/lib/tools.jar:$M2_HOME/lib
export PATH=$M2_HOME/bin:$JAVA_HOME/bin:$PATH
```

Build project
```shell
cd sarah-jlib

cd sarah-common
mvn clean install -DskipTests

cd ../sarah-node
mvn clean package

cp ./target/sarah-node-0.0.1-SNAPSHOT.jar ${SARAH_CONSOLE}/jnode # SARAH_CONSOLE is root directory of https://github.com/IPFS-grandhelmsman/sarah-console
# or run java -jar sarah-node/target/sarah-node-0.0.1-SNAPSHOT.jar

```
