DESCRIPTION = "SD-Card maintenance and startup script"
SECTION = "base"
DEPENDS = "makedevs"
RDEPENDS_${PN} = "makedevs"
LICENSE = "GPL"
PR = "r1"

SRC_URI = "file://sdcard \
           file://e2fsck.conf \
"

inherit update-rc.d

INITSCRIPT_NAME = "sdcard"
INITSCRIPT_PARAMS = "start 90 S ."

do_install () {
    install -m 0755 -d ${D}${sysconfdir}/init.d
    install -m 0755 ${WORKDIR}/sdcard ${D}${sysconfdir}/init.d/sdcard
    chmod 0755 ${D}${sysconfdir}/init.d/sdcard
    install -m 0755 ${WORKDIR}/e2fsck.conf ${D}${sysconfdir}/
}
