# This recipe is heavily based on angstrom-feed-configs.bb

DESCRIPTION = "Configuration files for the add-ons repository"

RRECOMMENDS_${PN} += "opkg opkg2json"

LICENSE = "NO LICENSE"


SRC_URI = "file://opkg.conf file://addon.sh file://addon2json.sh"
PR = "r4"

PACKAGE_ARCH = "all"

DSS_ADDON_FEED_BASEPATH ?= "feeds/dss-add-ons-testing/ipk/"
DSS_ADDON_FEED_URI ?= "http://feedback.aizo.com"

OPKG_ADD_ONS_CONF_DIR = "opkg-add-ons"


do_compile() {
	mkdir -p ${S}/${sysconfdir}/${OPKG_ADD_ONS_CONF_DIR}

	rm ${S}/${sysconfdir}/${OPKG_ADD_ONS_CONF_DIR}/arch.conf || true
	ipkgarchs="${PACKAGE_ARCHS}"
	priority=1
	for arch in $ipkgarchs; do 
		echo "arch $arch $priority" >> ${S}/${sysconfdir}/${OPKG_ADD_ONS_CONF_DIR}/arch.conf
		priority=$(expr $priority + 5)
	done

	echo "src/gz add-ons ${DSS_ADDON_FEED_URI}/${DSS_ADDON_FEED_BASEPATH}all" > ${S}/${sysconfdir}/${OPKG_ADD_ONS_CONF_DIR}/add-ons-feed.conf
}


do_install () {
	install -d ${D}${sysconfdir}/${OPKG_ADD_ONS_CONF_DIR}
	install -m 0644  ${S}/${sysconfdir}/${OPKG_ADD_ONS_CONF_DIR}/* ${D}${sysconfdir}/${OPKG_ADD_ONS_CONF_DIR}
    install -m 0644  ${WORKDIR}/opkg.conf ${D}${sysconfdir}/${OPKG_ADD_ONS_CONF_DIR}
    install -d ${D}${bindir}/
    install -m 0755 ${WORKDIR}/addon.sh ${D}${bindir}/addon
    install -m 0755 ${WORKDIR}/addon2json.sh ${D}${bindir}/addon2json
}

FILES_${PN} += " ${sysconfdir}/${OPKG_ADD_ONS_CONF_DIR}/add-ons-feed.conf \
                 ${sysconfdir}/${OPKG_ADD_ONS_CONF_DIR}/arch.conf \
                 ${sysconfdir}/${OPKG_ADD_ONS_CONF_DIR}/opkg.conf \
                 ${D}${bindir}/addon \
               "

CONFFILES_${PN} += "${sysconfdir}/${OPKG_ADD_ONS_CONF_DIR}/add-ons-feed.conf \
					${sysconfdir}/${OPKG_ADD_ONS_CONF_DIR}/arch.conf \
                    ${sysconfdir}/${OPKG_ADD_ONS_CONF_DIR}/opkg.conf \
					"

