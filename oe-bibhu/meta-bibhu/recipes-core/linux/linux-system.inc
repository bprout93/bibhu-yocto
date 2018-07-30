DESCRIPTION = "Linux kernel upgrade Image for bpA20 board"
SECTION = "kernel"
PR = "r0"

inherit kernel
#inherit externalsrc

EXTERNALSRC_BUILD = "${WORKDIR}/build_kernel"

require recipes-kernel/linux/linux-dtb.inc

KERNEL_IMAGETYPE = "uImage"

# Kernel Build directory
B = "${EXTERNALSRC_BUILD}"
BUILD_DIR = "${B}"

# Kernel source directory
S = "${WORKDIR}/linux-${PV}"

LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://${S}/README;md5=2c9d85329e7ae72478c92ba4272f2ab0"

EXTRA_OEMAKE_append = "-C ${S} O=${BUILD_DIR}"
KERNEL_EXTRA_ARGS += "LOADADDR=${UBOOT_ENTRYPOINT}"

LINUX_VERSION = "4.17"

KERNEL_IMAGE_BASE_NAME = "${KERNEL_IMAGETYPE}-${P}-${MACHINE}-${DATETIME}"
KERNEL_IMAGE_SYMLINK_NAME = "${KERNEL_IMAGETYPE}-${P}-${MACHINE}"
MODULE_IMAGE_BASE_NAME = "modules-${P}-${MACHINE}-${DATETIME}"
MODULE_TARBALL_SYMLINK_NAME = "modules-${P}-${MACHINE}.tgz"
INITRAMFS_BASE_NAME = "${KERNEL_IMAGETYPE}-bundleinitramfs-${P}-${MACHINE}-${DATETIME}"
INITRAMFS_SYMLINK_NAME = "${KERNEL_IMAGETYPE}-bundleinitramfs-${P}-${MACHINE}"

