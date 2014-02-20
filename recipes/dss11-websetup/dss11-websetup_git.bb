# setup interface for the whole system
DESCRIPTION = "dSS11 web setup UI"
DEPENDS = "ruby-native rubygem-sprockets-native"
RDEPENDS_${PN} = "lighttpd lighttpd-module-cgi haserl gettext tzdata ntpclient opkg2json dss-add-on-feeds dsm-firmware-upgrade dsm-firmware msmtp libxml2-utils"
LICENSE = "GPL"
SRCREV="${AUTOREV}"
PV="0.17.0+gitr${SRCPV}"
PR = "r1"

SRC_URI = "git://gitorious.digitalstrom.org/dss11-websetup/dss11-websetup.git;protocol=git;branch=master"

S = "${WORKDIR}/git"

inherit autotools

EXTRA_OEMAKE = "'CC=${CCACHE} ${BUILD_PREFIX}gcc' 'LD=${CCACHE} ${BUILD_PREFIX}ld'"

# somehow I failed to get rid of the prefix...
do_install_append() {
    install -d ${D}/www/
    mv ${D}/usr/share/dss11-websetup/www ${D}/www/pages
    mv ${D}/usr/share/dss11-websetup/scripts ${D}/www/scripts
    rm -rf ${D}/usr

    sed -i s:LOCALEDIR=\"/usr/share/www/pages/locale\":LOCALEDIR=\"/www/pages/locale\":g ${D}/www/pages/cgi-bin/langdefs.sh
    sed -i s:\.\.\/cgi-bin\/errlib.sh:\/www\/pages\/cgi-bin\/errlib.sh:g ${D}/www/scripts/doupdate.sh
    install -d ${D}/www/pages/add-ons/
}

PACKAGE_ARCH_${PN} = "all"
FILES_${PN} = "/www/*"

