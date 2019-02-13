SUMMARY = "Qt5 demo Music application"
DESCRIPTION = "This is the Music demo application."
LICENSE = "MIT"


#LICENSE = "LGPLv2.1+ & GFDL-1.2"
#LIC_FILES_CHKSUM = "file://COPYING.DOC;md5=ad1419ecc56e060eccf8184a87c4285f \
#                    file://COPYING.LIB;md5=2d5025d4aa3495befef8f17206a5b0a1"

LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=d41d8cd98f00b204e9800998ecf8427e"

#DEPENDS = "qtdeclarative qtgraphicaleffects qtbase qtmultimedia"
DEPENDS = "qtbase qtmultimedia gstreamer1.0"
CXXFLAGS += "-std=gnu++11 `pkg-config --cflags --libs gstreamer-1.0`"
SRC_URI := "\
						file://music-player.desktop \
						file://icons8-music-48.png \
						file://Dhadkan.mp3"
S = "${ROOT_BIBHU}/src-bibhu/musicplayer"
B = "${WORKDIR}/musicplayer"

require recipes-qt/qt5/qt5.inc

do_install() {
	    install -d ${D}${bindir}
			install -d ${D}${datadir}/applications
			install -d ${D}${datadir}/pixmaps
			install -d ${D}${ROOT_HOME}

			install -m 0755 ${B}/SimplePlayer ${D}${bindir}
			install -m 0644 ${WORKDIR}/music-player.desktop ${D}${datadir}/applications
			install -m 0644 ${WORKDIR}/icons8-music-48.png ${D}${datadir}/pixmaps/music.png
			install -m 0644 ${WORKDIR}/Dhadkan.mp3 ${D}${ROOT_HOME}
}

FILES_${PN}-dbg += "${bindir}/.debug"
FILES_${PN} += "${bindir} {datadir}/applications ${datadir}/pixmaps ${ROOT_HOME}"

RDEPENDS_${PN} = "qtbase-plugins qtmultimedia-plugins gstreamer1.0"
