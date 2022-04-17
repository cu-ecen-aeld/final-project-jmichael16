# See http://git.yoctoproject.org/cgit.cgi/poky/tree/meta/files/common-licenses
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

# Set this with the path to your assignments rep.  Use ssh protocol and see lecture notes
# about how to setup ssh-agent for passwordless access
#BB_STRICT_CHECKSUM = "0"
SRC_URI = "git://git@github.com/jmichael16/MQTT-ify.git;protocol=ssh;branch=main"

PV = "1.0+git${SRCPV}"

# Set to reference a specific commit hash in your assignment repo
SRCREV = "08aea8f0a53fa98d4e85be5aff11b8cd6def629d"  

# This sets your staging directory based on WORKDIR, where WORKDIR is defined at 
# https://www.yoctoproject.org/docs/latest/ref-manual/ref-manual.html#var-WORKDIR
# We reference the "server" directory here to build from the "server" directory
# in your assignments repo
S = "${WORKDIR}/git/embedded-target"

# Add the aesdsocket application and any other files you need to install
# See http://git.yoctoproject.org/cgit.cgi/poky/plain/meta/conf/bitbake.conf?h=warrior for yocto path prefixes
FILES_${PN} += "${bindir}/mqttify"
#FILES_${PN} += "${sysconfdir}/init.d/aesdsocket-start-stop"
# customize these as necessary for any libraries you need for your application
TARGET_LDFLAGS += "-lpaho-mqtt3cs"
EXTRA_OEMAKE_append = " 'TARGET_BUILD=1'"

DEPENDS = "paho-mqtt-c"

# Needed to configure start script with update-rc-d class
#INITSCRIPT_PACKAGES = "${PN}"
#INITSCRIPT_NAME_${PN} = "aesdsocket-start-stop.sh"
#inherit update-rc.d

do_configure () {
	:
}

do_compile () {
	oe_runmake
}

do_install () {
  # install destination dir /usr/bin (bindir) 
  install -m 0755 -d ${D}${bindir}
  # install aesdsocket to /usr/bin
  install -m 0755 ${S}/mqttify ${D}${bindir}/

  install -m 0755 -d ${D}${sysconfdir}/mqttify
  # install destination dir /etc/init.d (sysconfdir)
  #install -m 0755 -d ${D}${sysconfdir}/init.d
  # install aesdsocket-start-stop.sh 
  #install -m 0755 ${S}/aesdsocket-start-stop.sh ${D}${sysconfdir}/init.d
}

