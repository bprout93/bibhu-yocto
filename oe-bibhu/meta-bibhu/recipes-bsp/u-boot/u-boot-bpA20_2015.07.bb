U_BOOT_BUILDDIR = "${WORKDIR}/build_uboot"

require u-boot-bpA20.inc

COMPATIBLE_MACHINE = "bpA20"

PROVIDES = "virtual/bootloader"

UBOOT_MACHINE = "bpA20_config"

S = "${ROOT_BIBHU}/src-bibhu/u-boot/u-boot-2015.07"

EXTRA_OEMAKE = 'O=${U_BOOT_BUILDDIR} CROSS_COMPILE=${TARGET_PREFIX} CC="${TARGET_PREFIX}gcc ${TOOLCHAIN_OPTIONS}" V=1'


PACKAGE_ARCH = "${MACHINE_ARCH}"
SPL_BINARY ="u-boot-sunxi-with-spl.bin"
# v2015.07 = 33711bdd4a4dce942fb5ae85a68899a8357bdd94
SRCREV = "33711bdd4a4dce942fb5ae85a68899a8357bdd94"
