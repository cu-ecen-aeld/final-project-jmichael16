SUMMARY = "A custom image that supports the RPI 4"
LICENSE = "MIT"
DISTRO_FEATURES_append = " wifi"
IMAGE_FEATURES_append = " ssh-server-openssh splash"
IMAGE_FEATURES_remove = "debug-tweaks"
IMAGE_INSTALL_append = " wpa-supplicant \
                         python3  \
                         vim \  
                         net-tools \
                         paho-mqtt-c \
                         python3 \
                         cronie \
                         htop \
                         bash \
                         my-init \
                         mqttify \
                         "


# To generate password hash, it is recommended to use cmd line similar to below: 
# $: openssl passwd -noverify -6 <password>
# make sure to escape $ symbols after pasting into fields below

EXTRA_USERS_PARAMS_append = " \
usermod -p '\$6\$hkkCJQxK4owLdqYs\$G5sJ6RPkmQZQ/N6qsluqmwIUg1x6zoF.k2edCzpf9b9i4EaaGGM3yGZQvM2XrxysFgTqi5r3se2pAv0zaRmhi1' root; \
useradd -p '\$6\$4YE/z1P.mgjiSrgg\$PNK46V5J6.Mqsl7Kt7ZBWQsCFsfBwPPxo.PZVr9eRVFwEtilF/TB.Evbps0gbz5VYvgBgsMehoK3z.w0GBRSg.' dev; \
"
inherit extrausers
inherit core-image
