# -*- mode: ruby -*-
# vi: set ft=ruby :

Vagrant.configure(2) do |config|
  config.vm.box = "centos/7"
  config.vm.network "forwarded_port", guest: 8080, host: 8080
  config.vm.synced_folder "dependencies", "/dependencies"
  config.vm.synced_folder "build-output", "/build-output"
  config.vm.synced_folder "war", "/home/vagrant/bookhost"
  config.vm.provider "virtualbox" do |vb|
    vb.name = "bookhost"
    vb.gui = false
    vb.memory = "1024"
  end

  config.vm.provision "file", source: "scripts/tomee.service", destination: "/build-output/tomee.service"
  config.vm.provision "shell", inline: <<-SHELL

set -xeuo pipefail

function fetch
{
  if [[ ! -e /dependencies/$1 ]]; then
     wget $2 -O /dependencies/$1
  fi
}

export MIRROR=mirror.symnds.com
export TOMEE=apache-tomee-1.7.4-jaxrs.tar.gz

echo "export JAVA_HOME=/usr/lib/jvm/jre" >> /home/vagrant/.bash_profile

sudo -i 
yum install -y java-1.8.0-openjdk-devel.x86_64 unzip

#setup tomee user
sudo groupadd tomee || true
sudo useradd -s /bin/false -g tomee -d /opt/tomee tomee || true

#install tomee
fetch $TOMEE http://$MIRROR/sofware/Apache/tomee/tomee-1.7.4/$TOMEE

rm -r /opt/tomee || true
mkdir -p /opt/tomee
cd /opt/tomee
tar zxf /dependencies/$TOMEE
mv apache*/* .
rm -rf webapps/*
echo export CATALINA_OPTS="-Dbook=/opt/tomee/webapps/bookhost/song.mp3" > /opt/tomee/bin/setenv.sh


#reset permissions
sudo chgrp -R tomee /opt/tomee
sudo chown -R tomee /opt/tomee

#install tomee service
cp /build-output/tomee.service /etc/systemd/system/tomee.service

#install the app if it's already been built
if [[ -e /build-output/bookhost.war ]]; then
  cp /build-output/bookhost.war /opt/tomee/webapps
fi

sudo systemctl daemon-reload
sudo systemctl enable tomee
sudo systemctl start tomee

# install google chrome
fetch google-chrome-stable_current_x86_64.rpm https://dl.google.com/linux/direct/google-chrome-stable_current_x86_64.rpm

# stupid yum will return a bad exit status here if it's already installed
sudo yum install -y /dependencies/google-chrome-stable_current_x86_64.rpm || true

#install chrome driver
fetch chromedriver_linux64.zip http://chromedriver.storage.googleapis.com/2.22/chromedriver_linux64.zip
cd /dependencies
sudo unzip chromedriver_linux64.zip
sudo mv chromedriver /usr/local/bin

SHELL
end
