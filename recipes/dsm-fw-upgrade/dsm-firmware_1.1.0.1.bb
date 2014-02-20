# dSM firmware
DESCRIPTION = "digitalSTROM Meter firmware"
HOMEPAGE = "http://developer.digitalstrom.org/download/dsm-firmware-images"
DEPENDS = "libdsm-api"
RDEPENDS_${PN} = "dsm-firmware-upgrade"

LICENSE = "Proprietary"

PR="r1"

SRC_URI[arm.md5sum] = "200a82a47e5d9c25c054e137f2e2f026"
SRC_URI[arm.sha256sum] = "f260ecb627f61280491e7a52814c5fd4809b7e83c58e35e24c90d3ba8d6d85a6"

SRC_URI[dsp.md5sum] = "0aff94ee69cf047c2db152e8ab551abe"
SRC_URI[dsp.sha256sum] = "dfed8b50ff1c21a7c586cc2e3b6134fb64af78defe032539b726057bbc5d845b"

SRC_URI[cfg.md5sum] = "6f4326cf1b16d417639c3a109df20d27"
SRC_URI[cfg.sha256sum] = "049500f2936fdd29527734908ab326b2fe47ae5927d74b87b3a8d65443646262"

ARM_FW = "dsm-arm-v1.1.0.1-0-gcc27144.img"
DSP_FW = "dsm-dsp-v1.1.0-0-gf18c421.img"

SRC_URI="\
    http://developer.digitalstrom.org/download/dsm-firmware-images/1.1/${ARM_FW};name=arm \
    http://developer.digitalstrom.org/download/dsm-firmware-images/1.1/${DSP_FW};name=dsp \
    http://developer.digitalstrom.org/download/dsm-firmware-upgrade/dsm-config-update-1.5.tar.gz;name=cfg \
    file://do-perform-dsm-upgrade;name=script \
"

# S = ${WORKDIR}/dsm-config-update-1.5

inherit autotools

do_install_append() {
    install -m 0755 -d ${D}${datadir}/${PN}
    install -m 0755 ${WORKDIR}/${ARM_FW} ${D}${datadir}/${PN}
    install -m 0755 ${WORKDIR}/${DSP_FW} ${D}${datadir}/${PN}

    install -m 0755 -d ${D}${bindir}
    install -m 0755 ${WORKDIR}/do-perform-dsm-upgrade ${D}${bindir}/do-perform-dsm-upgrade
}

FILES_${PN} = "${datadir}/${PN}/* ${bindir}/*"

