# AutoLRPT
AutoLRPT is a small program that allows you to receive LRPT images from Meteor M2 automatically by using mlrpt.<br />

## Dependencies
- `mlrpt`: http://5b4az.org/ / https://github.com/dvdesolve/mlrpt<br />
- `predict`<br />

## Presupposed setup
AutoLRPT uses `predict` to get the pass times for Meteor M2 and automatically start ´mlrpt´ when Meteor-M2 passes over.<br />
### mlrpt<br />
If you haven't installed `mlrpt` yet, you can download it from one of the links mentioned above. Both pages also provide good documentation about the compilation, installation and setup.<br />
### predict<br />
For most distributions, `predict` is available in the official respositories. In case you can not find it there, you can also download it from [here](https://www.qsl.net/kd2bd/predict.html)<br />
<br />
After having installed `predict`, you can navigate into the folder `.predict` and open `predict.qth`:<br />
`cd .predict`<br />
`nano predict.qth`<br /><br />
Enter your name/callsign, your coordinates and your height.<br /><br />
Entering the longitude works differently in `predict` than you might expect. The longitude angle starts at 0° W with 0° and goes around the globe until it reaches 0° W again at 360°.<br />
If your longitude is negative when you are living in the west, you have to make it positive: `+ -> -`<br />
If your longitude is positive when you are living in the east, you have to subtract it from 360°: `360°-x`<br />
<br />
Run the script `tle_update` to download the TLE data of Meteor M2.
<br />
`./tle_update`
<br />
<br />
`predict` should now be able to work.<br />
<br />
## Setup of AutoLRPT<br />
Clone AutoLRPT into your home directory:<br />
`cd`<br />
`git clone https://github.com/BaumGuard/AutoLRPT`<br />
<br />
## Usage
Navigate into the directory of `AutoLRPT`...<br />
`cd AutoLRPT`<br />
...and start the script`AutoLRPT` by running<br />
`./ AutoLRPT &`<br />
<br />
Now `mlrpt` should start automatially when Meteor M2 passes over. You don't need to start `AutoLRPT` again manually afer the pass, since `AutoLRPT` will automatically schedule the next pass.<br />
<br />
If you want to stop AutoLRPT run `killall AutoLRPT`.
<br />

### Automatically update the TLE data<br />
Change the value of the variable `tle_autoupd` in `tle_update` to `true` and start the script in the background:<br />
`./tle_update &`

## Additional options

All the settings are located and explained in the top part of the script `AutoLRPT`!
