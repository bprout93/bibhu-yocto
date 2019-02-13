SUMMARY = "A small image just capable of allowing a device to boot."

IMAGE_LINGUAS = "en-us"

export IMAGE_BASENAME = "bibhu-image-rootfs"

IMAGE_FSTYPES = "tar.bz2 ext4"

#CORE_IMAGE_EXTRA_INSTALL = "packagegroup-core-x11"

LICENSE = "MIT"

inherit core-image

DEPENDS = "virtual/kernel virtual/bootloader"

IMAGE_ROOTFS_SIZE ?= "4096"

inherit extrausers

EXTRA_USERS_PARAMS = "useradd -P einfochips -d /home/bibhu bibhu;\
											usermod -P bibhu root;\
											"

GSTREAMER = "gstreamer1.0-meta-base \
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
						 gstreamer1.0-libav"
XSERVER += "x11-init \
						xinit \
					 xinput \
					 xrandr \
					 xinput-calibrator \
					 x11-common \
					 xauth \
					 xset \
					 "
IMAGE_INSTALL_append = "\
												sudo \
												glibc-utils \
												localedef \
												twm \
												xclock \
												psplash \
												xterm \
												firefox-dev \
												base-files \
												base-passwd \
												openssh \
												openssl \
												opkg \
												lighttpd \
												usbutils \
												iputils \
												coreutils \
												dhcp-client \
												ethtool \
												bash \
												gnutls \
												binutils \
												grep \
												i2c-tools \
												${XSERVER} \
												packagegroup-core-x11-sato-base \
												packagegroup-core-x11-sato-apps \
												matchbox-wm \
												alsa-utils \
												pulseaudio-module-alsa-card \
												pulseaudio-server \
												libpulsecore \
												musicplayer \
												polkit \
												bpa20-sound-config \
												${GSTREAMER} \
												"
