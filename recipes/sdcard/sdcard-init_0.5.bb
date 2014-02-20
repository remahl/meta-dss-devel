DESCRIPTION = "script to autoformat an unusable sd card"
SECTION = "base"
DEPENDS = "makedevs"
RDEPENDS_${PN} = "makedevs"
LICENSE = "GPL"
PR = "r1"

SRC_URI = "file://sdcard"

inherit update-rc.d

INITSCRIPT_NAME = "sdcard"
INITSCRIPT_PARAMS = "start 90 S ."

do_install () {
    install -m 0755 -d ${D}${sysconfdir}/init.d
    install -m 0755 ${WORKDIR}/sdcard ${D}${sysconfdir}/init.d/sdcard
}

pkg_postinst_${PN}() {
#/bin/sh
if test -x "/etc/init.d/sdcard"; then
    /etc/init.d/sdcard start
fi
}
