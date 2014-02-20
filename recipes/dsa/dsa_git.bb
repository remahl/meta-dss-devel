#dSA

SRC_URI = "git://gitorious.digitalstrom.org/ds-assistant/ds-assistant.git;protocol=git;branch=master"

require dsa.inc

PR="r1.${INC_PR}"
SRCREV="${AUTOREV}"
PV = "0.4.2+gitr${SRCPV}"

S="${WORKDIR}/git"
