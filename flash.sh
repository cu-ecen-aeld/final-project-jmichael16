#!/bin/bash
# Author: Jake Michael

bzcat build/tmp/deploy/images/raspberrypi4-64/core-image-base-raspberrypi4-64*.wic.bz2 | sudo dd of=/dev/sdc status=progress


<< 'MULTILINE-COMMENT'
if [ "$EUID" -ne 0 ]; then
  echo "Please run as root"
  exit
fi

disk_to_flash="/dev/sdc"
tempfile=$(mktemp --suffix=-rpi-img)

# decompress .bz2 archive to tempfile
bzcat -f build/tmp/deploy/images/raspberrypi4-64/core-image-base-raspberrypi4-64-*.bz2 > "$tempfile"

cmd="dd if=$tempfile of=$disk_to_flash bs=4M conv=fsync status=progress"
echo "The command you are about to run is shown below: "
echo "$cmd"
read -p "Do you wish to run it? [Y/n]: " user_yes_no
if [[ $user_yes_no == [yY] ]]
then
  $cmd
else
  echo "Ok, cleaning up and exiting."
fi

rm -rf "$tempfile"

MULTILINE-COMMENT
