
# Licensed to the Apache Software Foundation (ASF) under one
# or more contributor license agreements.  See the NOTICE file
# distributed with this work for additional information
# regarding copyright ownership.  The ASF licenses this file
# to you under the Apache License, Version 2.0 (the
# "License"); you may not use this file except in compliance
# with the License.  You may obtain a copy of the License at
#
#     http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.

# Dockerfile for installing the necessary dependencies for building Hadoop.
# See BUILDING.txt.

FROM ubuntu:focal
ARG OS_IDENTIFIER=ubuntu-20-04

WORKDIR /root

SHELL ["/bin/bash", "-o", "pipefail", "-c"]

#####
# Disable suggests/recommends
#####
RUN echo APT::Install-Recommends "0"\; > /etc/apt/apt.conf.d/10disableextras
RUN echo APT::Install-Suggests "0"\; >>  /etc/apt/apt.conf.d/10disableextras

ENV DEBIAN_FRONTEND noninteractive
ENV DEBCONF_TERSE true

# hadolint ignore=DL3008
RUN apt-get -q update \
    && apt-get -q install -y --no-install-recommends \
    ant \
    apt-utils \
    bats \
    build-essential \
    bzip2 \
    clang \
    cmake \
    curl \
    doxygen \
    findbugs \
    fuse \
    g++ \
    gcc \
    git \
    gnupg-agent \
    gosu \
    libbcprov-java \
    libbz2-dev \
    libcurl4-openssl-dev \
    libfuse-dev \
    libkrb5-dev \
    libprotobuf-dev \
    libprotoc-dev \
    libsasl2-dev \
    libsnappy-dev \
    libssl-dev \
    libtool \
    locales \
    make \
    maven \
    openjdk-8-jdk \
    pinentry-curses \
    pkg-config \
    rsync \
    shellcheck \
    software-properties-common \
    sudo \
    unzip \
    valgrind \
    wget \
    zlib1g-dev \
    && apt-get clean \
    && rm -rf /var/lib/apt/lists/*

######
# Set env vars required to build Hadoop
######
ENV MAVEN_HOME /usr
# JAVA_HOME must be set in Maven >= 3.5.0 (MNG-6003)
ENV JAVA_HOME /usr/lib/jvm/java-8-openjdk-amd64
ENV FINDBUGS_HOME /usr

###
# Avoid out of memory errors in builds
###
ENV MAVEN_OPTS -Xms256m -Xmx1536m

# Skip gpg verification when downloading Yetus via yetus-wrapper
ENV HADOOP_SKIP_YETUS_VERIFICATION true


COPY docker-entrypoint.sh /usr/local/bin/
ENTRYPOINT ["/usr/local/bin/docker-entrypoint.sh"]
