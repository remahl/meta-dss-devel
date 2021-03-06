# dSM firmware
DESCRIPTION = "digitalSTROM Meter firmware"
HOMEPAGE = "http://developer.digitalstrom.org/download/dsm-firmware-images"
DEPENDS = "libdsm-api"
RDEPENDS_${PN} = "dsm-firmware-upgrade"

LICENSE = "Proprietary"

PR="r1"

SRC_URI[arm.md5sum] = "9fc518c241f7f3f5d97daae874c6478f"
SRC_URI[arm.sha256sum] = "b33df602aef6522a6661ef140ff5e25b5d3228429b66a27efb08c9cd43b024a9"

SRC_URI[dsp.md5sum] = "cc9fb5341c8411cc71fd2ba05a289ec7"
SRC_URI[dsp.sha256sum] = "9d1e341ad858a349945461862588d5e33ae1a7fadef4eef294a794b3aa1f4f08"

SRC_URI[cfg.md5sum] = "b66b19a66fc92c76e6ec978864e62961"
SRC_URI[cfg.sha256sum] = "6a0780ffb958e3a943e54edef5dbfdb78ba238d8b7f4878ee7f17dd4093cd301"

ARM_FW = "dsm-arm-v1.0.0.3-0-g86a72e1.img"
DSP_FW = "dsm-dsp-v1.0.0.2-0-gd079938.img"

SRC_URI="\
    http://developer.digitalstrom.org/download/dsm-firmware-images/1.0/${ARM_FW};name=arm \
    http://developer.digitalstrom.org/download/dsm-firmware-images/1.0/${DSP_FW};name=dsp \
    http://developer.digitalstrom.org/download/dsm-firmware-upgrade/dsm-config-update-1.2.tar.gz;name=cfg \
    file://do-perform-dsm-upgrade;name=script \
"

# S = ${WORKDIR}/dsm-config-update-1.2

inherit autotools

do_install_append() {
    install -m 0755 -d ${D}${datadir}/${PN}
    install -m 0755 ${WORKDIR}/${ARM_FW} ${D}${datadir}/${PN}
    install -m 0755 ${WORKDIR}/${DSP_FW} ${D}${datadir}/${PN}

    install -m 0755 -d ${D}${bindir}
    install -m 0755 ${WORKDIR}/do-perform-dsm-upgrade ${D}${bindir}/do-perform-dsm-upgrade
}

FILES_${PN} = "${datadir}/${PN}/* ${bindir}/*"

