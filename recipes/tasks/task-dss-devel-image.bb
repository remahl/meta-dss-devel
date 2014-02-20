DESCRIPTION = "dSS11 Development Image dependency metapackage"
PR = "r12"


inherit task

RDEPENDS_${PN} = "\
    bootsetup \
    dss \
    lighttpd \
    lighttpd-module-cgi \
    rs485 \
    mtd-utils \
    avahi-daemon \
    avahi-autoipd \
    upnpbeacon \
    aufs2 \
    kernel-module-ext3 \
    kernel-module-ext4 \
    kernel-module-vfat \
    kernel-module-ntfs \
    kernel-module-aufs \
    kernel-module-scsi-wait-scan \
    kernel-module-loop \
    kernel-module-rtlwifi \
    kernel-module-rtl8192cu \
    kernel-module-rtl8192c-common \
    kernel-module-rt2x00lib \
    kernel-module-rt2800usb \
    kernel-module-rt2800lib \
    kernel-module-rt2x00usb \
    kernel-module-ath \
    kernel-module-carl9170 \
    kernel-module-zd1211rw \
    kernel-module-ecb \
    kernel-module-hmac \
    kernel-module-arc4 \
    kernel-module-aes-generic \
    kernel-module-ansi-cprng \
    kernel-module-sha256-generic \
    kernel-module-crc-itu-t \
    kernel-module-crc16 \
    kernel-module-crc-ccitt \
    kernel-module-mac80211 \
    kernel-module-cfg80211 \
    ntpclient \
    haserl \
    e2fsprogs-mke2fs \
    e2fsprogs-e2fsck \
    dss11-websetup \
    netplug \
    runit \
    logrotate \
    tzdata \
    tzdata-europe \
    tzdata-atlantic \
    tzdata-africa \
    tzdata-americas \
    tzdata-antarctica \
    tzdata-antarctica \
    tzdata-asia \
    tzdata-australia \
    tzdata-pacific \
    dss-add-on-feeds \
    dss11-utils \
    dss-web-module \
    dss11-help \
    dss11-buttond \
    dsm-firmware \
    dsa \
    dsa-ui-module \
    dss11-usb-upgrade \
    iw \
    wireless-tools \
    wpa-supplicant \
    wpa-supplicant-passphrase \
    zd1211-firmware \
    u-boot-utils \
    "
    

PACKAGE_ARCH = "${MACHINE_ARCH}"

