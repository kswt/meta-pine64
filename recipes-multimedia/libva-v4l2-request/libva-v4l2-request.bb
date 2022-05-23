SUMMARY = "v4l2-request libVA Backend"

SRCREV = "a3c2476de19e6635458273ceeaeceff124fabd63"
SRC_URI = "git://github.com/bootlin/libva-v4l2-request;protocol=https;branch=master \
           https://github.com/bootlin/libva-v4l2-request/pull/38.patch"
SRC_URI[sha256sum] = "6dd6cd65604bafe542a15104683674660cd96311ab677cf78372f72e1379d5a0"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${S}/COPYING.MIT;md5=0fdfbe4b63f3b713a3427cec241e05a4"

S = "${WORKDIR}/git"

inherit features_check pkgconfig meson

DEPENDS = "libva"

REQUIRED_DISTRO_FEATURES = "x11"

FILES:${PN} += "${libdir}/dri/v4l2_request_drv_video.so"

