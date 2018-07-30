DESCRIPTION = "Linux kernel System Image for beagle bone black board"
SECTION = "kernel"
PR = "r0"

KERNEL_DEVICETREE_bbb = " \
		am335x-boneblack.dtb \
"
PV = "4.17.8"

require linux-system.inc

STAGING_KERNEL_DIR = "${S}"

COMPATIBLE_MACHINE = "bbb"

PROVIDES = "virtual/kernel"

LINUX_VERSION = "4.17"

FILESEXTRAPATHS_prepend := "${THISDIR}/linux-bbb-system:"

SRC_URI[md5sum] = "74a9f83da76d868cda5dd50ccd80c355"
SRC_URI[sha256sum] = "ccddd8800953a54df39aa549b438a874441df153083b221c9fb7b37e6fff701c"

SRC_URI = " \
		https://mirrors.edge.kernel.org/pub/linux/kernel/v4.x/linux-${PV}.tar.gz \
        file://defconfig \
"
do_configure_prepend() {
    install -m 0644 ${WORKDIR}/defconfig ${BUILD_DIR}/.config
}

do_install_append() {
    cd ${B}
    cat arch/arm/boot/zImage arch/arm/boot/dts/am335x-boneblack.dtb > zImage_system_with_dtb
    mkimage -A arm -O linux -T kernel -C none -a ${UBOOT_ENTRYPOINT} -e ${UBOOT_ENTRYPOINT} -d zImage_system_with_dtb uImage_system_with_dtb

    install -d ${D}/boot
    install -m 0644 uImage_system_with_dtb ${D}/boot/uImage-system
}
