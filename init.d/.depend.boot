TARGETS = fake-hwclock hostname.sh mountkernfs.sh udev mountdevsubfs.sh hwclock.sh hdparm mountall.sh mountall-bootclean.sh mountnfs.sh mountnfs-bootclean.sh urandom checkroot.sh rpcbind nfs-common kbd bootmisc.sh procps kmod udev-finish checkfs.sh checkroot-bootclean.sh
INTERACTIVE = udev checkroot.sh kbd checkfs.sh
udev: mountkernfs.sh
mountdevsubfs.sh: mountkernfs.sh udev
hwclock.sh: mountdevsubfs.sh
hdparm: mountdevsubfs.sh udev
mountall.sh: checkfs.sh checkroot-bootclean.sh
mountall-bootclean.sh: mountall.sh
mountnfs.sh: mountall.sh mountall-bootclean.sh rpcbind nfs-common
mountnfs-bootclean.sh: mountall.sh mountall-bootclean.sh mountnfs.sh
urandom: mountall.sh mountall-bootclean.sh hwclock.sh
checkroot.sh: hwclock.sh fake-hwclock mountdevsubfs.sh hostname.sh hdparm
rpcbind: mountall.sh mountall-bootclean.sh
nfs-common: rpcbind hwclock.sh
kbd: mountall.sh mountall-bootclean.sh mountnfs.sh mountnfs-bootclean.sh
bootmisc.sh: mountall-bootclean.sh mountnfs-bootclean.sh checkroot-bootclean.sh mountall.sh mountnfs.sh udev
procps: mountkernfs.sh mountall.sh mountall-bootclean.sh udev
kmod: checkroot.sh
udev-finish: udev mountall.sh mountall-bootclean.sh
checkfs.sh: checkroot.sh
checkroot-bootclean.sh: checkroot.sh
