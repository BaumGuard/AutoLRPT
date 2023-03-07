# AutoLRPT
AutoLRPT is a small Bash script that allows you to receive LRPT images from Meteor M2 automatically by using mlrpt. The script only runs on **Linux** and can e. g. be used on a Raspberry Pi.<br />

## Warning
**Since the 24th December 2022 the Russian weather satellite Meteor M2, for which this script was written, doesn't transmit LRPT images anymore. The Russian space agency Roscosmos plans to launch a successor called Meteor M2-3 but the launch was postponed multiple times in the last few years.** <br />
**I will update this script to make it work with the new satellite when the satellite has been launched and when the decoder as well as the TLE data are available.**

## Dependencies
- `mlrpt`: http://5b4az.org/ / https://github.com/dvdesolve/mlrpt<br />
- `predict`<br />

## Video tutorial
Watch my [YouTube tutorial](https://www.youtube.com/watch?v=QXLyTNqWK6M) on how to setup and configure AutoLRPT.

## Presupposed setup
AutoLRPT uses `predict` to get the pass times for Meteor M2 and automatically start `mlrpt` when Meteor-M2 passes over.<br />
### mlrpt<br />
If you haven't installed `mlrpt` yet, you can download it from one of the links mentioned above. Both pages also provide good documentation about the compilation, installation and setup.<br />
### predict<br />
For most distributions, `predict` is available in the official respositories. In case you can not find it there, you can also download it from [here](https://www.qsl.net/kd2bd/predict.html)<br />


## Setup of AutoLRPT
Clone AutoLRPT into your home directory:<br />
`cd`<br />
`git clone https://github.com/BaumGuard/AutoLRPT`<br />
<br />
Enter the folder of AutoLRPT:<br />
`cd AutoLRPT`<br />
<br />
Execute the script `set_location` and enter your name/callsign, latitude, longitude and altitude:<br />
`./set_location`<br />


### Updating the TLE data
The so called TLE data is needed to calculate the position of a satellite at a given time and therefore also the pass times.<br />
The script `tle_update` downloads the current TLE data from [here](https://www.n2yo.com/satellite/?s=40069).<br />
<br />
You can choose whether the TLE should be updated automatically in regular intervals of 24 hours or if you want to update the TLE data manually. To update the TLE data in regular intervals, open the script and set the variable `tle_autoupd` to `true`.<br />
Then execute the script:<br />
`./tle_update &`

## Usage
Start `AutoLRPT` **from inside its directory**:<br />
`./AutoLRPT &`<br />
The `&` will execute the script in the background.
<br />
<br />
Now `mlrpt` should start automatially when Meteor M2 passes over. You don't need to start `AutoLRPT` again manually afer the pass, since `AutoLRPT` will automatically schedule the next pass.<br />
<br />
**Be sure to exit the console on which you have started `AutoLRPT` by running**<br />
`exit`
<br />**Otherways `AutoLRPT` will be stopped!**
<br />

If you want to stop AutoLRPT<br />
`killall AutoLRPT`


## Additional options

All the settings are located and explained in the top part of the script `AutoLRPT`.
