MySQL Driver configuration
-------------------------

Create file `module.xml`

```xml
<?xml version="1.0" encoding="UTF-8"?> 
<module xmlns="urn:jboss:module:1.1" name="com.mysql">
  <resources>
    <resource-root path="mysql-connector-java-5.1.34-bin.jar"/>
  </resources>
  <dependencies>
    <module name="javax.api"/>
    <module name="javax.transaction.api"/>
  </dependencies>
</module>
```

and store `module.xml` file and `mysql-connector-java-5.1.34-bin.jar` in folder

```
jboss-as-7.1.1.Final/modules/com/mysql/main
```

Add `<driver>` section inside section `<drivers>` in file

```
jboss-as-7.1.1.Final/standalone/configuration/standalone.xml
```

```xml
<drivers>
 ...
 <driver name="mysql" module="com.mysql">
  <driver-class>com.mysql.jdbc.Driver</driver-class>
  <xa-datasource-class>com.mysql.jdbc.jdbc2.optional.MysqlXADataSource</xa-datasource-class>
 </driver>
 ...
</drivers>
```

Create MySQL database
-------------------------

Open a command line and navigate to the root directory of this project.

```
$ cd resources
$ mysql -u root -p < db.sql
$ mysql -u bialekygd -p -D bialekygd < structure.sql
$ mysql -u bialekygd -p -D bialekygd < data.sql
```

Start JBoss WildFly with the Web Profile
-------------------------

The following shows the command line to start the server with the web profile:
```
jboss-as-7.1.1.Final/bin/standalone.sh
```

Build and Deploy
-------------------------

1. Make sure you have started the JBoss Server as described above.
2. Open a command line and navigate to the root directory of this project.
3. Type this command to build and deploy the archive:
```
mvn clean package wildfly:deploy
```
This will deploy `ear/target/bialek-ygd-ear.ear` to the running instance of the server.

If something goes wrong with connection to running server simply run 

```
mvn clean package
```

and copy `ear/target/bialek-ygd-ear.ear` to `jboss-7.1.1.Final/standalone/deployments`

Access the application 
---------------------

The application will be running at the following URL <http://localhost:8080/bialek-ygd>.
