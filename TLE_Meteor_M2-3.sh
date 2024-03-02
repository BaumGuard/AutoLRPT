#!/bin/bash

###########################################################
#                                                         #
# This script updates tle TLE data for Meteor-M2 3        #
# and writes it into the file ~/.predict/predict.tle      #
#                                                         #
# The source of the TLE data is Celestrak:                #
# https://celestrak.org/NORAD/elements/gp.php?CATNR=57166 #
#                                                         #
###########################################################


### CONFIG PART ###

## Automatical update of TLE data ##
#  The TLE data of the satellite might change over time,
#  so you can choose whether this script should should
#  update the TLE data in regular intervals of 24 hours

#  Possible values:
#  - 0 : No automatical update (just update once)
#  - 1 : Automatical update every 24 hours
autoupd=0



### OPERATIONAL PART ###

while true; do
    tle=`curl https://celestrak.org/NORAD/elements/gp.php?CATNR=57166`

    if [[ -z $(grep "METEOR-M2 3" ~/.predict/predict.tle) ]]; then
        echo "$tle" >> ~/.predict/predict.tle

    else
        sat_name=`sed '1q;d' <<< "$tle"`
        tle_line1=`sed '2q;d' <<< "$tle"`
        tle_line2=`sed '3q;d' <<< "$tle"`

        start_line=`grep -n "METEOR-M2 3" ~/.predict/predict.tle | grep -o -E '[0-9]+' | head -n1`

        start_line=$(($start_line + 1))
        sed -i "${start_line}s/.*/${tle_line1}/" ~/.predict/predict.tle
        start_line=$(($start_line + 1))
        sed -i "${start_line}s/.*/${tle_line2}/" ~/.predict/predict.tle
    fi


    if [[ $autoupd -eq 1 ]]; then
        sleep 24h
    else
        break
    fi

done
