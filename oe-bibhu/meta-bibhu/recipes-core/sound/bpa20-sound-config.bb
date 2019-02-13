SUMMARY = "Amixer sound configuration files"
DESCRIPTION = "sound configuration file"
SECTION = "base"
PR = "r0"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=751419260aa954499f7abaabaa882bbe"

SRC_URI = "file://asound.conf \
		   file://amixer.sh  \
		   file://gplv2-license.patch \
					 "

S = "${WORKDIR}"

inherit update-rc.d

INITSCRIPT_NAME = "amixer.sh"
INITSCRIPT_PARAMS = "start 45 S . "


do_install() {
			install -d ${D}${sysconfdir}/init.d

			install -m 0755 ${WORKDIR}/amixer.sh ${D}${sysconfdir}/init.d
			install -m 0755 ${WORKDIR}/asound.conf ${D}${sysconfdir}
}

