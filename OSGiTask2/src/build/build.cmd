cd ../main/java
javac -cp ../../lib/*.jar -d ../../build ru/itmo/masmirnov/hello/*.java ru/itmo/masmirnov/service/*.java ru/itmo/masmirnov/client/*.java
cd ../../build
jar --create --file HelloServiceBundle.jar --manifest ../manifests/SERVICE.MF "ru/itmo/masmirnov/hello/HelloI.class" "ru/itmo/masmirnov/hello/Hello.class" "ru/itmo/masmirnov/service/HelloService.class"
jar --create --file HelloClientBundle.jar --manifest ../manifests/CLIENT.MF "ru/itmo/masmirnov/client/HelloClient.class"
rmdir /q/s ru