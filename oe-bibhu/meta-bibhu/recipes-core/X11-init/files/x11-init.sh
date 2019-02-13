#!/bin/sh

PATH=/sbin:/bin:/usr/sbin:/usr/bin
DAEMON=/usr/bin/Xorg
NAME=Xorg
DESC="X11 server"
OPTS="-noreset -nocursor -nolisten tcp"

case "$1" in
	start)
		echo -n "Starting $DESC: "
		start-stop-daemon --start --background -x "$DAEMON" -- $OPTS
		sleep 1
		echo "$NAME."
		;;
	stop)
		echo -n "Stopping $DESC: "
		xrandr --output "DISP3 BG" --rotation normal
		start-stop-daemon --stop -x "$DAEMON"
		echo "$NAME."
		;;
	restart|force-reload)
		echo -n "Restarting $DESC: "
		xrandr --output "DISP3 BG" --rotation normal
		start-stop-daemon --stop -x "$DAEMON"
		sleep 1
		start-stop-daemon --start --background -x "$DAEMON" -- $OPTS
		sleep 1
		echo "$NAME."
		;;
	*)
		N=/etc/init.d/$NAME
		echo "Usage: $N {start|stop|restart|force-reload}" >&2
		exit 1
		;;
esac

exit 0
