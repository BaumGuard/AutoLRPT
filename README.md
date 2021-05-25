# AutoLRPT
AutoLRPT is a small program that allows you to receive LRPT images from Meteor M2 automatically using mlrpt.</b>

## Dependencies</b>
- `mlrpt`: http://5b4az.org/ / https://github.com/dvdesolve/mlrpt</b>
- `predict`</b>
- `at`</b>
- `JRE`</b>

## Presupposed setup</b>
AutoLRPT uses `predict` to get the pass times for Meteor M2 and `at` to schedule when `mlrpt` should be started</b>
</b>
**mlrpt**</b>
If you haven't installed `mlrpt` yet, you can download it from one of the links mentioned above. Both pages also provide good documentation about the compilation, installation and setup.</b>
</b>
**predict**</b>
For most distributions, `predict` is available in the official respositories. In case you can not find it there, you can also download it from here: https://www.qsl.net/kd2bd/predict.html</b>
</b>
After having installed `predict`, you can navigate into its directory:</b>
`cd ~/.predict`</b>
Then you can open the file `predict.qth` and enter your callsign, your coordinates and your height above the sea level:</b>
`nano predict.qth`</b>
Entering the longitude works different in `predict` than you might expect. The angle starts at 0° W with 0° and goes around the globe until it reaches 0° W again at 360°.</b>
</b>
Unfortunately, Meteor M2 isn't registered in `predict` by default. Consequently you have to add it yourself by retrieving the TLE data (https://www.n2yo.com/satellite/?s=40069) and replace one satellite in `predict.tle` by the TLE data. Above the TLE data from Meteor M2 you have to add the line "METEOR-M2".</b>
`predict` should now be able to work.</b>
</b>
**at**</b>
Like `predict` you can install `at` from the official repositories as well.</b>
To activate `at` run the following two commands:</b>
`sudo systemctl start atd.service`</b>
`sudo systemctl enable atd.service`</b>
</b>
</b>
## Setup of AutoLRPT</b>
There is no need to compile `AutoLRPT` yourself, since there is already an executable jar file.</b>
</b>
Clone the repository to your home directory:</b>
`cd`</b>
`git clone https://github.com/BaumGuard/AutoLRPT`</b>
</b>
</b>
## Usage
Go into the directory of `AutoLRPT`...</b>
`cd AutoLRPT`</b>
...and start `AutoLRPT` by running</b>
`java -jar AutoLRPT.jar`</b>
</b>
Now `AutoLRPT` has retrieved the starting time (AOS) of the upcoming pass from `predict` and scheduled the bash-script `start-mlrpt` with `at` at the given time.</b>
</b>
</b>
## How it works
When starting `AutoLRPT`, it retrieves the time of the upcoming pass from `predict` and stores it in the file `passes`. `AutoLRPT` now reads the first line of `passes`and creates a schedule for the bash-script `start-mlrpt` to be started at the time given in the first line using `at`. The combination of the java program and the bash-script is a loop that starts `AutoLRPT.jar` again when `mlrpt` has stopped. That way, the next pass is being scheduled automatically without the need to start `AutoLRPT` manually.
