# dS485 client
DESCRIPTION = "dS485 client"
LICENSE = "LGPL"
HOMEPAGE = "http://gitorious.digitalstrom.org/ds485-stack/ds485d"
DEPENDS = "ds485d"

RCONFLICTS_${PN} = "ds485-client"
RREPLACES_${PN} = "ds485-client"

python package_mapping_rename_hook_append () {
	pkgname = bb.data.getVar('PKG', d)
	replace = 'ds485-client'
	if (pkgname.endswith('-dev')):
		replace = replace + '-dev'
	elif (pkgname.endswith('-dbg')):
		replace = replace + '-dbg'

	bb.data.setVar('RREPLACES', replace, d)
	bb.data.setVar('RCONFLICTS', replace, d)
}

PR="r2"

S = "${WORKDIR}/ds485-client-${PV}"

SRC_URI[md5sum] = "9af17ee0fc181c38ece80d8ff82384a1"
SRC_URI[sha256sum] = "20b1515223a944d8f03d3d95113709dd946f910d3329e7121c9d3beb0b2ae0af"

SRC_URI="http://developer.digitalstrom.org/download/ds485-stack/1.1/ds485-client-${PV}.tar.bz2"

LEAD_SONAME = "libds485-client.so.1"

FILES_${PN} = " ${libdir}/libds485-client.so.* "
FILES_${PN}-dbg = " ${libdir}/.debug/libds485-client.* "

inherit cmake

