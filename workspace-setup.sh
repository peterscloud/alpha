#!/bin/bash

sudo apt-get update

#install gcc
sudo apt-get install -y g++ gcc make gdb gdbserver

#zlib is required by ruby bundle install
sudo apt-get install -y zlib1g-dev


#nodejs required by Jekyll
sudo apt-get install -y nodejs

#other utilities
sudo apt-get install -y net-tools jq

#groff is used by aws-cli
sudo apt-get install -y groff

#Jekill setup
sudo apt-get install -y ruby-full
sudo gem install bundler
bundle install



./setup-aws.sh
