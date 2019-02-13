# Copyright (C) 2009-2017, O.S. Systems Software Ltda. All Rights Reserved
# Released under the MIT license (see packages/COPYING)

DESCRIPTION ?= "Browser made by mozilla"
DEPENDS += "alsa-lib curl startup-notification libevent libnotify libvpx \
            virtual/libgl nss nspr pulseaudio yasm-native icu unzip-native gtk+3"

LICENSE = "MPLv1 | GPLv2+ | LGPLv2.1+"
LIC_FILES_CHKSUM = "file://toolkit/content/license.html;endline=39;md5=f7e14664a6dca6a06efe93d70f711c0e"

SRC_URI = "https://archive.mozilla.org/pub/firefox/releases/${PV}/source/firefox-${PV}.source.tar.xz;name=archive \
           file://mozilla-firefox.png \
           file://mozilla-firefox.desktop \
           file://vendor.js \
           file://avoid-running-config-status.patch \
           file://avoid-running-autoconf2.13.patch \
           file://remove-needless-windows-dependency.patch \
           file://fixes/Allow-.js-preference-files-to-set-locked-prefs-with-.patch \
           file://porting/Add-xptcall-support-for-SH4-processors.patch \
           file://porting/NSS-Fix-FTBFS-on-Hurd-because-of-MAXPATHLEN.patch \
           file://porting/NSS-GNU-kFreeBSD-support.patch \
           file://porting/Make-powerpc-not-use-static-page-sizes-in-mozjemallo.patch \
           file://porting/Disable-libyuv-assembly-on-mips64.patch \
           file://porting/Fix-CPU_ARCH-test-for-libjpeg-on-mips.patch \
           file://porting/Fix-crashes-in-AtomicOperations-none-on-s390x.patch \
           file://porting/Fix-broken-MIPS-build.patch \
           file://porting/Work-around-Debian-bug-844357.patch \
           file://prefs/Set-javascript.options.showInConsole.patch \
           file://prefs/Set-DPI-to-system-settings.patch \
           file://prefs/Don-t-auto-disable-extensions-in-system-directories.patch \
           file://debian-hacks/Avoid-wrong-sessionstore-data-to-keep-windows-out-of.patch \
           file://debian-hacks/Add-another-preferences-directory-for-applications-p.patch \
           file://debian-hacks/Don-t-register-plugins-if-the-MOZILLA_DISABLE_PLUGIN.patch \
           file://debian-hacks/Use-a-variable-for-xulrunner-base-version-in-various.patch \
           file://debian-hacks/Don-t-error-out-when-run-time-libsqlite-is-older-tha.patch \
           file://debian-hacks/Add-a-2-minutes-timeout-on-xpcshell-tests.patch \
           file://debian-hacks/Work-around-binutils-assertion-on-mips.patch \
           file://debian-hacks/Don-t-build-image-gtests.patch \
           file://debian-hacks/Allow-to-override-ICU_DATA_FILE-from-the-environment.patch \
           file://debian-hacks/Force-use-the-i686-rust-target.patch \
           file://debian-hacks/Set-program-name-from-the-remoting-name.patch \
           file://debian-hacks/Build-against-system-libjsoncpp.patch \
           file://debian-hacks/Use-the-Mozilla-Location-Service-key-when-the-Google.patch \
           file://0001-use-pkg-config-to-find-nss.patch \
           file://0002-use-pkg-config-to-find-nspr.patch \
           file://0003-do-not-link-against-crmf-library-it-is-not-there.patch \
           "

SRC_URI[archive.md5sum] = "32a7c074788569ca548de2c0394fd0aa"
SRC_URI[archive.sha256sum] = "babed4fe0ae95783e39358aedf7111b20fd9442f73b3b41b025fa4951fe76287"

S = "${WORKDIR}/firefox-${PV}"
# MOZ_APP_BASE_VERSION should be incremented after a release
MOZ_APP_BASE_VERSION = "52.8"

inherit mozilla

DISABLE_STATIC=""
EXTRA_OEMAKE += "installdir=${libdir}/${PN}-${MOZ_APP_BASE_VERSION}"

ARM_INSTRUCTION_SET = "arm"

do_install_append() {
    install -d ${D}${datadir}/applications
    install -d ${D}${datadir}/pixmaps
    install -d ${D}${libdir}/${PN}/defaults/pref

    install -m 0644 ${WORKDIR}/mozilla-firefox.desktop ${D}${datadir}/applications/
    install -m 0644 ${WORKDIR}/mozilla-firefox.png ${D}${datadir}/pixmaps/
    install -m 0644 ${WORKDIR}/vendor.js ${D}${libdir}/${PN}/defaults/pref/

    # Fix ownership of files
    chown root:root -R ${D}${datadir}
    chown root:root -R ${D}${libdir}
}

FILES_${PN} = "${bindir}/${PN} \
               ${datadir}/applications/ \
               ${datadir}/pixmaps/ \
               ${libdir}/${PN} \
							 ${libdir}/${PN}-${MOZ_APP_BASE_VERSION}/gtk2/libmozgtk.so \
							 ${libdir}/${PN}-${MOZ_APP_BASE_VERSION}/dependentlibs.list \
							 ${libdir}/${PN}-${MOZ_APP_BASE_VERSION}/firefox \
							 ${libdir}/${PN}-${MOZ_APP_BASE_VERSION}/firefox.bin \
							 ${libdir}/${PN}-${MOZ_APP_BASE_VERSION}/platform.ini \
							 ${libdir}/${PN}-${MOZ_APP_BASE_VERSION}/plugin-container \
							 ${libdir}/${PN}-${MOZ_APP_BASE_VERSION}/chrome.manifest \
							 ${libdir}/${PN}-${MOZ_APP_BASE_VERSION}/run-mozilla.sh \
							 ${libdir}/${PN}-${MOZ_APP_BASE_VERSION}/libmozgtk.so \
							 ${libdir}/${PN}-${MOZ_APP_BASE_VERSION}/application.ini \
							 ${libdir}/${PN}-${MOZ_APP_BASE_VERSION}/libxul.so \
							 ${libdir}/${PN}-${MOZ_APP_BASE_VERSION}/omni.ja \
							 ${libdir}/${PN}-${MOZ_APP_BASE_VERSION}/removed-files \
							 ${libdir}/${PN}-${MOZ_APP_BASE_VERSION}/libmozsqlite3.so \
							 ${libdir}/${PN}-${MOZ_APP_BASE_VERSION}/liblgpllibs.so \
							 ${libdir}/${PN}-${MOZ_APP_BASE_VERSION}/fonts \
							 ${libdir}/${PN}-${MOZ_APP_BASE_VERSION}/defaults \
							 ${libdir}/${PN}-${MOZ_APP_BASE_VERSION}/browser \
							 ${libdir}/${PN}-${MOZ_APP_BASE_VERSION}/gmp-clearkey \
							 ${libdir}/${PN}-${MOZ_APP_BASE_VERSION}/fonts/EmojiOneMozilla.ttf \
							 ${libdir}/${PN}-${MOZ_APP_BASE_VERSION}/dictionaries/en-US.aff \
							 ${libdir}/${PN}-${MOZ_APP_BASE_VERSION}/dictionaries/en-US.dic \
							 ${libdir}/${PN}-${MOZ_APP_BASE_VERSION}/defaults/pref \
							 ${libdir}/${PN}-${MOZ_APP_BASE_VERSION}/defaults/pref/channel-prefs.js \
							 ${libdir}/${PN}-${MOZ_APP_BASE_VERSION}/browser/chrome.manifest \
							 ${libdir}/${PN}-${MOZ_APP_BASE_VERSION}/browser/blocklist.xml \
							 ${libdir}/${PN}-${MOZ_APP_BASE_VERSION}/browser/extensions \
							 ${libdir}/${PN}-${MOZ_APP_BASE_VERSION}/browser/features \
							 ${libdir}/${PN}-${MOZ_APP_BASE_VERSION}/browser/chrome \
							 ${libdir}/${PN}-${MOZ_APP_BASE_VERSION}/browser/icons \
							 ${libdir}/${PN}-${MOZ_APP_BASE_VERSION}/browser/extensions/972ce4c6-7e08-4474-a285-3208198ce6fd}.xpi \
							 ${libdir}/${PN}-${MOZ_APP_BASE_VERSION}/browser/features/aushelper@mozilla.org.xpi \
							 ${libdir}/${PN}-${MOZ_APP_BASE_VERSION}/browser/features/firefox@getpocket.com.xpi \
							 ${libdir}/${PN}-${MOZ_APP_BASE_VERSION}/browser/features/webcompat@mozilla.org.xpi \
							 ${libdir}/${PN}-${MOZ_APP_BASE_VERSION}/browser/features/e10srollout@mozilla.org.xpi \
							 ${libdir}/${PN}-${MOZ_APP_BASE_VERSION}/browser/chrome/icons \
							 ${libdir}/${PN}-${MOZ_APP_BASE_VERSION}/browser/chrome/icons/default \
							 ${libdir}/${PN}-${MOZ_APP_BASE_VERSION}/browser/chrome/icons/default/default48.png \
							 ${libdir}/${PN}-${MOZ_APP_BASE_VERSION}/browser/chrome/icons/default/default16.png \
							 ${libdir}/${PN}-${MOZ_APP_BASE_VERSION}/browser/chrome/icons/default/default32.png \
							 ${libdir}/${PN}-${MOZ_APP_BASE_VERSION}/browser/icons/mozicon128.png \
							 ${libdir}/${PN}-${MOZ_APP_BASE_VERSION}/gmp-clearkey/0.1 \
							 ${libdir}/${PN}-${MOZ_APP_BASE_VERSION}/gmp-clearkey/0.1/libclearkey.so \
							 ${libdir}/${PN}-${MOZ_APP_BASE_VERSION}/gmp-clearkey/0.1/clearkey.info \
               ${bindir}/defaults"
FILES_${PN}-dev += "${datadir}/idl ${bindir}/${PN}-config ${libdir}/${PN}-devel-*/bin ${libdir}/${PN}-devel-*/idl ${libdir}/${PN}-devel-*/lib ${libdir}/${PN}-devel-*/include ${libdir}/${PN}-devel-*/xpcom-config.h ${libdir}/${PN}-devel-*/sdk/bin/* ${libdir}/${PN}-devel-*/sdk/lib/libxul.so ${libdir}/${PN}-${MOZ_APP_BASE_VERSION}/firefox-bin"
FILES_${PN}-staticdev += "${libdir}/${PN}-devel-*/sdk/lib/*.a"
FILES_${PN}-dbg += "${libdir}/${PN}-${MOZ_APP_BASE_VERSION}/*/*/.debug/* ${libdir}/${PN}-${MOZ_APP_BASE_VERSION}/*/.debug/* ${libdir}/${PN}-${MOZ_APP_BASE_VERSION}-devel-*/sdk/lib/.debug/* ${libdir}/${PN}-${MOZ_APP_BASE_VERSION}/.debug/* ${libdir}/${PN}-devel-*/sdk/lib/.debug/*"
# We don't build XUL as system shared lib, so we can mark all libs as private
PRIVATE_LIBS = "libmozjs.so \
                libxpcom.so \
                libnspr4.so \
                libxul.so \
                libmozalloc.so \
                libplc4.so \
                libplds4.so \
                liblgpllibs.so \
                libmozsqlite3.so \
                libbrowsercomps.so \
                libclearkey.so"
