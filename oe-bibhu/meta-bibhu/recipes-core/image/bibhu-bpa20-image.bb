SUMMARY = "A small image just capable of allowing a device to boot."

IMAGE_INSTALL += "packagegroup-core-boot ${ROOTFS_PKGMANAGE_BOOTSTRAP} ${CORE_IMAGE_EXTRA_INSTALL}"

IMAGE_LINGUAS = "en-us"

LICENSE = "MIT"

inherit core-image

IMAGE_ROOTFS_SIZE ?= "4096"

IMAGE_INSTALL_append = " openssh \
												openssl \
												opkg \
												lighttpd \
												usbutils \
												gstreamer1.0-meta-base \
												gstreamer1.0-meta-audio \
												gstreamer1.0-meta-video \
												gstreamer1.0-meta-x11-base \
												gstreamer1.0-plugins-good-meta \
												gstreamer1.0-plugins-base-meta \
												gstreamer1.0-plugins-bad-meta \
												gstreamer1.0-plugins-good \
												gstreamer1.0-plugins-base \
												gstreamer1.0-plugins-bad \
												gstreamer1.0-rtsp-server \
												"
