# meta-pine64

Pine64 Layer for OpenEmbedded/Yocto

## Description

This is the general hardware specific BSP overlay for the Pine64 devices.

This layer aims to support as many features as possible on Pine64 devices.
Where possible the layer aims to use opensource and upstream projects
avoiding custom forks and binary solutions.

## Dependencies

This layer depends on:

* URI: git://github.com/openembedded/openembedded-core
  * branch: master
  * revision: HEAD
* URI: git://github.com/openembedded/bitbake
  * branch: master
  * revision: HEAD
* URI: git://git.yoctoproject.org/meta-arm
  * branch: master
  * revision: HEAD

## Building

Follow the usual steps to setup OpenEmbedded and bitbake.

For example, if you use poky it would go like this: 
```shell
git clone git://git.yoctoproject.org/poky && cd poky
git clone git://git.yoctoproject.org/meta-arm
git clone https://github.com/alistair23/meta-pine64.git
. oe-init-build-env 
bitbake-layers add-layer  ../meta-arm/meta-arm-toolchain/
bitbake-layers add-layer  ../meta-arm/meta-arm
bitbake-layers add-layer  ../meta-arm/meta-arm-bsp/
bitbake-layers add-layer ../meta-pine64/
```

### Pine A64-LTS

I don't have a board to test on, so this board is supported as best I can. If you have any problems please raise an issue on GitHub.

```
MACHINE=pine-a64-lts bitbake core-image-base
```

### SoPine Baseboard

```
MACHINE=sopine-a64 bitbake core-image-base
```

### Pine A64-Plus

```
MACHINE=pine-a64-plus bitbake core-image-base
```

### Other Pine64 Boards

Please raise a GitHub issue if you would like another board suppoted.

### Using systemd instead of SysVinit

To use systemd add this to your local.conf:

```
INIT_MANAGER = "systemd"
```
### Connecting to WiFi

To use WiFi add this to your local.conf:

```
DISTRO_FEATURES += "ipv4 ipv6 wifi"
IMAGE_INSTALL:append = "linux-firmware-rtl8723 wpa-supplicant"
```

### Displaying the kernel boot log on HDMI

To see the kernel boot log on the HDMI output, which is useful for debugging, change `recipes-bsp/u-boot/files/boot.cmd` to the following:

```
setenv bootargs console=tty0 console=ttyS0,115200 root=/dev/mmcblk0p2 rootwait
```

This results in adding `console=tty0` which will direct Linux to display the console on the display.

### Graphical display over HDMI or MIPI DSI

To use Wayland graphics add this to your local.conf:

```
DISTRO_FEATURES += "wayland opengl"
PACKAGECONFIG:append:pn-mesa = " gallium lima kmsro"
```

Then build Weston using:

```
MACHINE=sopine-a64 bitbake core-image-weston
```

To add X11 support as well add this to your local.conf:

```
DISTRO_FEATURES += "x11"
```

## Demos

Below are some demos of this layer being used with Pine64 boards.

## Hardware video acceleration examples

### mpv, no rotation

https://www.youtube.com/watch?v=1m416FXKJWY

```
mpv <file> -vo=gpu --hwdec=drm --drm-device=/dev/dri/card0
```


### Gstreamer, OpenGL rotation

https://www.youtube.com/watch?v=gNg-Jkcy0MY

```
gst-launch-1.0 filesrc location=<file.mp4> ! qtdemux name=demux  demux.audio_0   ! queue ! decodebin ! audioconvert ! audioresample ! autoaudiosink  demux.video_0 ! queue ! h264parse ! v4l2slh264dec ! glimagesink rotate-method=1 render-rectangle="<0,0,720,1440>
```

### Gstreamer, rotate using sun8i-rotate (not usable: wrong colors, bad performance)

https://www.youtube.com/watch?v=Tj3qFjfC2dc

```
gst-launch-1.0 filesrc location=<file.mp4> ! qtdemux name=demux  demux.audio_0   ! queue ! decodebin ! audioconvert ! audioresample ! autoaudiosink  demux.video_0   ! queue ! h264parse ! v4l2slh264dec ! v4l2convert extra-controls="controls,rotate=90" ! video/x-raw,format=I420,width=1080,height=1920,pixel-aspect-ratio=1/1 ! waylandsink fullscreen=true
```

## Deploy to SD card

If you're doing this in a Linux environment, you can use the following process to transfer the image to an SD Card.
In this example we're using the core-image-weston-pine-a64-lts.wic, if you have built a different image, update the paths accordingly.

First check your SD card path using `lsblk`.

```shell
cd tmp/deploy/images/pine-a64-lts/
sudo dd if=core-image-weston-pine-a64-lts.wic of=/dev/sd<X> bs=4M iflag=fullblock oflag=direct conv=fsync status=progress
```

Replace <X> with your results from `lsblk`.
