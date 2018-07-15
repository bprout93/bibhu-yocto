DESCRIPTION = "Linux kernel System Image for bpA20 board"
SECTION = "kernel"
PR = "r0"

#STAGING_KERNEL_BUILDDIR = "${TMPDIR}/work-shared/${MACHINE}/kernel-build-artifacts-system"

KERNEL_DEVICETREE_bpA20 = " \
    sun7i-a20-bananapi.dtb \
"
require linux-bpA20.inc

PROVIDES = "virtual/kernel"

LINUX_VERSION = "4.17"

FILESEXTRAPATHS_prepend := "${THISDIR}/linux-bpA20-system:"

# v4.9.58 = 1af952704416d76ad86963f04feb10a3da143901
SRCREV = "1af952704416d76ad86963f04feb10a3da143901"

SRC_URI = " \
        file://defconfig \
"
do_configure_prepend() {
    install -m 0644 ${WORKDIR}/defconfig ${BUILD_DIR}/.config
}

do_install_append() {
    cd ${B}
    cat arch/arm/boot/zImage arch/arm/boot/dts/sun7i-a20-bananapi.dtb > zImage_system_with_dtb
    mkimage -A arm -O linux -T kernel -C none -a ${UBOOT_ENTRYPOINT} -e ${UBOOT_ENTRYPOINT} -d zImage_system_with_dtb uImage_system_with_dtb

    install -d ${D}/boot
    install -m 0644 uImage_system_with_dtb ${D}/boot/uImage-system
}
