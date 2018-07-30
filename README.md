# Steps to Build binary

source setup-env bibhu build_bpA20 bpA20

source setup-env <platform> build_<Machine> <Machine>

bitbake bibhu-bpa20-image    (for banana pi image)


bitbake bibhu-bbb-image      (for beaglebone black)

#To build only kernel

bitbake linux-bpA20-system


bitbake linux-bbb-system
