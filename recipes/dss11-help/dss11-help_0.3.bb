# help web pages
DESCRIPTION = "dSS11 help package"
RDEPENDS_${PN} = "lighttpd"
LICENSE = "unknown"
PR = "r1"

SRC_URI = "http://developer.digitalstrom.org/download/doc/digitalSTROM-Installationshandbuch_V003-DE_2011-06-01.zip"

S="${WORKDIR}/digitalSTROM-Installationshandbuch_V003-DE_2011-06-01"

do_install () {
    install -m 0755 -d ${D}/www/pages/help
    mv ${S}/digitalSTROM\ Installationshandbuch/* ${D}/www/pages/help/
}

FILES_${PN} = "/www/pages/help/*"

PACKAGE_ARCH_${PN} = "all"

SRC_URI[md5sum] = "7e8d82da2c7c4a911a7897f20a1eff03"
SRC_URI[sha256sum] = "f7cb71a426afb9c846013ac13c27d92c8f150d91995358dcbfb5db8916c6a71c"

