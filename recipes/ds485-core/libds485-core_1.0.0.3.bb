# dS485 core library
DESCRIPTION = "dS485 core library"
LICENSE = "GPLv2"
HOMEPAGE = "http://gitorious.digitalstrom.org/ds485-stack/ds485-core"

RCONFLICTS_${PN} = "ds485-core"
RREPLACES_${PN} = "ds485-core"

S = "${WORKDIR}/ds485-core-${PV}"

PR="r1"

SRC_URI="http://developer.digitalstrom.org/download/ds485-stack/1.0/ds485-core-${PV}.tar.bz2"

inherit cmake

require ds485-core.inc

LEAD_SONAME = "libds485-core.so.1"

FILES_${PN} = " ${libdir}/libds485-core.so.* "
FILES_${PN}-dbg = " ${libdir}/.debug/libds485-core.* "

SRC_URI[md5sum] = "b63ceff38f54529fcc61916947639f3e"
SRC_URI[sha256sum] = "132c43c6e72d0df4cbeaaf8d4919ece9966bdd3daac0274ddbced73b66e50a34"

