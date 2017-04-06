FROM java:8
ENV JAVA_MEMORY_DEMO_HOME=/usr/local/java-memory-demo
RUN mkdir /usr/local/java-memory-demo
WORKDIR /usr/local/java-memory-demo
ADD target/java-memory-demo-1.0-SNAPSHOT.jar /usr/local/java-memory-demo
RUN ls /usr/local/java-memory-demo
# Shit run cmd:
# docker run -e JAVA_OPTIONS="-Xmx128m" --memory-swap=0 --memory-swappiness=0 -m 100m --rm blademainer/java-memory-demo
# Worked well with cmd:
# docker run -e JAVA_OPTIONS="-Xmx128m" --memory-swap=0 --memory-swappiness=0 -m 256m --rm blademainer/java-memory-demo
CMD java -XX:+PrintFlagsFinal -XX:+PrintGCDetails ${JAVA_OPTIONS} -classpath java-memory-demo-1.0-SNAPSHOT.jar com.xiongyingqi.memory.MemoDemo
