# dSM firmware
DESCRIPTION = "digitalSTROM Meter firmware"
HOMEPAGE = "http://developer.digitalstrom.org/download/dsm-firmware-images"
DEPENDS = "libdsm-api"
RDEPENDS_${PN} = "dsm-firmware-upgrade"

LICENSE = "Proprietary"

PR="r2"

SRC_URI[arm.md5sum] = "5245807bc2fc0f57cb2e66441c1d4d6a"
SRC_URI[arm.sha256sum] = "eaa4cd62c2563b2e8178ab7fb78944861493c7cca6d773f34cf97d651b0dc5e2"

SRC_URI[dsp.md5sum] = "0aff94ee69cf047c2db152e8ab551abe"
SRC_URI[dsp.sha256sum] = "dfed8b50ff1c21a7c586cc2e3b6134fb64af78defe032539b726057bbc5d845b"

SRC_URI[cfg.md5sum] = "6f4326cf1b16d417639c3a109df20d27"
SRC_URI[cfg.sha256sum] = "049500f2936fdd29527734908ab326b2fe47ae5927d74b87b3a8d65443646262"

ARM_FW = "dsm-arm-v1.1.0-0-g6352bf8.img"
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

