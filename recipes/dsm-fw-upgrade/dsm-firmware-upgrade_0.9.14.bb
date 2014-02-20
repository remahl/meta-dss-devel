# dSM firmware upgrade tool
DESCRIPTION = "dSM firmware upgrade tool"
LICENSE = "GPLv2"
HOMEPAGE = "http://gitorious.digitalstrom.org/dss-misc/dsm-fw-upgrade"
DEPENDS = "libdsm-api"
RREPLACES_${PN} = "dsm-fw-upgrade"
RCONFLICTS_${PN} = "dsm-fw-upgrade"

PR="r1"

SRC_URI[md5sum] = "16179397cc4390f560eb4bb1b2def9ac"
SRC_URI[sha256sum] = "49541e826e4f4016f07d39f0fb5f64e86de956af7ba6867280ce65f94c394f30"
SRC_URI[script.md5sum] = "16dd0c449c396e04e2a3a13b6c8e397e"
SRC_URI[script.sha256sum] = "1f95584423266709b3da2f60820cf52aea056b7b9a679930189ddd4b272b52d0"

SRC_URI=" http://developer.digitalstrom.org/download/dsm-firmware-upgrade/dsm-firmware-upgrade-${PV}.tar.gz \
	file://perform-dsm-upgrade;name=script \
"

inherit autotools


do_install_append () {
    install -m 0755 -d ${D}${bindir}
    install -m 0755 ${WORKDIR}/perform-dsm-upgrade ${D}${bindir}/perform-dsm-upgrade
}
