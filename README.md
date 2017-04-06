[![Build Status](https://travis-ci.org/blademainer/java-memory-demo.png)](https://travis-ci.org/blademainer/java-memory-demo)

# java-memory-demo

# Bad run cmd:
```shell
docker run -e JAVA_OPTIONS="-Xmx128m" --memory-swap=0 --memory-swappiness=0 -m 100m --rm blademainer/java-memory-demo
```
# Good run cmd:
```shell
docker run -e JAVA_OPTIONS="-Xmx128m" --memory-swap=0 --memory-swappiness=0 -m 256m --rm blademainer/java-memory-demo
```
