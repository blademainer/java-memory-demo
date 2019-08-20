#!/usr/bin/env bash

script_dir="$(dirname $0)"

docker volume create --name maven-repo
docker run --rm --name maven-java-memory-demo -it -v maven-repo:/root/.m2 -v "$(pwd)":/usr/src/mymaven -w /usr/src/mymaven maven mvn install
cp ${script_dir}/fabric8-Dockerfile ${script_dir}/target/Dockerfile
cd ${script_dir}/target
cp java-memory*.jar app.jar

commit_id=`git rev-parse HEAD`
docker build . -t blademainer/java-memory-demo:${commit_id}

# PREVENT A CONTAINER FROM USING SWAP
# If --memory and --memory-swap are set to the same value, this prevents containers from using any swap.
# This is because --memory-swap is the amount of combined memory and swap that can be used,
# while --memory is only the amount of physical memory that can be used.
docker run --rm --name java-memory-demo --memory-swap=256m --memory-swappiness=0 -m256m  blademainer/java-memory-demo:${commit_id}
