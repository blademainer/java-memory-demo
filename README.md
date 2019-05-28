[![Build Status](https://travis-ci.org/blademainer/java-memory-demo.png)](https://travis-ci.org/blademainer/java-memory-demo)

# java-memory-demo

## Quick to use:
```shell
chmod +x build_docker_and_run_demo.sh && ./build_docker_and_run_demo.sh
```

## Bad run cmd:
```shell
docker run --name java-memory-demo --memory-swap=0 --memory-swappiness=0 -m 64m -e JAVA_OPTIONS="-Xmx128m -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=/jvmdump/" -v `pwd`/jvmdump:/jvmdump -d blademainer/java-memory-demo
```
## Good run cmd:
```shell
docker run --name java-memory-demo --memory-swap=0 --memory-swappiness=0 -m 256m -e JAVA_OPTIONS="-Xmx128m -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=/jvmdump/" -v `pwd`/jvmdump:/jvmdump -d blademainer/java-memory-demo
```
