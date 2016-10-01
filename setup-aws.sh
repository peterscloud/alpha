#!/bin/bash

aws --version  >/dev/null 2>&1

if [ ! "$?" -eq "0" ]
then
  pip --help >/dev/null 2>&1
  if [ ! "$?" -eq "0" ]
  then
    echo "installing pip"
    curl -O https://bootstrap.pypa.io/get-pip.py
    sudo -H python get-pip.py
  fi
  
  sudo -H pip install awscli
fi