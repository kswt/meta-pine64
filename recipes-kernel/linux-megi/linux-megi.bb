DESCRIPTION = "Megi's kernel for Pinephone"
SECTION = "kernel"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://${S}/COPYING;md5=6bc538ed5bd9a7fc9398086aedcd7e46"
#LIC_FILES_CHKSUM = "file://${S}/COPYING;md5=bbea815ee2795b2f4230826c0c6b8814"

inherit kernel
require recipes-kernel/linux/linux-yocto.inc

LINUX_VERSION ?= "5.19"
#LINUX_VERSION ?= "5.9"
LINUX_VERSION_EXTENSION = "-megi"
KERNEL_VERSION_SANITY_SKIP="1"

# Yes, this is valid branch name for Pinephone
BRANCH = "orange-pi-${LINUX_VERSION}"
SRCREV = "${AUTOREV}"

PV = "${LINUX_VERSION}+git${SRCPV}"

SRC_URI = "git://github.com/megous/linux;protocol=https;branch=${BRANCH} \
	   file://linux-megi.cfg \
	  "

KBUILD_DEFCONFIG:pinephone = "pinephone_defconfig"
#KBUILD_DEFCONFIG:pinephone = "defconfig"
KCONFIG_MODE="--alldefconfig"

COMPATIBLE_MACHINE = "pinephone"

# This is necessary since kmeta would be necessary otherwise
KERNEL_FEATURES:remove = "cfg/fs/vfat.scc"
