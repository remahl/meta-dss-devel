# SMTP Client

DESCRIPTION = "A sendmail replacement for use in MTAs like mutt"
HOMEPAGE = "http://msmtp.sourceforge.net/"
SECTION = "console/network"
LICENSE = "GPLv2"
DEPENDS = "zlib gnutls openssl"
RDEPENDS = "ca-certificates"
PR = "r1"

SRC_URI = "${SOURCEFORGE_MIRROR}/msmtp/msmtp-${PV}.tar.bz2 \
  file://msmtprc \
  file://msmtp.logrotate"

SRC_URI[md5sum] = "3ed704fbd3e7419cab5c65bb7928d9ba"
SRC_URI[sha256sum] = "f19f3fcc67da448420b3adbd5add09f8fe110664dd64f3c2cd693ef0cb736887"

EXTRA_OECONF += "\
  --with-libgnutls-prefix=${STAGING_LIBDIR}/.. \
  --with-libssl-prefix=${STAGING_LIBDIR}/.. \
  "

CONFFILES_${PN} = "${sysconfdir}/msmtprc"

inherit autotools

do_install_append() {
    install -d ${D}${sysconfdir}/logrotate.d
    install -m 0755 ${WORKDIR}/msmtprc ${D}${sysconfdir}
    install ${WORKDIR}/msmtp.logrotate ${D}${sysconfdir}/logrotate.d/msmtp
}

