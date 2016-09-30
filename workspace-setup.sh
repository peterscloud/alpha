#!/bin/bash

sudo apt-get update

#install gcc
sudo apt-get install g++ gcc make gdb gdbserver -y

#zlib is required by ruby bundle install
sudo apt-get install zlib1g-dev


#nodejs required by Jekyll
sudo apt-get install -y nodejs

sudo apt-get install -y net-tools

#Jekill setup
sudo apt-get install -y ruby-full
sudo gem install bundler
bundle install
