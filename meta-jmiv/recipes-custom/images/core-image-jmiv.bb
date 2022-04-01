inherit core-image
#IMAGE_INSTALL_append = "wpa-supplicant python3 vim my-init"
inherit extrausers
EXTRA_USERS_PARAMS = "usermod -p \$6\$GIddyArLKrtrinI5\$bMgWGz8ZvUI4RwHsUdCGndGvjp5tex8FMIQ9IXpq5NHqyAd6Wl25.xjuvI/liupOjRAmOyD7tS9PXez4P8gAp. root;"
