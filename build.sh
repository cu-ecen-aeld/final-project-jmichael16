#!/bin/bash
# Script to build image for raspberry pi 4 (64-bit)
# Authors: Siddhant Jajoo, Jake Michael

git submodule init
git submodule sync
git submodule update

# local.conf won't exist until this step on first execution
source poky/oe-init-build-env

# configuration lines are represented as an array
# note the quotation escape sequence so that the quotes also get appended to local.conf
declare -a CONFLINES=(
  "MACHINE = \"raspberrypi4-64\""
#  "ENABLE_UART = \"1\""
#  "DISTRO_FEATURES_append = \" wifi\""
#  "IMAGE_FEATURES_append = \" ssh-server-openssh\""
#  "IMAGE_INSTALL_append = \" wpa-supplicant python3 vim\""
#  "INHERIT += \"extrausers\""
#  "EXTRA_USERS_PARAMS = \"usermod -p \$6\$GIddyArLKrtrinI5\$bMgWGz8ZvUI4RwHsUdCGndGvjp5tex8FMIQ9IXpq5NHqyAd6Wl25.xjuvI/liupOjRAmOyD7tS9PXez4P8gAp. root;\""
) # end CONFLINES array


# configuration lines are each added to local.conf if not already present
for CONFLINE in "${CONFLINES[@]}"; do
  cat conf/local.conf | grep "${CONFLINE}" > /dev/null
  local_conf_info=$?

  if [ $local_conf_info -ne 0 ];then
    echo "Append ${CONFLINE} in the local.conf file"
    echo ${CONFLINE} >> conf/local.conf
    
  else
    echo "${CONFLINE} already exists in the local.conf file"
  fi
done

# layers are represented as an array
declare -a LAYERS=(
  "meta-raspberrypi"
  "meta-openembedded/meta-oe"
  "meta-openembedded/meta-python"
  "meta-openembedded/meta-networking"
  "meta-jmiv" # my custom layer
)

for LAYER in "${LAYERS[@]}"; do
  bitbake-layers show-layers | grep "${LAYER}" > /dev/null
  layer_info=$?
  if [ $layer_info -ne 0 ];then
    echo "Adding ${LAYER} layer"
    bitbake-layers add-layer ../${LAYER}
  else
    echo "${LAYER} layer already exists"
  fi
done

set -e

bitbake core-image-jmiv
