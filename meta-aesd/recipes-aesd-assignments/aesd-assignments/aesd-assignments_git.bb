# See http://git.yoctoproject.org/cgit.cgi/poky/tree/meta/files/common-licenses
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

# Set this with the path to your assignments rep.  Use ssh protocol and see lecture notes
# about how to setup ssh-agent for passwordless access
#BB_STRICT_CHECKSUM = "0"
SRC_URI = "git://git@github.com/cu-ecen-aeld/assignments-3-and-later-jmichael16.git;protocol=ssh;branch=main"

PV = "1.0+git${SRCPV}"
# Set to reference a specific commit hash in your assignment repo

SRCREV = "84f1cc3c017996223b7d86a3269d8d4565fdf424"  

# This sets your staging directory based on WORKDIR, where WORKDIR is defined at 
# https://www.yoctoproject.org/docs/latest/ref-manual/ref-manual.html#var-WORKDIR
# We reference the "server" directory here to build from the "server" directory
# in your assignments repo
S = "${WORKDIR}/git/server"

# Add the aesdsocket application and any other files you need to install
# See http://git.yoctoproject.org/cgit.cgi/poky/plain/meta/conf/bitbake.conf?h=warrior for yocto path prefixes
FILES_${PN} += "${bindir}/aesdsocket"
#FILES_${PN} += "${sysconfdir}/init.d/aesdsocket-start-stop"
# customize these as necessary for any libraries you need for your application
TARGET_LDFLAGS += "-pthread -lrt"
RDEPENDS_${PN} = "libgcc"

# Needed to configure start script with update-rc-d class
INITSCRIPT_PACKAGES = "${PN}"
INITSCRIPT_NAME_${PN} = "aesdsocket-start-stop.sh"
inherit update-rc.d

do_configure () {
	:
}

do_compile () {
	oe_runmake
}

do_install () {
	# Install your binaries/scripts here.
	# Be sure to install the target directory with install -d first
	# Yocto variables ${D} and ${S} are useful here, which you can read about at 
	# https://www.yoctoproject.org/docs/latest/ref-manual/ref-manual.html#var-D
	# and
	# https://www.yoctoproject.org/docs/latest/ref-manual/ref-manual.html#var-S
	# See example at https://github.com/cu-ecen-aeld/ecen5013-yocto/blob/ecen5013-hello-world/meta-ecen5013/recipes-ecen5013/ecen5013-hello-world/ecen5013-hello-world_git.bb
  
  # install destination dir /usr/bin (bindir) 
  install -m 0755 -d ${D}${bindir}
  # install aesdsocket to /usr/bin
  install -m 0755 ${S}/aesdsocket ${D}${bindir}/

  # install destination dir /etc/init.d (sysconfdir)
  install -m 0755 -d ${D}${sysconfdir}/init.d
  # install aesdsocket-start-stop.sh 
  install -m 0755 ${S}/aesdsocket-start-stop.sh ${D}${sysconfdir}/init.d
}
