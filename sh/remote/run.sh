#! /bin/bash

# some var
server_list=server.ini
param=
item_name=
user=
outer_host=
local_host=
dash_line="-----------------------------------------------------------------------------"

# help
if [ -z "$1" ] || [ "$1" = "help" ] ; then
    echo
    echo $dash_line
    while read LINE
    do
	param=$(echo $LINE | awk '{print $1}')
	item_name=$(echo $LINE | awk '{print $2}')
	user=$(echo $LINE | awk '{print $3}')
	outer_host=$(echo $LINE | awk '{print $4}')
	local_host=$(echo $LINE | awk '{print $5}')
	echo "$0 $param ====>>> run [$outer_host ($local_host)] $item_name "
   done <$server_list
    echo $dash_line
	exit 0
fi

LINE=`grep "^$1 " $server_list`
if [ -z "$LINE" ] ; then
    echo $dash_line
    echo "bad parameter!"
    echo "please $0  or $0 help  to get help"
    echo $dash_line
    exit 0
fi

param=$(echo $LINE | awk '{print $1}')
item_name=$(echo $LINE | awk '{print $2}')
user=$(echo $LINE | awk '{print $3}')
outer_host=$(echo $LINE | awk '{print $4}')
local_host=$(echo $LINE | awk '{print $5}')
program_type=$(echo $LINE | awk '{print $6}')
base_dir=$(echo $LINE | awk '{print $7}')
command_dir=
command=
start_log_type=
case $program_type in
    1)
	command_dir=$base_dir/bin
	command=startup.sh
	start_log_type=tomcat
	;;
    2)
    command_dir=$base_dir
	command=start.sh
	start_log_type=nohup
	;;
esac


ssh $user@${outer_host} <<EOF

cd $command_dir && ./$command
echo "run finished ,please check!"
exit
EOF

./logtail.sh $1 $start_log_type

echo $dash_line
exit 0

