SUMMARY = "X server initialization files"
DESCRIPTION = "X server initialization files"
SECTION = "base"
PR = "r0"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=751419260aa954499f7abaabaa882bbe"

SRC_URI = "file://xserver-nodm \
					 file://display.sh  \
					 file://gplv2-license.patch"

S = "${WORKDIR}"

inherit update-rc.d

INITSCRIPT_NAME = "xserver-nodm"
INITSCRIPT_PARAMS = "defaults 40"


do_install() {
			install -d ${D}${sysconfdir}/init.d
			install -d ${D}${sysconfdir}/profile.d

			install -m 0755 ${WORKDIR}/xserver-nodm ${D}${sysconfdir}/init.d
			install -m 0755 ${WORKDIR}/display.sh ${D}${sysconfdir}/profile.d
}

