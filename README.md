## build notes

```sh
mkdir crac-files
docker build -f src/main/docker/Dockerfile.jvm -t demo20230223-counting-on-crac .
docker run -it --privileged -v $(pwd)/crac-files:/opt/crac-files --rm --name demo20230223-counting-on-crac demo20230223-counting-on-crac
java -XX:CRaCCheckpointTo=/opt/crac-files $JAVA_OPTS -jar $JAVA_APP_JAR
```

in another shell

```sh
docker exec -it -u root demo20230223-counting-on-crac /bin/bash
ps -u root
# typically java is PID 9, used below
jcmd 9 JDK.checkpoint
```

to restore

```sh
java -XX:CRaCRestoreFrom=/opt/crac-files
```