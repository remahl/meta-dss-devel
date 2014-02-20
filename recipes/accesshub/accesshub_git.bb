# Access Hub

DESCRIPTION = "Access Hub is a daemon that will open a tunnel and allow access from the outside through your firewall"
MAINTAINER="Sergey 'Jin' Bostandzhyan <jin@dev.digitalstrom.org>"
HOMEPAGE = "http://gitorious.digitalstrom.org/ds-access-hub/ds-accesshub"

LICENSE = "GPLv3"

SRC_URI = "git://gitorious.digitalstrom.org/ds-access-hub/ds-accesshub.git;protocol=git;branch=master"


PR="r2"
SRCREV="${AUTOREV}"
PV = "1.0+gitr${SRCPV}"

S="${WORKDIR}/git"

inherit autotools

EXTRA_OECONF = " --enable-accessdaemon "

