# AutoLRPT
AutoLRPT is a small program that allows you to receive LRPT images from Meteor M2 automatically by using mlrpt.<br />

## Dependencies
- `mlrpt`: http://5b4az.org/ / https://github.com/dvdesolve/mlrpt<br />
- `predict`<br />
- `at`<br />

## Presupposed setup
AutoLRPT uses `predict` to get the pass times for Meteor M2 and `at` to schedule when `mlrpt` should be started<br />
<br />
**mlrpt**<br />
If you haven't installed `mlrpt` yet, you can download it from one of the links mentioned above. Both pages also provide good documentation about the compilation, installation and setup.<br />
<br />
**predict**<br />
For most distributions, `predict` is available in the official respositories. In case you can not find it there, you can also download it from here: https://www.qsl.net/kd2bd/predict.html<br />
<br />
After having installed `predict`, you can navigate into the folder `.predict` and open `predict.qth`:<br />
`cd .predict`<br />
`nano predict.qth`<br />
Enter your name/callsign, your coordinates and your height:<br />
Entering the longitude works differently in `predict` than you might expect. The longitude angle starts at 0° W with 0° and goes around the globe until it reaches 0° W again at 360°.<br />
If your longitude is negative when you are living in the west, you have to make it positive: `+ -> -`<br />
If your longitude is positive when you are living in the east, you have to subtract it from 360°: `360°-x`<br />
<br />
Unfortunately, Meteor M2 isn't registered in `predict` by default. Consequently you have to add it yourself by retrieving the TLE data (https://www.n2yo.com/satellite/?s=40069) from it and replace one satellite in `predict.tle` by the TLE data. Above the TLE data from Meteor M2 you have to add the line `METEOR-M2`.<br />
`predict` should now be able to work.<br />
<br />
**at**<br />
Like `predict` you can install `at` from the official repositories as well.<br />
To activate `at` run the following two commands:<br />
`sudo systemctl start atd.service`<br />
`sudo systemctl enable atd.service`<br />
<br />
## Setup of AutoLRPT<br />
Clone AutoLRPT into your home directory:<br />
`cd`<br />
`git clone https://github.com/BaumGuard/AutoLRPT`<br />
<br />
## Usage
Navigate into the directory of `AutoLRPT`...<br />
`cd AutoLRPT`<br />
...and start the script`scheduler` by running<br />
`bash scheduler`<br />
<br />
Now `mlrpt` should start automatially when Meteor M2 passes over. You don't need to start `AutoLRPT` again manually afer the pass, since `AutoLRPT` will automatically schedule the next pass.<br />
<br />
You can view the schedule by running `atq`<br />
If you want to abord passes you can delete the job number with `atrm`<br />
<br />
## Starting at boot<br />
To start `AutoLRPT` at boot you have to execute the script `autostart`:<br />
`sudo bash autostart`<br />
Shortly after you have started the script, `nano` will open a file. In the fourth line you will find a file path. Replace `/root` by the path of your home folder. After that, press `Ctrl+O` to save it and `Ctrl+X` to exit.<br />
