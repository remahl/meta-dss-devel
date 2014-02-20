DESCRIPTION = "various dss11 utilities"
LICENSE = "unknown"

SRC_URI="git://gitorious.digitalstrom.org/dss-misc/dss11-utils.git;protocol=git;branch=master"
SRCREV="9adbd915c7ffc7ed4dc768f5e4b39d745dd3cbda"

RDEPENDS_${PN} = "i2c-tools"

PV_append="+gitr${SRCREV}"
PR = "r1"

S="${WORKDIR}/git"

do_install () {
    install -m 0755 -d ${D}${sbindir}
    install -m 0755 ${S}/scripts/read_tmp75 ${D}${sbindir}/read_tmp75
    install -m 0755 ${S}/scripts/dss11-factory-reset ${D}${sbindir}/dss11-factory-reset
}

