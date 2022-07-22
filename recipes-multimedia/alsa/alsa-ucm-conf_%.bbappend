SUMMARY = "ALSA Use Case Manager configuration with PinePhone support"

LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=20d74d74db9741697903372ad001d3b4"

SRC_URI = "git://github.com/alsa-project/alsa-ucm-conf;protocol=https;branch=master \
           https://github.com/alsa-project/alsa-ucm-conf/pull/134.patch"
SRC_URI[sha256sum] = "2549fd55721bcb6e2ed06be9ecf1b83f79fbc2673854d04b9ca52f88614099e3"
SRCREV = "6e1c0847b5ef9e3368aaabc4f35d4694dcbe49b2"

S = "${WORKDIR}/git"
