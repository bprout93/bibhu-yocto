# Base this image on core-image-minimal
#include recipes-core/images/core-image-minimal.bb

DESCRIPTION = "Image"

IMAGE_FEATURES += "ssh-server-dropbear"

LICENSE = "MIT"

inherit core-image

