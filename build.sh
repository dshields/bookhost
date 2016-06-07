#!/bin/bash

vagrant plugin install vagrant-vbguest
vagrant up
vagrant ssh <<-SHELL
  cd /home/vagrant/bookhost
  ./gradlew clean war test
  sudo cp build/libs/bookhost.war /build-output
  sudo systemctl restart tomee
SHELL
