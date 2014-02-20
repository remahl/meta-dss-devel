# create self signed certificate
DESCRIPTION = "Helper script to create a self signed certificate"
LICENSE = "GPL"
PR = "r1"

RDEPENDS_${PN} = "openssl"

SRC_URI = "file://create-cert"

do_install() {
    install -m 0755 -d ${D}${bindir}
    install -m 0755 ${WORKDIR}/create-cert ${D}${bindir}
}

PACKAGE_ARCH_${PN} = "all"

