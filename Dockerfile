FROM core:latest

MAINTAINER Pseudow

WORKDIR /usr/minecraft/

ENV MC_SERVER_TYPE=parkour

ENV PROJECT_NAME=altarise-cosmetics
ENV FOLDER=/usr/minecraft/plugins/

RUN bash download_artifacts.sh -C

ENV PROJECT_NAME=altarise-cosmetics
ENV FOLDER=/usr/minecraft/plugins/

RUN bash download_artifacts.sh -C

# Downloading dependencies....

RUN ls -C
RUN ls plugins/ -C

RUN echo Starting minecraft server...

ENTRYPOINT ["java", "-Xmx3072M", "-XX:+UseG1GC", "-XX:+DisableExplicitGC", "-jar", "paper.jar"]
