# dSM API
DESCRIPTION = "dS485 dSM API"
LICENSE = "LGPL"
HOMEPAGE = "http://gitorious.digitalstrom.org/ds485-stack/dsm-api"
DEPENDS = "python libds485-client python python-native python-lxml-native swig-native lua5.1"

PR="r1"

SRC_URI="http://developer.digitalstrom.org/download/ds485-stack/0.9/dsm-api-${PV}.tar.bz2"

S = "${WORKDIR}/dsm-api-${PV}"

inherit cmake python-dir

RREPLACES_${PN} = "dsm-api libdsm-api-v2-1.1.0"
RCONFLICTS_${PN} = "dsm-api libdsm-api-v2-1.1.0"

SRC_URI[md5sum] = "e959ff86548135766f082232b8363773"
SRC_URI[sha256sum] = "c3e2fb831e8c73cd1a0aeedac9127ea1d88ae74d8dc2911492556f15dc6f7318"

EXTRA_OECMAKE = "-DCMAKE_SWIG_ADDITIONAL_INCLUDE=${STAGING_INCDIR} -DPYTHON_LIBRARY_PATH=${PYTHON_SITEPACKAGES_DIR} -DCMAKE_SKIP_RPATH=TRUE -DWITH_SCRIPTING=on"

PACKAGES += "${PN}-python-dbg ${PN}-python"
FILES_${PN}-python-dbg = "${PYTHON_SITEPACKAGES_DIR}/dsmapi2/.debug"
FILES_${PN}-python = "${bindir}/* ${PYTHON_SITEPACKAGES_DIR}/dsmapi2/*.so ${PYTHON_SITEPACKAGES_DIR}/dsmapi2/*.py"
RDEPENDS_${PN}-python = "${PN} python"

PACKAGES += "${PN}-lua ${PN}-lua-dbg"
FILES_${PN}-lua-dbg = "${libdir}/lua/5.1/.debug/*"
FILES_${PN}-lua = "${libdir}/lua/5.1/*.so"
RDEPENDS_${PN}-lua = "${PN}"

FILES_${PN} = "${libdir}/*.so.*"

pkg_preinst_${PN}() {
#/bin/sh
if test -e "/etc/runit/dss/supervise/stat"; then
    if test `cat /etc/runit/dss/supervise/stat` = "run"; then
        echo "Stopping dSS..."
        sv -w 15 down /etc/runit/dss
    fi
fi
}

pkg_prerm_${PN}() {
#/bin/sh
if test -e "/etc/runit/dss/supervise/stat"; then
    if test `cat /etc/runit/dss/supervise/stat` = "run"; then
        echo "Stopping dSS..."
        sv -w 15 down /etc/runit/dss
    fi
fi
}

pkg_postinst_${PN}() {
#/bin/sh
if test -e "/etc/runit/dss/supervise/stat"; then
    if test `cat /etc/runit/dss/supervise/stat` = "down"; then
        echo "Starting dSS..."
        sv -w 15 up /etc/runit/dss
    fi
fi
}
