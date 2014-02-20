# dS485 core library
DESCRIPTION = "dS485 core library"
LICENSE = "GPLv2"
HOMEPAGE = "http://gitorious.digitalstrom.org/ds485-stack/ds485-core"
SRCREV = "${AUTOREV}"

PR="r1"
PV = "0.5.0+gitr${SRCPV}"

SRC_URI="git://gitorious.digitalstrom.org/ds485-stack/ds485-core.git;protocol=git;branch=master"

inherit cmake

require ds485-core.inc

S = "${WORKDIR}/git"

RCONFLICTS_${PN} = "ds485-core"
RREPLACES_${PN} = "ds485-core"

LEAD_SONAME = "libds485-core.so.1"

FILES_${PN} = " ${libdir}/libds485-core.so.* "
FILES_${PN}-dbg = " ${libdir}/.debug/libds485-core.* "