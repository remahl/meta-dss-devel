# digitalSTROM Server build file

DESCRIPTION="digitalSTROM Server"
HOMEPAGE="http://developer.digitalstrom.org"
MAINTAINER="dss-developer@forum.digitalstrom.org"
LICENSE="GPLv3"
DEPENDS = "boost poco gsoap libical js-threadsafe libxml2 avahi libdsm-api openssl"
RDEPENDS_${PN} += "svlogd2 runit ds485d openssl-so-links create-certificate dss11-ledd msmtp"
INC_PR="50"
ARM_INSTRUCTION_SET = "arm"

# INITSCRIPT_NAME = "dss.sh"
# INITSCRIPT_PARAMS = "defaults 56"

SRC_URI += " file://dss.logrotate \
             file://dss.run \
             file://dss.finish \
             file://dss.log.run \
             file://sim.xml"

inherit cmake

do_configure_append() {
    sed -i s:ttyUSB0:ttyS2:g ${S}/data/config.xml
}

do_install_append () {
    install -m 0755 -d ${D}${sysconfdir}/runit/dss/log
    install -m 0755 -d ${D}${datadir}/dss
    install -m 0755 -d ${D}${datadir}/dss/data/savedprops
    install -m 0755 -d ${D}${datadir}/dss/data/metering
    install -m 0755 ${WORKDIR}/dss.run ${D}${sysconfdir}/runit/dss/run
    install -m 0755 ${WORKDIR}/dss.finish ${D}${sysconfdir}/runit/dss/finish
    install -m 0755 ${WORKDIR}/dss.log.run ${D}${sysconfdir}/runit/dss/log/run
    install -m 0755 ${S}/tests/dsstests ${D}${datadir}/dss/dsstests
    install -m 0755 ${S}/tools/ds3 ${D}${bindir}/ds3
    install -m 0755 -d ${D}${sysconfdir}/logrotate.d
    install ${WORKDIR}/dss.logrotate ${D}${sysconfdir}/logrotate.d/dss
    install -m 0644 ${WORKDIR}/sim.xml ${D}${datadir}/dss/data/sim.xml
    install -m 0755 -d ${D}${datadir}/dss-web/webroot
    install -m 0644 ${S}/webservices/dss.wsdl ${D}${datadir}/dss-web/webroot/dss.wsdl
}

do_compile_append () {
    oe_runmake dsstests
}

pkg_postinst_${PN}() {
#!/bin/sh

if [[ -f "/etc/runit/dss/supervise/stat" ]]; then
    if [[ "`cat /etc/runit/dss/supervise/stat`" == "down" ]]; then
        echo "Starting dSS..."
        sv up /etc/runit/dss
        if [[ $? -ne 0 ]]; then
            echo "sv command return error, could not start dSS!"
        fi
    fi
fi
 
}

pkg_prerm_${PN}() {
#!/bin/sh

if [[  -f "/etc/runit/dss/supervise/stat" ]]; then
    if [[ "`cat /etc/runit/dss/supervise/stat`" == "run" ]]; then
        echo "Stopping dSS..."
        sv -w 5 force-stop /etc/runit/dss
        if [[ $? -ne 0 ]]; then
            echo "sv command returned error, killing dSS process"
            killall -9 dss
        else
            sleep 3
        fi
    fi
fi

# safeguard to make sure dSS is really down
if [[ -n "`pidof dss`" ]]; then
    killall -9 dss
    sleep 1
fi

}

pkg_preinst_${PN}() {
#!/bin/sh

if [[ ! -f "/etc/ssl/certs/dsscert.pem" ]]; then
    mkdir -p /etc/ssl/certs
    create-cert /etc/ssl/certs
fi

if [[ -f "/etc/runit/dss/supervise/stat" ]]; then
    if [[ "`cat /etc/runit/dss/supervise/stat`" == "run" ]]; then
        echo "Stopping dSS..."
        sv -w 5 force-stop /etc/runit/dss
        if [[ $? -ne 0 ]]; then
            echo "sv command returned error, killing dSS process"
            killall -9 dss
        else
            sleep 3
        fi
    fi
fi

# safeguard to make sure dSS is really down
if [[ -n "`pidof dss`" ]]; then
    killall -9 dss
    sleep 1
fi

}

pkg_postrm_${PN}() {
#!/bin/sh

if [[ -d "/etc/runit/dss" ]]; then
    rm -rf /etc/runit/dss
fi

}

CONFFILES_${PN} = "${datadir}/dss/data/sim.xml"

PACKAGES =+ "${PN}-tests ${PN}-tests-dbg ${PN}-ds3 "
FILES_${PN} += " ${datadir}/dss-web "
FILES_${PN}-tests = "${datadir}/dss/dsstests"
FILES_${PN}-tests-dbg += " ${datadir}/dss/.debug/dsstests "

FILES_${PN}-ds3 = "${bindir}/ds3"
RDEPENDS_${PN}-ds3 = "dss curl"
PACKAGE_ARCH_${PN}-ds3 = "all"
