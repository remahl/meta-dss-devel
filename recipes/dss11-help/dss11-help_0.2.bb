# help web pages
DESCRIPTION = "dSS11 help package"
RDEPENDS_${PN} = "lighttpd"
LICENSE = "unknown"
PR = "r1"

SRC_URI = "http://developer.digitalstrom.org/download/doc/digitalSTROM-Installationshandbuch_V002-DE_2011-04-05.zip"

S="${WORKDIR}/digitalSTROM-Installationshandbuch_V002-DE_2011-04-05"

do_install () {
    install -m 0755 -d ${D}/www/pages/help
    mv ${S}/digitalSTROM\ Installationshandbuch/* ${D}/www/pages/help/
}

FILES_${PN} = "/www/pages/help/*"

PACKAGE_ARCH_${PN} = "all"

SRC_URI[md5sum] = "23ba778f4a638026656fcf60cdccaa9c"
SRC_URI[sha256sum] = "ee8360785b9f91c15d5a8f8b6dc014a00a37d8cb8d197f420263953582157b86"

