# -*- mode: ruby -*-
# vi: set ft=ruby :

Vagrant.configure(2) do |config|
  config.vm.box = "bento/ubuntu-16.04"
  config.vm.network "forwarded_port", guest: 8080, host: 8080
  config.vm.synced_folder "data", "/vagrant_data"
  config.vm.synced_folder "build/libs", "/install"
  config.vm.provider "virtualbox" do |vb|
    vb.name = "bookhost"
    # Display the VirtualBox GUI when booting the machine
    vb.gui = false
  
    # Customize the amount of memory on the VM:
    vb.memory = "1024"
  end

  config.vm.provision "shell", inline: <<-SHELL
    sudo apt-get install -y openjdk-8-jdk
    if [[ ! -e /vagrant_data/apache-tomee-1.7.4-jaxrs.tar.gz ]]; then
      wget -nv http://mirror.symnds.com/software/Apache/tomee/tomee-1.7.4/apache-tomee-1.7.4-jaxrs.tar.gz
      mv apache-tomee-1.7.4* /vagrant_data
    fi
    mkdir -p /opt/tomee
    cd /opt/tomee
    rm -rf *
    tar zxvf /vagrant_data/apache-tomee-1.7.4-jaxrs.tar.gz
    mv apache*/* .
    rm -rf webapps/*
    cp /install/bookhost.war webapps
    cd /opt/tomee/bin
    echo export CATALINA_OPTS="-Dbook=/opt/tomee/webapps/bookhost/song.mp3" > /opt/tomee/bin/setenv.sh
    sudo ./catalina.sh stop
    sudo ./catalina.sh start
  SHELL
end
