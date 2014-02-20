# dS485 client
DESCRIPTION = "dS485 client"
LICENSE = "LGPL"
HOMEPAGE = "http://gitorious.digitalstrom.org/ds485-stack/ds485d"
DEPENDS = "ds485d"

SRCREV = "${AUTOREV}"
PR="r1"
PV = "0.5.0+gitr${SRCPV}"

SRC_URI="git://gitorious.digitalstrom.org/ds485-stack/ds485-client.git;protocol=git;branch=master"

inherit cmake

S = "${WORKDIR}/git"

RCONFLICTS_${PN} = "ds485-client"
RREPLACES_${PN} = "ds485-client"
