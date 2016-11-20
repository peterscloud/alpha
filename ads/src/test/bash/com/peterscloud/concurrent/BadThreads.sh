#!/bin/bash

basedir=.

max=$1
if [ -z "$max" ]
then
  max=1
fi

for (( i=0 ; i<$max ; i=i+1 ))
do
  out=$(java -classpath $basedir/target/test-classes/ com.peterscloud.concurrent.BadThreads)
  
  if [ ! "$out" == "Mares do eat oats." ]
  then
    echo "$out"
    exit 1
  fi
done

exit 0