#!/usr/bin/env bash

script_dir="$(dirname $0)"

docker volume create --name maven-repo
docker run --rm --name maven-java-memory-demo -it -v maven-repo:/root/.m2 -v "$(pwd)":/usr/src/mymaven -w /usr/src/mymaven maven mvn install
cp ${script_dir}/Dockerfile ${script_dir}/target
cd ${script_dir}/target
cp java-memory*.jar app.jar

commit_id=`git rev-parse HEAD`
docker build . -t blademainer/java-memory-demo:${commit_id}

docker run --rm -d --name good-java-memory-demo --memory-swap=256m --memory-swappiness=0 -m 256m -e JAVA_OPTIONS="-Xmx128m -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=/jvmdump/" -v `pwd`/jvmdump:/jvmdump  blademainer/java-memory-demo:${commit_id} &
docker run --rm -d --name bad-java-memory-demo --memory-swap=256m --memory-swappiness=0 -m 256m -e JAVA_OPTIONS="-Xmx512m -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=/jvmdump/" -v `pwd`/jvmdump:/jvmdump  blademainer/java-memory-demo:${commit_id} &

# you should see bad-java-memory-demo is oom
docker events -f image=blademainer/java-memory-demo