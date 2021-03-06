# dS485 daemon
DESCRIPTION = "dS485 daemon"
LICENSE = "GPLv2"
HOMEPAGE = "http://gitorious.digitalstrom.org/ds485-stack/ds485d"
DEPENDS = "libds485-core"

PR="r1"

SRC_URI="http://developer.digitalstrom.org/download/ds485-stack/1.0/ds485d-${PV}.tar.bz2 \
         file://ds485d.run \
         file://ds485d.log.run \
         file://ds485d.finish \
         file://ds485d.logrotate \
         "

inherit cmake

CONFFILES_${PN} = "${sysconfdir}/runit/ds485d/run"

SRC_URI[md5sum] = "15f4b0634566ff8687c2d8a70a164e99"
SRC_URI[sha256sum] = "00a88e65103b93cfd766058580187915e05d4b4682c3abef2eed8b3edc0d219d"

do_install_append() {
    install -m 0755 -d ${D}${sysconfdir}/runit/ds485d/log
    install -m 0755 -d ${D}${sysconfdir}/logrotate.d

    install -m 0755 ${WORKDIR}/ds485d.run ${D}${sysconfdir}/runit/ds485d/run
    install -m 0755 ${WORKDIR}/ds485d.finish ${D}${sysconfdir}/runit/ds485d/finish
    install -m 0755 ${WORKDIR}/ds485d.log.run ${D}${sysconfdir}/runit/ds485d/log/run
    install ${WORKDIR}/ds485d.logrotate ${D}${sysconfdir}/logrotate.d/ds485d
}

pkg_preinst_${PN}() {
#!/bin/sh

if test -e "/etc/runit/ds485d/supervise/stat"; then
    if test `cat /etc/runit/ds485d/supervise/stat` = "run"; then
        echo "Stopping dS485d..."
        sv -w 15 down /etc/runit/ds485d
    fi
fi
}

pkg_prerm_${PN}() {
#!/bin/sh

if test -e "/etc/runit/ds485d/supervise/stat"; then
    if test `cat /etc/runit/ds485d/supervise/stat` = "run"; then
        echo "Stopping dS485d..."
        sv -w 15 down /etc/runit/ds485d
    fi
fi
}

pkg_postrm_${PN}() {
#!/bin/sh

if test -d "/etc/runit/ds485d"; then
    rm -rf /etc/runit/ds485d
fi

if test -d "/var/run/ds485.pid"; then
    rm -f /var/run/ds485.pid
fi
}

pkg_postinst_${PN}() {
#!/bin/sh
if test -e "/etc/runit/ds485d/supervise/stat"; then
    if test `cat /etc/runit/ds485d/supervise/stat` = "down"; then
        echo "Starting dS485d..."
        sv -w 15 up /etc/runit/ds485d
    fi
fi
}

