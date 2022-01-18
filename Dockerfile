FROM gradle:jdk17 as builder
COPY --chown=gradle:gradle . /home/gradle/fxgame
WORKDIR .
RUN gradle dist --no-daemon
RUN unzip ./build/distributions/CartridgeScaping-*-MVP.zip -d /tmp/fxgame

FROM ubuntu:18.04
COPY --from=builder /tmp/fxgame/image/ /fxgame
RUN apt-get update && apt-get install --no-install-recommends -y xorg libgl1-mesa-glx && rm -rf /var/lib/apt/lists/*
WORKDIR /fxgame
CMD ./bin/fxgame