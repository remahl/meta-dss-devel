# dS485 core library
DESCRIPTION = "dS485 core library"
LICENSE = "GPLv2"
HOMEPAGE = "http://gitorious.digitalstrom.org/ds485-stack/ds485-core"

RCONFLICTS_${PN} = "ds485-core"
RREPLACES_${PN} = "ds485-core"

S = "${WORKDIR}/ds485-core-${PV}"

PR="r1"

SRC_URI="http://developer.digitalstrom.org/download/ds485-stack/1.1/ds485-core-${PV}.tar.bz2"

inherit cmake

require ds485-core.inc

LEAD_SONAME = "libds485-core.so.1"

FILES_${PN} = " ${libdir}/libds485-core.so.* "
FILES_${PN}-dbg = " ${libdir}/.debug/libds485-core.* "

SRC_URI[md5sum] = "a109a988230b1cee1dd215e9f97b5033"
SRC_URI[sha256sum] = "5ee4505d62d1f6379b757e41c44c85779de77664cb872b1347af6d64790732bb"

