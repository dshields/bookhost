#!/bin/bash

if [[ ! -e .vbguest-installed ]]; then
  touch .vbguest-installed
  vagrant plugin install vagrant-vbguest
fi
vagrant up
vagrant ssh <<-SHELL
  set -x
  cd /home/vagrant/bookhost
  ./gradlew clean war
  sudo systemctl stop tomee
  sudo rm -r /opt/tomee/webapps/bookhost*
  sudo cp build/libs/bookhost.war /opt/tomee/webapps
  sudo chown tomee:tomee /opt/tomee/webapps/bookhost.war
  sudo cp build/libs/bookhost.war /build-output
  sudo systemctl start tomee
SHELL
