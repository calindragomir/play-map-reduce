# Use a base image with Java 11
FROM openjdk:11-jdk

# Set environment variables for Hadoop version and download URL
ENV HADOOP_VERSION 3.3.6
ENV HADOOP_DOWNLOAD_URL https://archive.apache.org/dist/hadoop/common/hadoop-$HADOOP_VERSION/hadoop-$HADOOP_VERSION.tar.gz

# Create a directory for Hadoop installation
RUN mkdir -p /opt/hadoop

# Download and extract Hadoop
RUN curl -fsSL "$HADOOP_DOWNLOAD_URL" -o /tmp/hadoop.tar.gz \
    && tar -xf /tmp/hadoop.tar.gz -C /opt/hadoop --strip-components=1 \
    && rm /tmp/hadoop.tar.gz

# Set environment variables for Hadoop home and add Hadoop to PATH
ENV HADOOP_HOME /opt/hadoop
ENV PATH $HADOOP_HOME/bin:$PATH

# Copy Hadoop configuration files
COPY config/* $HADOOP_HOME/etc/hadoop/

# Create HDFS directories
RUN mkdir -p /tmp/hadoop-root/dfs/data /tmp/hadoop-root/dfs/name \
    && hdfs namenode -format

# Expose HDFS ports
EXPOSE 9000 9870

# Start HDFS
CMD ["hdfs", "datanode"]
