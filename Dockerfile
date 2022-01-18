FROM gradle:jdk17 as builder
COPY --chown=gradle:gradle . /home/gradle/CartridgeScaping
WORKDIR .
RUN gradle dist --no-daemon
RUN unzip ./build/distributions/CartridgeScaping-*-MVP.zip -d /tmp/CartridgeScaping

FROM ubuntu:18.04
COPY --from=builder /tmp/CartridgeScaping/image/ /CartridgeScaping
RUN apt-get update && apt-get install --no-install-recommends -y xorg libgl1-mesa-glx && rm -rf /var/lib/apt/lists/*
WORKDIR /CartridgeScaping
CMD ./bin/CartridgeScaping