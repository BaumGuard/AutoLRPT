# AutoLRPT
AutoLRPT is a small Bash script that allows you to receive **LRPT images** from **Meteor M2-3** and **Meteor M2-4** automatically by using **mlrpt**. The script only runs on **Linux** and can e. g. be used on a **Raspberry Pi**.<br />

## Dependencies
* `mlrpt`
  * Download **mlrpt** from [5B4AZ.com](http://5b4az.org)
  * Downlaod **mlrpt** from [GitHub.com](https://github.com/dvdesolve/mlrpt)
* `predict`

## Presupposed setup
You can follow the following instructions in case you are using a **Raspberry Pi** with **Raspberry Pi OS**. For other distros the setup should work similar but the names of the required packages might differ.<br />
### mlrpt
On the mentioned OS you first have to install a bunch of packages that are needed by `mlrpt`:
```bash
sudo apt install cmake make automake autoconf libtool rtl-sdr librtlsdr-dev
```
The repository of AutoLRPT contains a modified version of mlrpt. It adds an option in the config files to set the pattern of the file name, so that [AutoLRPT-Slideshow](https://www.github.com/BaumGuard/AutoLRPT-Slideshow) can handle the image files better.
1. Move `mlrpt.tar.gz` to the home folder
   ```bash
   mv mlrpt.tar.gz ..
   cd
   ```
2. Unzip the package:
   ```bash
   tar -xzf mlrpt.tar.gz
   ```
3. Now enter the folder of mlrpt and execute the following commands after another:
   ```bash
   cd mlrpt

   ./autogen.sh
   ./configure
   make
   sudo make install
   ```

Unfortunately, `mlrpt` sometimes replaces the config file with one from `/usr/share/examples/mlrpt/config`. To avoid that you can delete the config files in `/usr/share/mlrpt/examples/config` and copy the config file `~/mlrpt/default.cfg` to `/usr/share/mlrpt/examples/config`:
```bash
sudo rm -r /usr/local/share/examples/mlrpt
sudo scp /home/user/mlrpt/default.cfg /usr/local/share/examples/mlrpt/config
```
### predict<br />
For most distributions, `predict` is available in the official respositories. In case you can not find it there, you can also download it from [here](https://www.qsl.net/kd2bd/predict.html)<br />
On **Raspberry Pi OS** and other Debian/Ubuntu-based distros you can install it with the following command:
```bash
sudo apt install predict
```
If you can't install `predict` from the repositories you can also download it from [here](https://www.qsl.net/kd2bd/predict-2.3.1.tar.gz)
```bash
wget https://www.qsl.net/kd2bd/predict-2.3.1.tar.gz
```
Unpack the *tar.gz* file
```bash
cd
tar -xzf predict-2.3.1.tar.gz
cd predict-2.3.1
```
Run the script `configure`
```bash
./configure
```
The script first checks for the existence of some libraries. If you haven't installed them already you can do so
```bash
sudo apt-get install libncurses5-dev libncursesw5-dev libasound2-dev
```
## Setup of AutoLRPT
Clone AutoLRPT into your home directory:<br />
```bash
cd
git clone https://github.com/BaumGuard/AutoLRPT
```
Enter the folder of AutoLRPT:
```bash
cd AutoLRPT
```
Make the scripts executable:
```bash
chmod +x AutoLRPT_M2-3 AutoLRPT_M2-4 set_location tle_update
```
Execute the script `set_location` and enter your **name/callsign**, **latitude**, **longitude** and **altitude**:
```bash
./set_location
```

### Updating the TLE data
The so called **TLE data** is needed to calculate the position of a satellite at a given time and therefore also the pass times.<br />

The script `tle_update` downloads the current TLE data for Meteor-M2 3 and Meteor-M2 4 from *CelesTrak* and writes it to the file `~/.predict/predict.tle`.<br />
<br />
You can choose whether the TLE should be updated automatically in regular intervals of 24 hours or if you want to update the TLE data manually. To update the TLE data in regular intervals, open the script and set the variable `autoupd` to `1`.<br />
Then execute the script:<br />
```bash
./tle_update
```

## Usage
Start `AutoLRPT` **from inside its directory**:<br />
#### Meteor M2-3
```bash
./AutoLRPT_M2-3 &
```
#### Meteor M2-4
```bash
./AutoLRPT_M2-4 &
```
The `&` will execute the script in the background.
<br />
<br />
Now `mlrpt` should start automatially when **Meteor M2 3** or **Meteor M2-4** passes over. You don't need to start `AutoLRPT` again manually afer the pass, since `AutoLRPT` will automatically schedule the next pass.<br />
<br />
**Be sure to exit the console on which you have started `AutoLRPT` by running**<br />
```bash
exit
```
<br />**Otherways `AutoLRPT` will be stopped!**
<br />

If you want to stop AutoLRPT<br />
#### Meteor M2-3
```bash
killall AutoLRPT_M2-3
```
#### Meteor M2-4
```bash
killall AutoLRPT_M2-4
```

### Start on boot
If you want to start AutoLRPT automatically when booting the Raspberry Pi you can add a `cronjob` for both AutoLRPT scripts
```bash
crontab -e
```
Append these two lines on the bottom of the file
```
@reboot ~/AutoLRPT/AutoLRPT_M2-3
@reboot ~/AutoLRPT/AutoLRPT_M2-4
```
Now both AutoLRPT scripts should start automatically after every boot.

## Additional options
You can change the following settings in `M2-3.conf` and `M2-4.conf` according to your needs:
* **Minimum elevation**<br />
  Only record passes that have at least the given elevation.
  * Variable: `min_elev`
  * Possible values: `0 - 90`
* **Recording in the evening (Meteor M2-3)**<br />
  Due to a worse solar illumation by the sun, the images recorded in the evenings usually are rather dark and don't show that many details. You can choose whether you want `AutoLRPT` to record passes in the evenings or not
  * Variable: `evening_rec`
  * Possible values:
    * `0`: Evening recording disabled
    * `1`: Evening recording enabled
* **Recording in the night (Meteor M2-4)**<br />
 Due to a worse solar illumation by the sun, the images recorded in the night usually are rather dark and don't show that many details. You can choose whether you want `AutoLRPT` to record passes in the evenings or not
  * Variable: `night_rec`
  * Possible values:
    * `0`: Evening recording disabled
    * `1`: Evening recording enabled
 
* **Autoflip**<br />
  Because **Meteor M2 3** and all the other LEO satellites will pass over from South to North in the evenings, the images will be upside down by default, but `mlrpt` can flip them. You can choose whether you want `mlrpt` to flip the images of evening passes automatically
  * Variable: `autoflip`
  * Possible values:
    * `0`: Autoflip disabled
    * `1`: Autoflip enabled
* **Automatically update color channels**<br />
  Throughout the year **Meteor M2 3** can change its color channels a few times. `AutoLRPT` can retrieve the currently active color channels from [this webpage](https://github.com/happysat/Meteor-M-N-2-3-Satellite-Operational-Status) and adjust the color channels in `mlrpt`'s `default.cfg` automatically. This will happen before every pass. You can choose whether you want `AutoLRPT` to update the color channels automatically or not
  * Variable: `upd_channels`
  * Possible values:
    * `0`: Automatical update of color channels disabled
    * `1`: Automatical update of color channels enabled
* **Logging**<br />
  `AutoLRPT` can log information about the passes and the recording status in the file `AutoLRPT.log`<br /><br />
  The logs will have the folloing format:<br />
  `PassDate PassTime SatelliteName MaxElevation Azimuth Status`<br/>
  Please note that the date and time are in **UTC**

  The `Status` gives some information about the success of the reception:
  * `IMAGE_RECORDED`: If an image was received and saved (successful execution of `mlrpt`)
  * `NO_IMAGE_RECORDED`: If no image was received (successful execution of `mlrpt`)
  * `NO_RECORDING`: If the user disabled the recording under certain circumstances (`min_elev`, `evening_rec`) - No exection of `mlrpt`
  * `NO_SDR_CONNECTED`: If `mlrpt` stops because no SDR was connected (unsuccessful execution of `mlrpt`)
  * `ERROR`: Any other errors
 
  You can choose whether you want `AutoLRPT` to log the passes or not:
  * Variable: `logging`
  * Possible values:
    * `0`: Logging disabled
    * `1`: Logging enabled
