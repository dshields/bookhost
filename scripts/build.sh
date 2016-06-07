#!/bin/bash 

set -xeuo pipefail

vagrant ssh <<-SHELL
  cd /home/vagrant/bookhost
  ./gradlew clean war test
  sudo cp build/libs/bookhost.war /build-output
  systemctl restart tomee
SHELL
