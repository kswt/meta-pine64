SUMMARY = "Pinephone firmware by Megi"
LICENSE = "CLOSED"

SRC_URI+="git://megous.com/git/linux-firmware/;protocol=https;branch=master;rev=5c4c2b89f30a42f5ffabb5b5bcbc799d8ac9f66f"
S = "${WORKDIR}/git"
do_install () {
	install -d ${D}${nonarch_base_libdir}/firmware/rtl_bt/
	install ${S}/rtl_bt/rtl8723cs*.bin ${D}${nonarch_base_libdir}/firmware/rtl_bt/
	install ${S}/ov5640*.bin ${D}${nonarch_base_libdir}/firmware/
	install ${S}/anx7688-fw.bin ${D}${nonarch_base_libdir}/firmware/
}

FILES:${PN} = "${nonarch_base_libdir}/firmware/"
