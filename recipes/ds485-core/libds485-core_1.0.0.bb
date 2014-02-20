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

SRC_URI[md5sum] = "41f97cffd61b4afcc57852220cbddbfa"
SRC_URI[sha256sum] = "b44e34477a39072c0fadabc3309bece25279b309e963c434518b6dbc01f2cccd"

