# UPnP beacon utility
DESCRIPTION = "Utility to announce presence of a device via UPnP"
LICENSE = "GPL"
HOMEPAGE = "http://www.digitalstrom.org/"
DEPENDS = "libupnp libconfig util-linux-ng"
PR="r3"

SRC_URI="http://developer.digitalstrom.org/releases/tools/upnpbeacon-${PV}.tar.gz \
         file://upnpbeacon.cfg \
         file://upnpbeacon.ifup \
         file://upnpbeacon.init \
         file://upnpbeacon.udhcpc"

INITSCRIPT_NAME = "upnpbeacon"
INITSCRIPT_PARAMS = "defaults 99"

inherit autotools update-rc.d

CONFFILES_${PN} = "${sysconfdir}/upnpbeacon.cfg ${sysconfdir}/upnpbeacon.udn"

SRC_URI[md5sum] = "981763329548a620ca03cd8d2626ae20"
SRC_URI[sha256sum] = "e3972d808885acb1b9262db246e0eef11abb23ff9376b30793d576a0d97e5702"

do_install_append() {
    install -m 0755 -d ${D}${sysconfdir}
    install ${WORKDIR}/upnpbeacon.cfg ${D}${sysconfdir}/upnpbeacon.cfg
    touch ${D}${sysconfdir}/upnpbeacon.udn

    install -D -m 0755 ${WORKDIR}/upnpbeacon.init ${D}${sysconfdir}/init.d/upnpbeacon
    install -D -m 0755 ${WORKDIR}/upnpbeacon.ifup ${D}${sysconfdir}/network/if-up.d/upnpbeacon
    install -D -m 0755 ${WORKDIR}/upnpbeacon.udhcpc ${D}${sysconfdir}/udhcpc.d/99upnpbeacon
}

