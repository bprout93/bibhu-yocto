DESCRIPTION = "Linux kernel System Image for banana pi board"
SECTION = "kernel"
PR = "r0"

KERNEL_DEVICETREE_bpA20 = " \
    sun7i-a20-bananapi.dtb \
"
PV = "4.17.8"

require linux-system.inc

COMPATIBLE_MACHINE = "bpA20"

PROVIDES = "virtual/kernel"

LINUX_VERSION = "4.17"

FILESEXTRAPATHS_prepend := "${THISDIR}/linux-bpA20-system:"

SRC_URI[md5sum] = "74a9f83da76d868cda5dd50ccd80c355"
SRC_URI[sha256sum] = "ccddd8800953a54df39aa549b438a874441df153083b221c9fb7b37e6fff701c"

SRC_URI = " \
		https://mirrors.edge.kernel.org/pub/linux/kernel/v4.x/linux-${PV}.tar.gz \
        file://defconfig \
				file://boot.cmd \
"
do_configure_prepend() {
    install -m 0644 ${WORKDIR}/defconfig ${BUILD_DIR}/.config
}

do_install_append() {
    cd ${B}
    cat arch/arm/boot/zImage arch/arm/boot/dts/sun7i-a20-bananapi.dtb > zImage_system_with_dtb
    mkimage -A arm -O linux -T kernel -C none -a ${UBOOT_ENTRYPOINT} -e ${UBOOT_ENTRYPOINT} -d zImage_system_with_dtb uImage_system_with_dtb
		mkimage -A arm -O linux -T script -C none -d ${WORKDIR}/boot.cmd ${WORKDIR}/boot.scr
    cp ${WORKDIR}/boot.scr arch/arm/boot
		install -d ${D}/boot
    install -m 0644 uImage_system_with_dtb ${D}/boot/uImage-system
}
