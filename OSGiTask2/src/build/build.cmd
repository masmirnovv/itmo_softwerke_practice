cd ../main/java
javac -cp ../../lib/*.jar -d ../../build hello/*.java service/*.java client/*.java
cd ../../build
jar --create --file HelloServiceBundle.jar --manifest ../manifests/SERVICE.MF "hello/HelloI.class" "hello/Hello.class" "service/HelloService.class"
jar --create --file HelloClientBundle.jar --manifest ../manifests/CLIENT.MF "client/HelloClient.class"
rmdir /q/s hello
rmdir /q/s client
rmdir /q/s service