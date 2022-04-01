SUMMARY = "A custom image that supports the RPI 4"
LICENSE = "MIT"
ENABLE_UART = "1"
DISTRO_FEATURES_append = " wifi"
IMAGE_FEATURES_append = " ssh-server-openssh splash"
IMAGE_FEATURES_remove = "debug-tweaks"
IMAGE_INSTALL_append = " wpa-supplicant python3 vim net-tools my-init"
EXTRA_USERS_PARAMS_append = "\
usermod -p '\$6\$hkkCJQxK4owLdqYs\$G5sJ6RPkmQZQ/N6qsluqmwIUg1x6zoF.k2edCzpf9b9i4EaaGGM3yGZQvM2XrxysFgTqi5r3se2pAv0zaRmhi1' root; \
useradd -p '\$6\$4YE/z1P.mgjiSrgg\$wLwVYVNRX77y/WAS4b5TPOdXSKy5p49du67T5k3SNstAdHZVj/T.FQ82xUSEmHL6c9CbyPh8w70.thN0Pt4rO0' dev; \
"
inherit extrausers
inherit core-image
