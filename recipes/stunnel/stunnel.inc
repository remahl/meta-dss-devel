SECTION = "console/network"
DEPENDS = "openssl"
HOMEPAGE = "http://www.stunnel.org"
LICENSE = "GPL"
DESCRIPTION = "Stunnel is a program that allows you to encrypt \
arbitrary TCP connections inside SSL"

PR="r6"

SRC_URI = "file://stunnel.run \
           file://stunnel.finish \
           file://stunnel.logrotate \
           file://stunnel.log.run \
           "

S = "${WORKDIR}/stunnel-${PV}"

inherit autotools

EXTRA_OECONF = "--with-ssl=${STAGING_LIBDIR}/.. \
		--with-random=/dev/urandom \
		--with-ptmx \
		--without-ptc"

do_install() {
    autotools_do_install
    install -d ${D}${sysconfdir}/stunnel
    install -m 644 ${WORKDIR}/stunnel.conf ${D}${sysconfdir}/stunnel

    install -d ${D}${sysconfdir}/runit/stunnel/log
    install -m 0755 ${WORKDIR}/stunnel.log.run ${D}${sysconfdir}/runit/stunnel/log/run
    install -m 0755 ${WORKDIR}/stunnel.run ${D}${sysconfdir}/runit/stunnel/run
    install -m 0755 ${WORKDIR}/stunnel.finish ${D}${sysconfdir}/runit/stunnel/finish
    install -d ${D}${sysconfdir}/logrotate.d
    install ${WORKDIR}/stunnel.logrotate ${D}${sysconfdir}/logrotate.d/stunnel
}

FILES_${PN} += "${sysconfdir}"

pkg_preinst_${PN}() {
#/bin/sh

update-rc.d stunnel remove

if test -e "/etc/runit/stunnel/supervise/stat"; then
    if test `cat /etc/runit/stunnel/supervise/stat` = "run"; then
        echo "Stopping stunnel..."
        sv -w 15 down /etc/runit/stunnel
    fi
fi
}

pkg_prerm_${PN}() {
#/bin/sh

if test -e "/etc/runit/stunnel/supervise/stat"; then
    if test `cat /etc/runit/stunnel/supervise/stat` = "run"; then
        echo "Stopping stunnel..."
        sv -w 15 down /etc/runit/stunnel
    fi
fi
}

pkg_postrm_${PN}() {
#/bin/sh

if test -d "/etc/runit/stunnel"; then
    rm -rf /etc/runit/stunnel
fi
}


pkg_postinst_${PN}() {
#/bin/sh
if test -e "/etc/runit/stunnel/supervise/stat"; then
    if test `cat /etc/runit/stunnel/supervise/stat` = "down"; then
        echo "Starting stunnel..."
        sv -w 15 up /etc/runit/stunnel
    fi
fi

}
