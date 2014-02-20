# web user interface for the dSA
DESCRIPTION = "digitalSTROM Assistant web interface"
LICENSE="GPL"
DEPENDS = "ruby-native rubygem-sprockets-native"
RDEPENDS_${PN} = "dsa"
SRCREV="${AUTOREV}"
PV="0.4.0+gitr${SRCPV}"
PR="r1"

SRC_URI="git://gitorious.digitalstrom.org/ds-assistant/ds-assistant-ui.git;protocol=git;branch=master"

S = "${WORKDIR}/git"

inherit autotools

EXTRA_OEMAKE = "'CC=${CCACHE} ${BUILD_PREFIX}gcc' 'LD=${CCACHE} ${BUILD_PREFIX}ld'"

require dsa-webmodule-icons.inc

do_install_append() {
    install -d ${D}/www/pages/js/dsa/css
    install -d ${D}/www/pages/locale/de_DE/LC_MESSAGES
    install -d ${D}/www/pages/images
    install ${S}/webroot/js/dsa/dsa-interface-module.js ${D}/www/pages/js/dsa/dsa-interface-module.js
    install ${S}/webroot/js/dsa/css/dsa_icons.css ${D}/www/pages/js/dsa/css/dsa_icons.css
    install ${S}/webroot/locale/de_DE/LC_MESSAGES/dsa-langpack.json ${D}/www/pages/locale/de_DE/LC_MESSAGES/dsa-langpack.json

    for icon in ${INSTALL_ICONS}; do
        install ${S}/webroot/images/$icon ${D}/www/pages/images/$icon
    done
}

PACKAGE_ARCH_${PN} = "all"
PACKAGE_ARCH_${PN}-module = "all"

PACKAGES += "${PN}-module"
FILES_${PN}-module = "/www/*"

