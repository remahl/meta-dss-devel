# setup interface for the whole system
DESCRIPTION = "dSS11 web setup UI"
RDEPENDS_${PN} = "dss"
LICENSE = "GPL"
PR = "r3"

SRCREV="${AUTOREV}"
PV = "1.2.0+gitr${SRCPV}"
SRC_URI = "git://gitorious.digitalstrom.org/dss-websrc/dss-websrc-mainline.git;protocol=git;branch=master"

S="${WORKDIR}/git"

inherit autotools

EXTRA_OECONF = "--enable-debug"
EXTRA_OEMAKE = "'CC=${CCACHE} ${BUILD_PREFIX}gcc' 'LD=${CCACHE} ${BUILD_PREFIX}ld'"

require dss-webmodule-icons.inc

# this link is needed so that hiding behind lighttpd works correctly
do_install_append() {
    ln -s ${datadir}/${PN}/webroot ${D}${datadir}/${PN}/webroot/dss
    install -d ${D}/www/pages/js/dss/css
    install -d ${D}/www/pages/locale/de_DE/LC_MESSAGES
    install -d ${D}/www/pages/images
    install ${S}/webroot/js/dss/dss-interface-module.js ${D}/www/pages/js/dss/dss-interface-module.js
    install ${S}/webroot/js/dss/css/dss_icons.css ${D}/www/pages/js/dss/css/dss_icons.css
    install ${S}/webroot/locale/de_DE/LC_MESSAGES/dss-langpack.json ${D}/www/pages/locale/de_DE/LC_MESSAGES/dss-langpack.json

    for icon in ${INSTALL_ICONS}; do
        install ${S}/webroot/images/$icon ${D}/www/pages/images/$icon
    done
}

PACKAGE_ARCH_${PN} = "all"
PACKAGE_ARCH_${PN}-module = "all"

PACKAGES += "${PN}-module"
FILES_${PN}-module = "/www/*"

