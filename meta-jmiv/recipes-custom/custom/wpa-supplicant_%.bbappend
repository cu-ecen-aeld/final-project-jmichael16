# this should get the wifi configured on boot

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI_append = "\
  file://wpa_supplicant.conf-sane \
  "

