SUMMARY = "Test bibhu_char"
DESCRIPTION = "Test bibhu_char device driver from userspace"
PR = "r0"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=d41d8cd98f00b204e9800998ecf8427e"
S = "${ROOT_BIBHU}/src-bibhu/bibhu_char"

B = "${WORKDIR}/build"

do_compile() {
        #make OUTDIR=${BUILD_DIR}
	${CC}  ${S}/test_bibhu_char.c -o ${B}/test_bibhu_char
}

do_install () {
        install -d ${D}/${datadir}/
				install ${B}/test_bibhu_char ${D}/${datadir}/
}
FILES_${PN} = "${datadir}/test_bibhu_char"
