#!/bin/bash

if [[ ! -e .vbguest-installed ]]; then
  touch .vbguest-installed
  vagrant plugin install vagrant-vbguest
fi
vagrant up
vagrant ssh <<-SHELL
  cd /home/vagrant/bookhost
  ./gradlew clean war test
  sudo cp build/libs/bookhost.war /opt/tomee/webapps
  sudo chown tomee:tomee /opt/tomee/webapps/bookhost.war
  sudo cp build/libs/bookhost.war /build-output
  sudo systemctl restart tomee
SHELL
