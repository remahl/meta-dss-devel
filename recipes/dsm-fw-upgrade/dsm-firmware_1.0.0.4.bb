# dSM firmware
DESCRIPTION = "digitalSTROM Meter firmware"
HOMEPAGE = "http://developer.digitalstrom.org/download/dsm-firmware-images"
DEPENDS = "libdsm-api"
RDEPENDS_${PN} = "dsm-firmware-upgrade"

LICENSE = "Proprietary"

PR="r1"

SRC_URI[arm.md5sum] = "9f03ab54f4aeb3c2cc88e1c40a33ca31"
SRC_URI[arm.sha256sum] = "6041033fcee42faf69553d8be3b4c331f9b1f83f4a2c3c23c4dcea714c6e0f0c"

SRC_URI[dsp.md5sum] = "cc9fb5341c8411cc71fd2ba05a289ec7"
SRC_URI[dsp.sha256sum] = "9d1e341ad858a349945461862588d5e33ae1a7fadef4eef294a794b3aa1f4f08"

SRC_URI[cfg.md5sum] = "de45ab231fdbd984ff7bc7656f056ff7"
SRC_URI[cfg.sha256sum] = "3b815d373af3f721350e20be447f95ae84a2019d2845115fd4359bef6f8568ce"

ARM_FW = "dsm-arm-v1.0.0.4-0-g2504cb6.img"
DSP_FW = "dsm-dsp-v1.0.0.2-0-gd079938.img"

SRC_URI="\
    http://developer.digitalstrom.org/download/dsm-firmware-images/1.0/${ARM_FW};name=arm \
    http://developer.digitalstrom.org/download/dsm-firmware-images/1.0/${DSP_FW};name=dsp \
    http://developer.digitalstrom.org/download/dsm-firmware-upgrade/dsm-config-update-1.3.tar.gz;name=cfg \
    file://do-perform-dsm-upgrade;name=script \
"

# S = ${WORKDIR}/dsm-config-update-1.3

inherit autotools

do_install_append() {
    install -m 0755 -d ${D}${datadir}/${PN}
    install -m 0755 ${WORKDIR}/${ARM_FW} ${D}${datadir}/${PN}
    install -m 0755 ${WORKDIR}/${DSP_FW} ${D}${datadir}/${PN}

    install -m 0755 -d ${D}${bindir}
    install -m 0755 ${WORKDIR}/do-perform-dsm-upgrade ${D}${bindir}/do-perform-dsm-upgrade
}

FILES_${PN} = "${datadir}/${PN}/* ${bindir}/*"

