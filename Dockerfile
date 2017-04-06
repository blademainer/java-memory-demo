FROM java:8
ENV JAVA_MEMORY_DEMO_HOME=/usr/local/java-memory-demo
RUN mkdir /usr/local/java-memory-demo
WORKDIR /usr/local/java-memory-demo
ADD target/java-memory-demo-1.0-SNAPSHOT.jar /usr/local/java-memory-demo
RUN ls /usr/local/java-memory-demo
CMD ["java","-classpath","java-memory-demo-1.0-SNAPSHOT.jar","com.xiongyingqi.memory.MemoDemo"]
