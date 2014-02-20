# dS485 daemon
DESCRIPTION = "dS485 daemon"
LICENSE = "GPLv2"
HOMEPAGE = "http://gitorious.digitalstrom.org/ds485-stack/ds485d"
DEPENDS = "libds485-core"

SRCREV = "281ab6bbf4551756288e03169bad1fa0445fce33"
PR="r1"
PV = "0.5.0+gitr${SRCPV}"

SRC_URI="git://gitorious.digitalstrom.org/ds485-stack/ds485d.git;protocol=git;branch=production \
         file://ds485d.run \
         file://ds485d.log.run \
         file://ds485d.finish \
         file://ds485d.logrotate \
         "

inherit cmake

S = "${WORKDIR}/git"

do_install_append() {
    install -m 0755 -d ${D}${sysconfdir}/runit/ds485d/log
    install -m 0755 -d ${D}${sysconfdir}/logrotate.d

    install -m 0755 ${WORKDIR}/ds485d.run ${D}${sysconfdir}/runit/ds485d/run
    install -m 0755 ${WORKDIR}/ds485d.finish ${D}${sysconfdir}/runit/ds485d/finish
    install -m 0755 ${WORKDIR}/ds485d.log.run ${D}${sysconfdir}/runit/ds485d/log/run
    install ${WORKDIR}/ds485d.logrotate ${D}${sysconfdir}/logrotate.d/ds485d
}

pkg_preinst_${PN}() {
#/bin/sh

if test -e "/etc/runit/ds485d/supervise/stat"; then
    if test `cat /etc/runit/ds485d/supervise/stat` = "run"; then
        echo "Stopping ds485d..."
        sv -w 15 down /etc/runit/ds485d
    fi
fi
}

pkg_prerm_${PN}() {
#/bin/sh

if test -e "/etc/runit/ds485d/supervise/stat"; then
    if test `cat /etc/runit/ds485d/supervise/stat` = "run"; then
        echo "Stopping ds485d..."
        sv -w 15 down /etc/runit/ds485d
    fi
fi
}

pkg_postrm_${PN}() {
#/bin/sh

if test -d "/etc/runit/ds485d"; then
    rm -rf /etc/runit/ds485d
fi

if test -d "/var/run/ds485.pid"; then
    rm -f /var/run/ds485.pid
fi
}

pkg_postinst_${PN}() {
#/bin/sh
if test -e "/etc/runit/ds485d/supervise/stat"; then
    if test `cat /etc/runit/ds485d/supervise/stat` = "down"; then
        echo "Starting ds485d..."
        sv -w 15 up /etc/runit/ds485d
    fi
fi

if test -e "/etc/runit/dss"; then
    sleep 2
    sv restart dss
fi
}

