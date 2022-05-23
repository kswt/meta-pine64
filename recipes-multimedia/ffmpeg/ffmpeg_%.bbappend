SRC_URI = 'git://github.com/jernejsk/FFmpeg;protocol=https;branch=v4l2-request-hwaccel-4.4 \
           file://0001-Add-some-includes.patch'
SRCREV = 'ab65af8bee5ff6cb6b886223f3e7d9a2fedfe972'
FILESEXTRAPATHS:prepend := "${THISDIR}/files:"
S = "${WORKDIR}/git"

DEPENDS = "libdrm eudev"

do_compile[depends] += "virtual/kernel:do_shared_workdir"

PACKAGECONFIG[v4l2-request] = "--enable-v4l2-request, , "
PACKAGECONFIG[libdrm] = "--enable-libdrm ,  , "
PACKAGECONFIG[libudev] = "--enable-libudev ,  , "
PACKAGECONFIG:append =  " v4l2-request libdrm libudev vaapi"
