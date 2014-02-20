# digitalSTROM Server build file

SRC_URI="git://gitorious.digitalstrom.org/dss/dss-mainline.git;protocol=git;branch=master"

PR = "r1.${INC_PR}"
SRCREV="${AUTOREV}"
PV = "1.2.0+gitr${SRCPV}"

S="${WORKDIR}/git"

require dss.inc

do_configure_prepend() {
    echo "${SRCPV}" > ${S}/.dss_git_version
}

do_install_prepend() {
    install -d ${S}/data/webroot
}

