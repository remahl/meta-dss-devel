# dSM firmware
DESCRIPTION = "digitalSTROM Meter firmware"
HOMEPAGE = "http://developer.digitalstrom.org/download/dsm-firmware-images"
RDEPENDS_${PN} = "dsm-firmware-upgrade"

LICENSE = "Proprietary"

PR="r2"

SRC_URI[arm.md5sum] = "e1d54aa71284f42c0c0f6fc6ada43ccb"
SRC_URI[arm.sha256sum] = "1884dea0038cdfed4f7b69fd4980c183a725f064d3880bc2c39313ce9f318a82"

SRC_URI[dsp.md5sum] = "3ca1acc42f9207c00a5e22ec2d7472c5"
SRC_URI[dsp.sha256sum] = "b45215aaebbcc9150b5fe8c78d7c8670045526e5828b2a89cef9476d6e023d73"

SRC_URI[cfg.md5sum] = "0af0a6e8be3ec6207c52db631902dd1a"
SRC_URI[cfg.sha256sum] = "fdcd4ca030e2adc16cdd35edf44bdbab42296975b8d96376e736e6ec2c511591"


ARM_FW = "dsm-arm-v0.9.4-0-g262e701.img"
DSP_FW = "dsm-dsp-v0.9.3-0-gac2b7d7.img"

SRC_URI="\
    http://developer.digitalstrom.org/download/dsm-firmware-images/0.9/${ARM_FW};name=arm \
    http://developer.digitalstrom.org/download/dsm-firmware-images/0.9/${DSP_FW};name=dsp \
    http://developer.digitalstrom.org/download/dsm-firmware-upgrade/dsm-config-update-1.0.tar.gz;name=cfg \
    file://do-perform-dsm-upgrade;name=script \
"

# S = ${WORKDIR}/dsm-config-update-1.0

inherit autotools

do_install_append() {
    install -m 0755 -d ${D}${datadir}/${PN}
    install -m 0755 ${WORKDIR}/${ARM_FW} ${D}${datadir}/${PN}
    install -m 0755 ${WORKDIR}/${DSP_FW} ${D}${datadir}/${PN}

    install -m 0755 -d ${D}${bindir}
    install -m 0755 ${WORKDIR}/do-perform-dsm-upgrade ${D}${bindir}/do-perform-dsm-upgrade
}

FILES_${PN} = "${datadir}/${PN}/* ${bindir}/*"

