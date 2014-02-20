# Copyright (C) 2007-2008, Stelios Koroneos - Digital OPSiS, All Rights Reserved
# Released under the MIT license (see packages/COPYING)
DESCRIPTION = "A cgi wrapper for embedding shell scripts into html documents"
SECTION = "console/network"
LICENSE = "GPL"

SRC_URI = "${SOURCEFORGE_MIRROR}/${PN}/${PN}-${PV}.tar.gz"

PR="r1"

inherit autotools gettext

SRC_URI[md5sum] = "4cac9409530200b4a7a82a48ec174800"
SRC_URI[sha256sum] = "0f6569f7af6eaa448d3daf72914c96835a3b1bb62bd53dec8de3571edbdff22d"

